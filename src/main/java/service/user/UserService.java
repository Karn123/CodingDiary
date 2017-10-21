package service.user;

import cst.Authority;
import cst.Page;
import cst.UserAction;
import framework.exceptions.AuthorityException;
import framework.exceptions.NotLogInException;
import service.common.ICommService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 11022 on 2017/1/31.
 */
public interface UserService extends ICommService {
    public Authority checkAuthority(int userID) throws AuthorityException;

    public int getUserID(HttpServletRequest request, UserAction userAction, Page page) throws NotLogInException, AuthorityException;
}
