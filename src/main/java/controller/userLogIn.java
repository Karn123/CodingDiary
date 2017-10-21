package controller;

import cst.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.userLogIn.IUserLogInService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 佳乐 on 2017/1/22.
 */
@Controller
public class userLogIn extends BaseController {

    @Resource
    private IUserLogInService userLogInService;

    @RequestMapping(value = "/jsp/userLogIn")
    public String toUserLogIn(HttpServletRequest request, HttpServletResponse response) {
        return "userLogIn";
    }

    @RequestMapping(value = "/jsp/user/logIn", method = RequestMethod.POST)
    @ResponseBody
    public Map userLogIn(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map result = new HashMap();
        String login_account = request.getParameter("login_account");
        String login_password = request.getParameter("login_password");
        int login_result = userLogInService.login(login_account, login_password);
        if (login_result > 0) {
            HttpSession session = request.getSession();
            session.setAttribute(Constants.USER_Id, login_result);
            session.setAttribute(Constants.USER_HEADPORTRAIT, userLogInService.getUserHeadPortrait(login_result));
            session.setAttribute(Constants.USER_INTEREST, recommendService.getUserInterest(login_result));
        }
        result.put("returnCode", String.valueOf(login_result));
        return result;
    }

    @RequestMapping(value="/jsp/userLogOut")
    public String userLogOut(){
        request.getSession().setAttribute(Constants.USER_Id,null);
        return "index";
    }
}
