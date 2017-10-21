package controller;

import common.searchType.SearchType;
import cst.Authority;
import cst.Constants;
import cst.Message;
import cst.Page;
import framework.exceptions.AuthorityException;
import framework.exceptions.ServiceException;
import model.ForumpostEntity;
import model.ForumpostFloorEntity;
import model.UserActionHistoryEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.forumpostSystem.ForumpostService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by 佳乐 on 2017/2/13.
 */
@Controller("Forumpost")
public class Forumpost extends BaseController{

    @Resource
    protected ForumpostService forumpostService;

    @RequestMapping(value="/jsp/postForumpost")
    public String toPostForumpost(){
        return  Page.POSTFORUMPOST.toString();
    }

    @RequestMapping(value="/jsp/**/singleForumpost")
    public String toSingleForumpost(){
        return "singleForumpost";
    }

    @RequestMapping(value = "/jsp/forumpost/post", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> postForumpost(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<String, Object>();
        int userID = Integer.parseInt(request.getSession().getAttribute(Constants.USER_Id).toString());
        String forumpostTitle = request.getParameter("forumpost_title");

        String forumpostContent = request.getParameter("forumpost_content");
        Set<Integer> tagIDList = Tag.getTagIDList(request.getParameter("forumpost_tags"));

        int retCode = forumpostService.postForumpost(userID,forumpostTitle,forumpostContent, tagIDList);
        result.put("retCode", retCode);
        return result;
    }

    @RequestMapping(value = {"/jsp/forumpostList", "jsp/myForumpost","/jsp/searchMyForumposts"})
    public ModelAndView forumpostList() throws ServiceException {
        ModelAndView mv = new ModelAndView("myForumpost");
        SearchType searchType = new SearchType(request);
        searchType.setSearchByUserId(Integer.parseInt(request.getSession().getAttribute(Constants.USER_Id).toString()));
        searchType.setTableName(Constants.TableName.FORUMPOST);
        Pagination pagination = new Pagination(request,Constants.forumpostListPageSize);
        List<ForumpostEntity> forumposts = searchService.search(searchType,pagination);
        mv.addObject("list",forumposts);
        mv.addObject("pagination",pagination.updateBeginAndEndNumber());
        mv.addObject("searchType",searchType);
        return mv;
    }

    @RequestMapping(value = "/jsp/bbsIndex")
    public ModelAndView bbsIndex() {
        ModelAndView mv = new ModelAndView();
        List<ForumpostEntity> bbsList = forumpostService.getBbsIndex();
        mv.setViewName("bbsIndex");
        mv.addObject("bbsList", bbsList);
        mv.addAllObjects(recommendService.getRecommendForumposts(3, 0, 0, 0, null));
        return mv;
    }

    @RequestMapping(value = {"/jsp/otherForumpost","/jsp/searchOtherForumposts"})
    public ModelAndView searchOtherForumposts() throws ServiceException {
        ModelAndView mv = new ModelAndView("otherForumpost");
        SearchType searchType = new SearchType(request);
        searchType.setTableName(Constants.TableName.FORUMPOST);
        Pagination pagination = new Pagination(request,Constants.forumpostListPageSize);
        List<ForumpostEntity> forumposts = searchService.search(searchType,pagination);
        mv.addObject("list",forumposts);
        mv.addObject("pagination",pagination.updateBeginAndEndNumber());
        mv.addObject("searchType",searchType);
        return mv;
    }

    @RequestMapping(value = "/jsp/singleForumpost")
    public ModelAndView singleForumpost(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        Authority authority = (Authority) request.getAttribute("authority");
        int forumpostID = Integer.parseInt(request.getParameter("forumpostID"));
        try {
            ForumpostEntity forumpostEntity=forumpostService.getForumpostByID(forumpostID, authority);
            List<ForumpostFloorEntity> forumpostFloorEntityList=forumpostService.getForumpostFloorList(forumpostEntity.getForumpostId());
            mv.setViewName("singleForumpost");
            mv.addObject("forumpost", forumpostEntity);
            mv.addObject("floorList",forumpostFloorEntityList);
          //  System.out.println(forumpostEntity.getForumpostCommentsByPostId().size());
            UserActionHistoryEntity userActionHistoryEntity = (UserActionHistoryEntity) request.getAttribute("userActionHistory");
            userActionHistoryEntity.setAboutThemeId(forumpostID);
            List<ForumpostEntity> recommendForumposts;
            if (userActionHistoryEntity.getUserId() == 0) {
                recommendForumposts = recommendService.getRecommendForumposts(4, 0, 0, 0, null).get("hottestForumpost");
            } else {
                actionCollectService.handleUserActionHistory(userActionHistoryEntity, 0);
                recommendForumposts = recommendService.getRecommendForumposts(0, 0, 4, 0, (List<Integer>) request.getSession().getAttribute(Constants.USER_INTEREST)).get("hottestInterestingForumpost");
            }
            mv.addObject("recommendForumposts", recommendForumposts);
            return mv;
        } catch (AuthorityException ae) {
            return toError(Message.NOAUTHORITY, Page.INDEX, ae.getMessage());
        }
    }

    @RequestMapping(value = "/jsp/forumpost/comment", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> commentForumpost(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<String, Object>();
        int userID = Integer.parseInt(request.getSession().getAttribute(Constants.USER_Id).toString());
        int forumpostID = Integer.parseInt(request.getParameter("forumpostId"));
        String commentContent = request.getParameter("content");
        int retCode = forumpostService.comment(userID,forumpostID, commentContent);
        result.put("retCode", retCode);
        return result;
    }

    @RequestMapping(value = "/jsp/forumpost/praise", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> praiseForumpost(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<String, Object>();
        int userID = Integer.parseInt(request.getSession().getAttribute(Constants.USER_Id).toString());
        int floorID = Integer.parseInt(request.getParameter("floorId"));
        int retCode = forumpostService.praise(userID,floorID);
        UserActionHistoryEntity userActionHistoryEntity = (UserActionHistoryEntity) request.getAttribute("userActionHistory");
        userActionHistoryEntity.setAboutThemeId(floorID);
        result.put("retCode", retCode);
        return result;
    }

    @RequestMapping(value = "/jsp/forumpost/collect", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> collectForumpost(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<String, Object>();
        int userID = Integer.parseInt(request.getSession().getAttribute(Constants.USER_Id).toString());
        int forumpostId = Integer.parseInt(request.getParameter("forumpostId"));
        int retCode = forumpostService.collect(userID,forumpostId);
        result.put("retCode", String.valueOf(retCode));
        System.out.println(result.get(retCode));
        return result;
    }
}
