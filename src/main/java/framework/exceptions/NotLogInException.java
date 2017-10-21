package framework.exceptions;

import cst.Message;
import cst.Page;

/**
 * Created by 11022 on 2017/2/1.
 */
public class NotLogInException extends BaseException {
    public NotLogInException(String message) {
        super(message);
    }

    public NotLogInException() {
        super(Message.NOTLOGIN, Page.USERLOGIN, Message.NOTLOGINDETAIL);
    }
}
