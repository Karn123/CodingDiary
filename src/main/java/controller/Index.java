package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * Created by Karn on 2016/11/29.
 */
@Controller("Index")
public class Index extends BaseController {
    @RequestMapping(value="/jsp/index")
    public ModelAndView toIndex(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        mv.addAllObjects(recommendService.getRecommendBlogs(6,0,0,0,null));
        mv.addAllObjects(recommendService.getRecommendForumposts(6,0,0,0,null));
        mv.addAllObjects(recommendService.getRecommendResources(6,0,0,0,null));
        return mv;
    }
    @RequestMapping(value="/")
    public ModelAndView toIndex1(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/jsp/index");
        return mv;
    }
    @RequestMapping(value = "/jsp/**/error")
    public String toError() {
        return "error";
    }
}