package controller;

import cst.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.getPortrait.IGetPortraitService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by joy12 on 2017/2/1.
 * 获取头像地址
 */
@Controller
public class GetPortrait {
    @Autowired
    HttpServletRequest request;

    @Resource
    IGetPortraitService IGetPortraitService;

    @RequestMapping(value = "/jsp/GetPortrait/GetPortraitAction",method = RequestMethod.POST)
    @ResponseBody
    public Map getUserPortrait(){
        Map result = new HashMap();
      // 获取userID和相对路径
        //System.out.print("开始获取头像");
        Integer user_id = Integer.parseInt(request.getSession().getAttribute(Constants.USER_Id).toString());
        String portraitUrl = IGetPortraitService.getPortrait(user_id);
        result.put("portraitUrl",portraitUrl);
        return result;
    }
}
