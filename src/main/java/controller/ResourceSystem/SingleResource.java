package controller.ResourceSystem;

import controller.BaseController;
import cst.Constants;
import cst.Message;
import cst.Page;
import framework.exceptions.ServiceException;
import model.ResourceEntity;
import model.UserActionHistoryEntity;
import model.UsercommentEntity;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.commentSystem.CommentService;
import service.resourceSystem.loadResource.ILoadResourceService;
import service.resourceSystem.singleResource.ISingleResourceService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Karn on 2017/2/5.
 */
@Controller
public class SingleResource extends BaseController {
    @Autowired
    private HttpServletRequest request;
    @Resource
    private ILoadResourceService loadResourceService;

    @Resource
    private ISingleResourceService singleResourceService;

    @Resource
    private CommentService commentService;
    /**
     * 加载单个资源
     */
    @RequestMapping(value = "/jsp/singleResource")
    public ModelAndView loadSingleResource(){
        int resourceID = Integer.parseInt(request.getParameter("resourceID"));
        ModelAndView mv = new ModelAndView();
        mv.setViewName("singleResource");
        try {
            Map<String,String> map = loadResourceService.getResourceInfoByID(resourceID,true);
            setIsPraiseAndIsCollectInfo(map);
            mv.addObject("resourceMap",map);
            UserActionHistoryEntity userActionHistoryEntity = (UserActionHistoryEntity) request.getAttribute("userActionHistory");
            userActionHistoryEntity.setAboutThemeId(resourceID);
            List<ResourceEntity> recommendResources;
            if (userActionHistoryEntity.getUserId() == 0) {
                recommendResources = recommendService.getRecommendResources(4, 0, 0, 0, null).get("hottestResource");
            } else {
                actionCollectService.handleUserActionHistory(userActionHistoryEntity, 0);
                recommendResources = recommendService.getRecommendResources(0, 0, 4, 0, (List<Integer>) request.getSession().getAttribute(Constants.USER_INTEREST)).get("hottestInterestingResource");
            }
            mv.addObject("recommendResources", recommendResources);
            return mv;
        } catch (ServiceException e) {
            return toError(Message.RESPONSE_ERROR, Page.INDEX, "系统忙, 请稍后重试");
        }
    }
    /**
     * 加载评论
     */
    @RequestMapping(value = "/jsp/resource/loadComment")
    @ResponseBody
    public List<Map<String, String>> loadComments(@RequestParam("resourceID") String resourceID,
                                                @RequestParam("pageNum") String pageNum)
            throws ServiceException {
        int resource_id = Integer.parseInt(resourceID);
        int page_num = Integer.parseInt(pageNum);
        List<UsercommentEntity> usercommentEntities = commentService.getResourceUsercommentList(resource_id,page_num);
        return singleResourceService.getResourceCommentInfo(resource_id,usercommentEntities);
    }

