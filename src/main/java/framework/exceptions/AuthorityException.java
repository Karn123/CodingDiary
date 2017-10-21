package framework.exceptions;

import cst.Message;
import cst.Page;
import cst.UserAction;

/**
 * Created by 11022 on 2017/1/31.
 */
public class AuthorityException extends BaseException {

    public AuthorityException() {
        super("没有权限");
    }

    public AuthorityException(String msg) {
        super(msg);
    }

    public AuthorityException(UserAction userAction){
        super("没有" + userAction.getChinese() + "的权限");
    }

    public AuthorityException(String message, Page page, String detailMessage) {
        super(message, page, detailMessage);
    }

    public AuthorityException(Page page, UserAction userAction) {
        super(Message.NOAUTHORITY, page, "你没有" + userAction.getChinese() + "的权限");
    }
}
