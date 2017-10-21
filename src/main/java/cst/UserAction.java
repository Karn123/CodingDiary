package cst;

/**
 * Created by 11022 on 2017/2/1.
 */
public enum
UserAction {
    NONE("无",false,false),
    COMMENT("评论",true,true),
    PRAISE("点赞",true,true),
    COLLECT("收藏",true,true),
    FORWARD("",true,true),
    FOLLOW("关注",true,true),
    POST("发表",true,true),
    DELETE("删除",true,true),
    BROWSE("浏览",false,true),
    LOGIN("登录",false,false),
    REGISTER("注册",false,false),
    OTHER("其他",false,false),
    BROWSEMYPAGE("浏览",true,false),
    FOLLOWANDUNFOLLOW("关注/取消关注",true,true),
    EVALUATE("评分",true,true),
    DOWNLOAD("下载",true,true),
    GETUNREADMSG("获取聊天消息",true,false),
    SAVECHATMSG("发送/保存消息",true,false)
    ;

    private boolean needLogIn;
    private boolean blocked;
    private String chinese;

    public boolean isBlocked() {
        return blocked;
    }

    UserAction(String chinese,boolean needLogIn, boolean blocked) {
        this.needLogIn = needLogIn;
        this.blocked = blocked;
        this.chinese = chinese;
    }

    public boolean isNeedLogIn() {
        return needLogIn;
    }

    public String getChinese() {
        return chinese;
    }
}