    /**
     * 下载资源
     * @throws IOException
     */
    @RequestMapping(value = "/jsp/resource/download")
    public ResponseEntity<byte[]> downloadResource() throws IOException {
        int resourceID = Integer.parseInt(request.getParameter("resourceID"));
        String uploaderID = request.getParameter("uploaderID");
        String fileName = request.getParameter("resourceName");
        String fileRelativePath = "/fileResources/" + String.valueOf(uploaderID);
        String filePath = request.getSession().getServletContext().getRealPath(fileRelativePath);
        File file = new File(filePath,fileName);
        try {
            singleResourceService.downloadResoure(resourceID);
        } catch (ServiceException e) {
            return null;
        }
        //防止文件名在浏览器端乱码
        String generalFileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", generalFileName);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }

    /**
     * 在map中添加用户是否点过赞和收藏过的信息
     * @param map
     */
    private void setIsPraiseAndIsCollectInfo(Map<String,String> map){
        if(request.getSession().getAttribute(Constants.USER_Id) == null){
            map.put("ifPraised","false");
            map.put("ifCollected","false");
            return;
        }
        String user_id = request.getSession().getAttribute(Constants.USER_Id).toString();
        String[] praiseUsers = map.get("praiseUsers").split(" ");
        String[] collectUsers = map.get("collectUsers").split(" ");
        boolean flag = false;
        for(String user : praiseUsers){
            if(user.equals(user_id)){
                flag = true;
                break;
            }
        }
        if(flag){
            map.put("ifPraised","true");
        }else {
            map.put("ifPraised","false");
        }
        flag = false;
        for(String user : collectUsers){
            if(user.equals(user_id)){
                flag = true;
                break;
            }
        }
        if(flag){
            map.put("ifCollected","true");
        }else {
            map.put("ifCollected","false");
        }
    }
    /**
     * 评论资源
     */
    @RequestMapping(value="/jsp/resource/comment")
    @ResponseBody
    public Map commentResource(@RequestParam("resourceID") String resourceID,
                               @RequestParam("commentText") String commentText)
            throws ServiceException {
        int resource_id = Integer.parseInt(resourceID);

        int user_id = Integer.parseInt(request.getSession().getAttribute(Constants.USER_Id).toString());
        Map map = singleResourceService.commentResource(user_id,resource_id,commentText);
        return map;
    }

    /**
     * 点赞资源
     */
    @RequestMapping(value = "/jsp/resource/praise")
    @ResponseBody
    public Map<String,String> praiseResource(@RequestParam("resourceID") String resourceID)
            throws ServiceException {
        int user_id = Integer.parseInt(request.getSession().getAttribute(Constants.USER_Id).toString());
        int resource_id = Integer.parseInt(resourceID);
        Map map = singleResourceService.praiseResource(resource_id,user_id);
        UserActionHistoryEntity userActionHistoryEntity = (UserActionHistoryEntity) request.getAttribute("userActionHistory");
        userActionHistoryEntity.setAboutThemeId(resource_id);
        if(map.get("is_have_praised").equals("false")){
            actionCollectService.handleUserActionHistory(userActionHistoryEntity,1);
        }else{
            actionCollectService.handleUserActionHistory(userActionHistoryEntity,-1);
        }
        return map;
    }

    /**
     * 收藏资源
     */
    @RequestMapping(value = "/jsp/resource/collect")
    @ResponseBody
    public Map<String,String> collectResource(@RequestParam("resourceID") String resourceID)
            throws ServiceException{
        int user_id = Integer.parseInt(request.getSession().getAttribute(Constants.USER_Id).toString());
        int resource_id = Integer.parseInt(resourceID);
        Map map = singleResourceService.collectResource(resource_id,user_id);
        UserActionHistoryEntity userActionHistoryEntity = (UserActionHistoryEntity) request.getAttribute("userActionHistory");
        userActionHistoryEntity.setAboutThemeId(resource_id);
        if(map.get("is_have_collected").equals("false")){
            actionCollectService.handleUserActionHistory(userActionHistoryEntity,1);
        }else{
            actionCollectService.handleUserActionHistory(userActionHistoryEntity,-1);
        }
        return map;
    }

    /**
     * 转发资源
     */
    @RequestMapping(value = "/jsp/resource/forward")
    public Map<String,String> forwardResource(@RequestParam("resourceID") String resourceID)
            throws ServiceException{
        int user_id = Integer.parseInt(request.getSession().getAttribute(Constants.USER_Id).toString());
        int resource_id = Integer.parseInt(resourceID);
        Map map = singleResourceService.forwardResource(resource_id,user_id);
        /*UserActionHistoryEntity userActionHistoryEntity = (UserActionHistoryEntity) request.getAttribute("userActionHistory");
        userActionHistoryEntity.setAboutThemeId(resource_id);
        if(map.get("is_have_forward").equals("false")){
            actionCollectService.handleUserActionHistory(userActionHistoryEntity,1);
        }else{
            actionCollectService.handleUserActionHistory(userActionHistoryEntity,-1);
        }*/
        return map;
    }

    /**
     * 资源评分
     * @param resourceID
     * @param score
     * @return
     */
    @RequestMapping(value = "/jsp/resource/evaluate")
    @ResponseBody
    public Map<String,String> evaluate(@RequestParam("resourceID") String resourceID,
                                       @RequestParam("score") String score)
            throws ServiceException {
        int user_id = Integer.parseInt(request.getSession().getAttribute(Constants.USER_Id).toString());
        int resource_id = Integer.parseInt(resourceID);
        float resource_score = Float.parseFloat(score);
        Map map = singleResourceService.evaluateResource(user_id,resource_id,resource_score);
        return map;
    }
}