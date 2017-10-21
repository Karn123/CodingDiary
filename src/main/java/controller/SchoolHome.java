package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by 86761 on 2017/2/12.
 */
@Controller("SchoolHome")
public class SchoolHome extends BaseController{
    @RequestMapping(value="/jsp/schoolHome")
    public ModelAndView toSchoolHome(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("schoolHome");
        mv.addAllObjects(recommendService.getRecommendBlogs(6,0,0,0,null));
        mv.addAllObjects(recommendService.getRecommendForumposts(6,0,0,0,null));
        mv.addAllObjects(recommendService.getRecommendResources(6,0,0,0,null));
        return mv;
    }
}
