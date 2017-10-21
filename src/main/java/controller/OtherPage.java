package controller;

import com.sun.javafx.sg.prism.NGShape;
import cst.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.OtherPage.OtherPageService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by joy12 on 2017/2/12.
 */
@Controller
public class OtherPage {

    @Autowired
    private HttpServletRequest request;

    @Resource
    OtherPageService otherPageService;
    private Integer user_id;
    private Integer this_id;

    //只有跳转到OtherPage页面时，地址栏带有参数this_id,其余TA的系列页面，该属性从session中取
    @RequestMapping(value = "/jsp/otherPage")
    public ModelAndView initInfo() {
        ModelAndView result = new ModelAndView();
        //System.out.println("start init info");
        result.setViewName("otherPage");
        if (request.getSession().getAttribute(Constants.USER_Id) == null) {
            user_id = -1;
        } else {
            user_id = Integer.parseInt(request.getSession().getAttribute(Constants.USER_Id).toString());
        }
        this_id = Integer.parseInt(request.getParameter("this_id"));
        result.addObject("this_user_id", this_id);
        if (this_id == user_id) {
            result.setViewName("redirect:/jsp/myPage");
            return result;
        }
        //把当前联系人的id和头像存入session
        Map<String, Object> infos = otherPageService.getInfo(user_id, this_id);
        String this_portrait_url = infos.get("this_portrait").toString();
        request.getSession().setAttribute("this_id", this_id);
        System.out.println(this_id + "的头像: " + this_portrait_url);
        request.getSession().setAttribute("this_portrait", this_portrait_url);

        result.addAllObjects(infos);

        String timestamp = String.valueOf(new Date().getTime());
        String appKey = "b11b1d3348715d6f90630999";
        String randomStr = "022cd9fd995849b58b3ef0e943421ed9";
        String masterSecret = "7728fa28ea5706a76fbad57a";

        result.addObject("appKey", appKey);
        result.addObject("randomStr", randomStr);
        result.addObject("timestamp",timestamp);
        result.addObject("masterSecret", masterSecret);

        return result;
    }

    @RequestMapping(value = "/jsp/otherPage/followAndUnfollow",method = RequestMethod.POST)
    @ResponseBody
    public Map followAndUnfollow(){
        System.out.println("start follow/unfollow");
        String action = request.getParameter("action");
        Map result = new HashMap();
        if (action.equals("follow")){
            otherPageService.follow(user_id,this_id);
            result.put("retCode","0001");
        }else if(action.equals("unfollow")){
            otherPageService.unfollow(user_id,this_id);
            result.put("retCode","0000");
        }
        return result;
    }
}
