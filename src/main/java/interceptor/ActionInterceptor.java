package interceptor;

import cst.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import service.recommendSystem.RecommendService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 11022 on 2017/2/23.
 */
public class ActionInterceptor extends HandlerInterceptorAdapter {
    private final Logger log = LoggerFactory.getLogger(ActionInterceptor.class);

    @Resource
    protected RecommendService recommendService;


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        try{
            String[] strings = modelAndView.getViewName().toUpperCase().split("/");
            switch (strings.length) {
                case 1:
                    switch (Page.valueOf(strings[0])) {
                        case SINGLEBLOG:

                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }

        }catch (Exception e){

        }
    }
}
