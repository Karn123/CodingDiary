package controller;

import cst.Constants;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.getFollowerAndFollowing.FollowDataService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by joy12 on 2017/3/12.
 */
@Controller
public class FollowersAndFollowings {
    @Autowired
    private HttpServletRequest request;
    @Resource
    FollowDataService followDataService;

    @RequestMapping(value = "/jsp/myFollower")
    public String toMyFollower(){
        return "myFollower";
    }

    @RequestMapping(value = "/jsp/myFollowing")
    public String toMyFollowing() {
        return "myFollowing";
    }

    @RequestMapping(value = "/jsp/otherFollower")
    public String toOtherFollower(){
        return "otherFollower";
    }

    @RequestMapping(value = "/jsp/otherFollowing")
    public String toOtherFollowing(){
        return "otherFollowing";
    }

    @RequestMapping(value="/jsp/getFollowInfo",method = RequestMethod.POST)
    public void getUserFollowInfo (HttpServletResponse response){
        String action = request.getParameter("action");
        String user = request.getParameter("user");
        Integer id = -1;
        if (user.equals("me")){
            id = Integer.parseInt(request.getSession().getAttribute(Constants.USER_Id).toString());
        }else if (user.equals("other")){
            id = Integer.parseInt(request.getSession().getAttribute("this_id").toString());
        }
        //System.out.println("Get id::" + id);

        //得到id、昵称和头像
        JSONArray jsonArray = JSONArray.fromObject(followDataService.getFollowInfos(id,action));
        System.out.println("json::"+jsonArray.toString());
        try {
            response.getWriter().write(jsonArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
