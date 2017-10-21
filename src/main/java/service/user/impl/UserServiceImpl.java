package service.user.impl;

import cst.Authority;
import cst.Constants;
import cst.Page;
import cst.UserAction;
import daos.UserinfoDAO;
import framework.exceptions.AuthorityException;
import framework.exceptions.NotLogInException;
import model.UserinfoEntity;
import org.springframework.stereotype.Service;
import service.common.impl.CommServiceImpl;
import service.user.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by 11022 on 2017/1/31.
 */
@Service(value = "userService")
public class UserServiceImpl extends CommServiceImpl implements UserService {
    @Resource
    protected UserinfoDAO userinfoDAO;

    @Override
    public Authority checkAuthority(int userID) throws AuthorityException {
        UserinfoEntity userinfoEntity = userinfoDAO.findById(userID);
        if (userinfoEntity.getTfByIsGotBlocked().getTfOption().equals("T")) {
            return Authority.BLOCKED;
        }
        if (userinfoEntity.getAdministratorsByUserId().size() != 0) {
            return Authority.ADMIN;
        }
        return Authority.NORMAL;
    }

    @Override
    public int getUserID(HttpServletRequest request, UserAction userAction, Page page) throws NotLogInException,AuthorityException {
        Object object = request.getSession().getAttribute(Constants.USER_Id);
        if (object == null) {
            throw new NotLogInException();
        }
        int userID=Integer.parseInt(object.toString());
        Authority authority = checkAuthority(userID);
        switch (authority) {
            case BLOCKED:
                if(userAction.isBlocked()){
                    throw new AuthorityException(page,userAction);
                }
            default:
                request.setAttribute("authority",authority);
                return userID;
        }
    }



}
