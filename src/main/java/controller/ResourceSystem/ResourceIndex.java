package controller.ResourceSystem;

import com.sun.org.apache.xpath.internal.operations.Mod;
import framework.exceptions.ServiceException;
import model.ResourceEntity;
import model.ResourceTagEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.resourceSystem.loadResource.ILoadResourceService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Karn on 2017/2/21.
 */
@Controller
public class ResourceIndex {

    @Resource
    private ILoadResourceService iLoadResourceService;

    @RequestMapping(value = "/jsp/resourceIndex")
    public ModelAndView getHottestResources(){
        ModelAndView mv = new ModelAndView();
        List<ResourceEntity> resourceEntityList = new ArrayList<ResourceEntity>();
        try {
             resourceEntityList = iLoadResourceService.loadHottestResources();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        
        mv.setViewName("resourceIndex");
        mv.addObject("resourceEntities",resourceEntityList);
        return mv;
    }
}
