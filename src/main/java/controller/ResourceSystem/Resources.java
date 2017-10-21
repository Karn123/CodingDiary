package controller.ResourceSystem;

import cst.Constants;
import framework.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.resourceSystem.loadResource.ILoadResourceService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Karn on 2017/2/2.
 */
@Controller
public class Resources {

    @Autowired
    private HttpServletRequest request;

    @Resource
    private ILoadResourceService loadResourceService;

    @RequestMapping(value="/jsp/myResource")
    public String toMyResource(){
        return "myResource";
    }

    @RequestMapping(value="/jsp/otherResource")
    public ModelAndView toPersonalResource(){
        ModelAndView mv = new ModelAndView("otherResource");
        mv.addObject("this_id",request.getParameter("this_id"));
        return mv;
    }

    @RequestMapping(value={"/jsp/resource/loadResources/{pageIndex}"})
    @ResponseBody
    public List<Map<String,String>> loadResources(@PathVariable String pageIndex,
                                                    @RequestParam("userId") String userID,
                                                    @RequestParam("pageSize") String pageSize,
                                                    @RequestParam("searchContent") String searchContent,
                                                    @RequestParam("selectDate") String selectDate,
                                                    @RequestParam("pageTotal") String pageTotal) {
        int user_id = Integer.parseInt(userID);
        int page_size = Integer.parseInt(pageSize);
        int page_total = Integer.parseInt(pageTotal);
        int offset = (Integer.parseInt(pageIndex)-1)*page_size;
        List<Map<String,String>> resourceList;
        try {
             resourceList = loadResourceService.loadResources(user_id,page_total,searchContent,offset,page_size,selectDate);
             return resourceList;
        } catch (ServiceException e) {
            e.printStackTrace();
            return null;
        }
    }
}