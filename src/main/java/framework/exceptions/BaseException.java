package framework.exceptions;

import cst.Page;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 11022 on 2017/2/1.
 */
public class BaseException extends Exception {
    private Page page;
    private String detailMessage;
    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Page page, String detailMessage) {
        super(message);
        this.page = page;
        this.detailMessage = detailMessage;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public void setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
    }

    public Map<String, Object> toMap(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("retCode", 0);
        map.put("msg",getMessage());
        return map;
    }
}
