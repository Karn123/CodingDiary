package controller;

import cst.Constants;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.userInfo.UserInfoService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by joy12 on 2017/2/6.
 * 获取和修改用户信息
 */
@Controller
public class UserInfo {
    @Resource
    UserInfoService userInfoService;

    @RequestMapping(value = "/jsp/myPage")
    @ResponseBody
    public ModelAndView getInfo(HttpServletRequest request, HttpServletResponse response){
        Integer id = Integer.parseInt(request.getSession().getAttribute(Constants.USER_Id).toString());;
        //若数据库中没有此ID，则return为null
        ModelAndView result = new ModelAndView();
        result.setViewName("myPage");
        Map<String,Object> myinfo = userInfoService.getUserInfo(id);
        if (myinfo != null)
            result.addAllObjects(myinfo);
        return result;
    }

    /*
    * return 0:成功
    * return 1:info传递失败
    * */
    @RequestMapping(value = "/jsp/userInfo/saveUserInfoAction",method = RequestMethod.POST)
    @ResponseBody
    public Map saveInfo(HttpServletRequest request, HttpServletResponse response){
        Map result = new HashMap();
        Integer id = Integer.parseInt(request.getSession().getAttribute(Constants.USER_Id).toString());
        Map<String,String> info = new HashedMap();
        Enumeration<String> keys = request.getParameterNames();
        while (keys.hasMoreElements()){
            String key = keys.nextElement();
            info.put(key,request.getParameter(key));
        }
        int ret = userInfoService.saveUserInfo(id,info);
        result.put("returnCode",ret);
        return result;
    }

    @RequestMapping(value = "/jsp/studentCertification")
    public String toStudentCertification(HttpServletRequest request, HttpServletResponse response) {
        return "studentCertification";
    }


    @RequestMapping(value="/jsp/userinfo/sendVerification",method = RequestMethod.POST)
    @ResponseBody
    public Map getVerificationByEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("sendVerification");
        String mail_addr = request.getParameter("mail_addr");
        Random random = new Random();
        String verificationCode = "";
        for (int i = 0; i < 6; i++)
            verificationCode = verificationCode + (random.nextInt(10)) + "";
        try {
            new Register().createMail(mail_addr, verificationCode);
        } catch (Exception x) {
            System.out.println(x);
        }
        System.out.println(verificationCode);
        Map result = new HashMap();
        result.put("verification", verificationCode);
        return result;
    }

    @RequestMapping(value = "/jsp/userinfo/certified", method = RequestMethod.POST)
    @ResponseBody
    public Map certified(HttpServletRequest request,HttpServletResponse response){
        Map<String, Object> result = new HashMap<String, Object>();
        Integer userId = Integer.parseInt(request.getSession().getAttribute(Constants.USER_Id).toString());
        userInfoService.setUserCertified(userId);
        result.put("msg","认证成功");
        return result;
    }
}
