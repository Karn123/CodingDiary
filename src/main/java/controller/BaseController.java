package controller;

import cst.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import service.actionCollectSystem.ActionCollectService;
import service.collectSystem.CollectService;
import service.commentSystem.CommentService;
import service.praiseSystem.PraiseService;
import service.recommendSystem.RecommendService;
import service.searchSystem.ISearchService;
import service.user.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by 11022 on 2017/2/1.
 */
public abstract class BaseController {

    @Autowired
    protected HttpServletRequest request;

    @Resource
    protected UserService userService;

    @Resource
    protected CommentService commentService;

    @Resource
    protected PraiseService praiseService;

    @Resource
    protected CollectService collectService;

    @Resource
    protected ActionCollectService actionCollectService;

    @Resource
    protected RecommendService recommendService;

    @Resource
    protected ISearchService searchService;

    protected ModelAndView toError(String msg, Page page, String detailMessage){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error");
        mv.addObject("msg", msg);
        mv.addObject("option",page.toString());
        mv.addObject("optionMessage",page.getPageMessage());
        mv.addObject("detailMessage", detailMessage);
        return mv;
    }
}

