package interceptor;

import cst.Authority;
import cst.Page;
import cst.UserAction;
import cst.UserActionAbout;
import framework.exceptions.AuthorityException;
import framework.exceptions.NotLogInException;
import model.UserActionHistoryEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import service.user.UserService;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 11022 on 2017/2/1.
 */
public class AuthorityInterceptor extends HandlerInterceptorAdapter {
    private final Logger log = LoggerFactory.getLogger(AuthorityInterceptor.class);

    @Resource
    protected UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(request.getRequestURI());
        String url = request.getRequestURI().substring(1);
        String[] urlSplit = url.split("/");
        UserAction userAction;
        UserActionAbout userActionAbout;
        UserActionHistoryEntity userActionHistoryEntity = new UserActionHistoryEntity();
        Page page = Page.INDEX;
        try {
            switch (urlSplit.length) {
                case 2:
                    page = Page.valueOf(urlSplit[1].toUpperCase());
                    userAction = page.getUserAction();
                    userActionAbout = page.getUserActionAbout();
                    break;
                case 3:
                    userAction = UserAction.valueOf(urlSplit[2].toUpperCase());
                    userActionAbout = UserActionAbout.valueOf(urlSplit[1].toUpperCase());
                    break;
                default:
                    userAction = UserAction.NONE;
                    userActionAbout = UserActionAbout.NONE;
                    break;
            }
        } catch (Exception e) {
            userAction = UserAction.OTHER;
            userActionAbout = UserActionAbout.NONE;
        }
        userActionHistoryEntity.setActionId(userAction.ordinal());
        userActionHistoryEntity.setActionAboutId(userActionAbout.ordinal());
        userActionHistoryEntity.setUserId(0);

        if (userAction.isNeedLogIn()) {
            try {
                int userID = userService.getUserID(request, userAction, page);
                userActionHistoryEntity.setUserId(userID);
                request.setAttribute("userActionHistory", userActionHistoryEntity);
                return true;
            } catch (NotLogInException e) {
                if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {//ajax特别
                    ServletOutputStream out = response.getOutputStream();
                    out.print("{\"msg\":\"notLogIn\"}");//返回给前端页面的未登陆标识
                    out.flush();
                    out.close();
                    return false;
                }
                request.setAttribute("exception", e);
                request.getRequestDispatcher(Page.ERROR.toString()).forward(request, response);
                return false;
            } catch (AuthorityException e) {
                if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {//ajax特别
                    ServletOutputStream out = response.getOutputStream();
                    out.print("{\"msg\":\"noAuthority\"}");//返回给前端页面的无权限标识
                    out.flush();
                    out.close();
                    return false;
                }
                request.setAttribute("exception", e);
                request.getRequestDispatcher(Page.ERROR.toString()).forward(request, response);
                return false;
            }
        }else{
            try{
                int userID = userService.getUserID(request, userAction, page);
                userActionHistoryEntity.setUserId(userID);
            }catch (Exception e){
                request.setAttribute("authority", Authority.NOTLOGIN);
            }
            request.setAttribute("userActionHistory", userActionHistoryEntity);
            return true;
        }
    }
}
