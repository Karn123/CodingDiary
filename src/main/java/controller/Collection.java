package controller;

import cst.Constants;
import model.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.collectSystem.CollectService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by 86761 on 2017/3/9.
 */
@Controller
public class Collection {

    @Resource
    protected CollectService collectService;


    @RequestMapping("jsp/collectedPost")
    public ModelAndView collectedPostList(HttpServletRequest request,HttpServletResponse response){
        ModelAndView mv = new ModelAndView();
        int userID = 0;
        String type = request.getParameter("type"), typeValue = request.getParameter("type_value");
            userID = Integer.parseInt(request.getSession().getAttribute(Constants.USER_Id).toString().trim());
            mv.setViewName("collectedPost");
        Pagination pagination = new Pagination(request, Constants.forumpostListPageSize);
        List<ForumpostEntity> list = collectService.getForumpostCollectList(type, typeValue, userID, null, pagination);
        System.out.println(list.size());
        mv.addObject("list", list);
        mv.addObject("type", type);
        mv.addObject("typeValue", typeValue);
        mv.addObject("pagination", pagination);
        return mv;
    }

    @RequestMapping("jsp/collectedBlog")
    public ModelAndView collectedBlogList(HttpServletRequest request,HttpServletResponse response){
        ModelAndView mv = new ModelAndView();
        int userID = 0;
        String type = request.getParameter("type"), typeValue = request.getParameter("type_value");
        userID = Integer.parseInt(request.getSession().getAttribute(Constants.USER_Id).toString().trim());
        mv.setViewName("collectedBlog");
        Pagination pagination = new Pagination(request, Constants.forumpostListPageSize);
        List<BlogEntity> list = collectService.getBlogCollectList(type, typeValue, userID, null, pagination);
        System.out.println(list.size());
        mv.addObject("list", list);
        mv.addObject("type", type);
        mv.addObject("typeValue", typeValue);
        mv.addObject("pagination", pagination);
        return mv;
    }

    @RequestMapping("jsp/collectedResource")
    public ModelAndView collectedResourceList(HttpServletRequest request,HttpServletResponse response){
        ModelAndView mv = new ModelAndView();
        int userID = 0;
        String type = request.getParameter("type"), typeValue = request.getParameter("type_value");
        userID = Integer.parseInt(request.getSession().getAttribute(Constants.USER_Id).toString().trim());
        mv.setViewName("collectedResource");
        Pagination pagination = new Pagination(request, Constants.forumpostListPageSize);
        List<ResourceEntity> list = collectService.getResourceCollectList(type, typeValue, userID, null, pagination);
        System.out.println(list.size());
        mv.addObject("list", list);
        mv.addObject("type", type);
        mv.addObject("typeValue", typeValue);
        mv.addObject("pagination", pagination);
        return mv;
    }

}

