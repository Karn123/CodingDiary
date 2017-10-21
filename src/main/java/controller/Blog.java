package controller;

import common.searchType.SearchType;
import cst.*;
import framework.exceptions.AuthorityException;
import framework.exceptions.ServiceException;
import model.BlogEntity;
import model.UserActionHistoryEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.blogSystem.BlogService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

/**
 * Created by 叶润杰 on 2017/1/30.
 */
@Controller("Blog")
public class Blog extends BaseController {

    @Resource
    protected BlogService blogService;

    @RequestMapping(value = "/jsp/postBlog")
    public ModelAndView toPostBlog(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("postBlog");
        Authority authority = (Authority) request.getAttribute("authority");
        String ID = request.getParameter("blogID");
        if (ID != null) {
            int blogID = Integer.parseInt(ID);
            try {
                BlogEntity blogEntity = blogService.getBlogByID(blogID, authority);
                mv.addObject("blog",blogEntity);
                mv.addObject("htmlTitle", "修改博客");
            } catch (AuthorityException ae) {
                return toError(Message.NOAUTHORITY, Page.INDEX, ae.getMessage());
            }
        } else {
            mv.addObject("blog",new BlogEntity());
            mv.addObject("htmlTitle", "发表博客");
        }
        return mv;
    }

    @RequestMapping(value = "/jsp/blog/post", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> postBlog(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<String, Object>();
        int userID = Integer.parseInt(request.getSession().getAttribute(Constants.USER_Id).toString());
        int blogID=Integer.parseInt(request.getParameter("blog_id"));
        String blogTitle = request.getParameter("blog_title");
        String blogContent = request.getParameter("blog_content");
        Set<Integer> tagIDList = Tag.getTagIDList(request.getParameter("blog_tags"));
        int retCode=blogID;
        if(blogID==0){
            retCode = blogService.postBlog(userID, blogTitle, blogContent, tagIDList);
        }else{
            retCode = blogService.editBlog(blogID, blogTitle, blogContent, tagIDList);
        }
        result.put("retCode", retCode);
        return result;
    }

    @RequestMapping(value = {"/jsp/blogList"})
    public ModelAndView blogList(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        int userID = 0;
        Authority authority = (Authority) request.getAttribute("authority");
        String ID = (String) request.getParameter("userID");
        String type = request.getParameter("type"), typeValue = request.getParameter("type_value");
        mv.setViewName("blogList");
        if (ID != null) {
            userID = Integer.parseInt(ID);
        }
        Pagination pagination = new Pagination(request, Constants.blogListPageSize);
        List<BlogEntity> list = blogService.getBlogList(type, typeValue, userID, null, pagination, authority);
        mv.addObject("list", list);
        mv.addObject("type", type);
        mv.addObject("typeValue", typeValue);
        mv.addObject("pagination", pagination.updateBeginAndEndNumber());
        return mv;
    }

    @RequestMapping(value ={"/jsp/searchMyBlogs","/jsp/myBlog"})
    public ModelAndView searchMyBlogs() throws ServiceException {
        ModelAndView mv = new ModelAndView("myBlog");
        SearchType searchType = new SearchType(request);
        searchType.setTableName(Constants.TableName.BLOG);
        Pagination pagination = new Pagination(request,Constants.blogListPageSize);
        searchType.setSearchByUserId(Integer.parseInt(request.getSession().getAttribute(Constants.USER_Id).toString()));
        List<BlogEntity> blogs = searchService.search(searchType,pagination);
        mv.addObject("list",blogs);
        mv.addObject("pagination",pagination.updateBeginAndEndNumber());
        mv.addObject("searchType",searchType);
        return mv;
    }

    @RequestMapping(value = {"/jsp/searchOtherBlogs","/jsp/otherBlog"})
    public ModelAndView searchOtherBlogs() throws ServiceException {
        ModelAndView mv = new ModelAndView("otherBlog");
        SearchType searchType = new SearchType(request);
        searchType.setTableName(Constants.TableName.BLOG);
        Pagination pagination = new Pagination(request,Constants.blogListPageSize);
        List<BlogEntity> blogs = searchService.search(searchType,pagination);
        mv.addObject("list",blogs);
        mv.addObject("pagination",pagination.updateBeginAndEndNumber());
        mv.addObject("searchType",searchType);
        return mv;
    }

    @RequestMapping(value = "/jsp/singleBlog")
    public ModelAndView singleBlog(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        Authority authority = (Authority) request.getAttribute("authority");
        int blogID = Integer.parseInt(request.getParameter("blogID"));
        UserActionHistoryEntity userActionHistoryEntity = (UserActionHistoryEntity) request.getAttribute("userActionHistory");
        userActionHistoryEntity.setAboutThemeId(blogID);
        List<BlogEntity> recommendBlogs;
        if (userActionHistoryEntity.getUserId() == 0) {
            recommendBlogs = recommendService.getRecommendBlogs(4, 0, 0, 0, null).get("hottestBlog");
        } else {
            actionCollectService.handleUserActionHistory(userActionHistoryEntity, 0);
            recommendBlogs = recommendService.getRecommendBlogs(0, 0, 4, 0, (List<Integer>) request.getSession().getAttribute(Constants.USER_INTEREST)).get("hottestInterestingBlog");
        }
        try {
            BlogEntity blogEntity = blogService.getBlogByID(blogID, authority);
            mv.setViewName("singleBlog");
            mv.addAllObjects(blogService.getBlogStatue(blogID, userActionHistoryEntity.getUserId()));
            mv.addObject("blog", blogEntity);
            mv.addObject("recommendBlogs", recommendBlogs);
            return mv;
        } catch (AuthorityException ae) {
            return toError(Message.NOAUTHORITY, Page.INDEX, ae.getMessage());
        }
    }

    @RequestMapping(value = "/jsp/blog/comment", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> commentBlog(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<String, Object>();
        int userID = Integer.parseInt(request.getSession().getAttribute(Constants.USER_Id).toString());
        int blogID = Integer.parseInt(request.getParameter("blogId"));
        String commentContent = request.getParameter("content");
        int retCode = commentService.comment(userID, commentContent, blogID, BlogEntity.class);
        result.put("retCode", retCode);
        UserActionHistoryEntity userActionHistoryEntity = (UserActionHistoryEntity) request.getAttribute("userActionHistory");
        userActionHistoryEntity.setAboutThemeId(blogID);
        actionCollectService.handleUserActionHistory(userActionHistoryEntity, retCode);
        return result;
    }

    @RequestMapping(value = "/jsp/blog/praise", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> praiseBlog(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<String, Object>();
        int userID = Integer.parseInt(request.getSession().getAttribute(Constants.USER_Id).toString());
        int blogID = Integer.parseInt(request.getParameter("blogId"));
        UserActionHistoryEntity userActionHistoryEntity = (UserActionHistoryEntity) request.getAttribute("userActionHistory");
        userActionHistoryEntity.setAboutThemeId(blogID);
        int retCode = praiseService.praise(userID, blogID, "Blog");
        result.put("retCode", retCode);
        actionCollectService.handleUserActionHistory(userActionHistoryEntity, retCode);
        recommendService.updateValue(userActionHistoryEntity, retCode);
        return result;
    }

    @RequestMapping(value = "/jsp/blog/collect", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> collectBlog(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<String, Object>();
        int userID = Integer.parseInt(request.getSession().getAttribute(Constants.USER_Id).toString());
        int blogID = Integer.parseInt(request.getParameter("blogId"));
        UserActionHistoryEntity userActionHistoryEntity = (UserActionHistoryEntity) request.getAttribute("userActionHistory");
        userActionHistoryEntity.setAboutThemeId(blogID);
        int retCode = collectService.collect(userID, blogID, UserActionAbout.BLOG);
        result.put("retCode", retCode);
        actionCollectService.handleUserActionHistory(userActionHistoryEntity, retCode);
        recommendService.updateValue(userActionHistoryEntity, retCode);
        return result;
    }

    @RequestMapping(value = "/jsp/blog/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteBlog(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<String, Object>();
        int blogID = Integer.parseInt(request.getParameter("blogId"));
        UserActionHistoryEntity userActionHistoryEntity = (UserActionHistoryEntity) request.getAttribute("userActionHistory");
        userActionHistoryEntity.setAboutThemeId(blogID);
        int retCode = blogService.deleteBlog(blogID);
        result.put("retCode", retCode);
        actionCollectService.handleUserActionHistory(userActionHistoryEntity, retCode);
        return result;
    }

    @RequestMapping(value = "/jsp/image/upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadImage(@RequestParam("fileToUpload") MultipartFile file) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (!file.isEmpty()) {
            try {
                //System.out.println(file.getOriginalFilename());
                //相对路径
                String fileRelativePath = "/images/";
                // 文件保存路径
                String filePath = request.getSession().getServletContext().getRealPath(fileRelativePath);
                File f = new File(filePath);
                //若目录不存在，则创建
                if (!f.exists()) {
                    f.mkdirs();
                }
                System.out.println(filePath);
                // 转存文件
                String fileName = new Date().getTime() + file.getOriginalFilename();
                file.transferTo(new File(filePath, fileName));
                fileRelativePath += "/" + fileName;
                result.put("message", "uploadSuccess");
                result.put("file", fileRelativePath);
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        result.put("message", "uploadError");
        return result;
    }

    @RequestMapping(value = "/jsp/blogIndex")
    public ModelAndView blogIndex(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("blogIndex");
        modelAndView.addAllObjects(recommendService.getRecommendBlogs(3, 0, 3, 0, (List<Integer>) request.getSession().getAttribute(Constants.USER_INTEREST)));
        return modelAndView;
    }

    @RequestMapping(value = "/jsp/recommend")
    public ModelAndView recommend(HttpServletRequest request, HttpServletResponse response) {
        recommendService.updateTotalValue();
        recommendService.updateRecommend();
        recommendService.updateUserAction();
        recommendService.getUserInterest(1);
        recommendService.getRecommendForumposts(2, 2, 2, 2, (List<Integer>) request.getSession().getAttribute(Constants.USER_INTEREST));
        recommendService.getRecommendResources(2, 2, 2, 2, (List<Integer>) request.getSession().getAttribute(Constants.USER_INTEREST));
        return null;
    }
}
