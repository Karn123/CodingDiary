package cst;

/**
 * Created by 11022 on 2017/2/1.
 */
public enum Page {
    USERLOGIN("登录","userLogIn","去登录",UserAction.BROWSE,UserActionAbout.BLOG),
    INDEX("主页","index","去主页",UserAction.BROWSE,UserActionAbout.NONE),
    POSTBLOG("发表博客","postBlog","去发表博客",UserAction.POST,UserActionAbout.BLOG),
    SINGLEBLOG("浏览博客","singleBlog","浏览单篇博客",UserAction.BROWSE,UserActionAbout.BLOG),
    ERROR("","error","",UserAction.NONE,UserActionAbout.NONE),
    REGISTER("注册","register","去注册",UserAction.REGISTER,UserActionAbout.NONE),
    MYBLOG("我的博客","myBlog","去我的博客",UserAction.BROWSEMYPAGE,UserActionAbout.BLOG),
    BLOGLIST("博客列表","blogList","去博客列表",UserAction.BROWSE,UserActionAbout.BLOG),
    POSTFORUMPOST("发表帖子","postForumpost","去发表帖子" ,UserAction.POST,UserActionAbout.FORUMPOST),
    SINGLEFORUMPOST("浏览帖子","singleForumpost","浏览单条帖子",UserAction.BROWSE,UserActionAbout.FORUMPOST),
    UPLOADFILE("上传资源","uploadFile","去上传资源",UserAction.POST,UserActionAbout.RESOURCE),
    MYPAGE("我的主页","myPage","去我的主页",UserAction.BROWSEMYPAGE,UserActionAbout.NONE),
    MYRESOURCE("我的资源","myResource","浏览我的资源",UserAction.BROWSEMYPAGE,UserActionAbout.RESOURCE),
    SINGLERESOURCE("浏览资源","singleResource","浏览单个资源",UserAction.BROWSE,UserActionAbout.RESOURCE),
    OTHERPAGE("TA的主页","otherPage","浏览TA的主页",UserAction.BROWSE,UserActionAbout.NONE)
    ;
    private String pageName;
    private String pageAddress;
    private String pageMessage;
    private UserAction userAction;
    private UserActionAbout userActionAbout;

    Page(String pageName, String pageAddress, String pageMessage, UserAction userAction, UserActionAbout userActionAbout) {
        this.pageName = pageName;
        this.pageAddress = pageAddress;
        this.pageMessage = pageMessage;
        this.userAction = userAction;
        this.userActionAbout = userActionAbout;
    }

    public String getPageName() {
        return pageName;
    }

    public String getPageAddress() {
        return pageAddress;
    }

    public String getPageMessage() {
        return pageMessage;
    }

    public UserAction getUserAction() {
        return userAction;
    }

    public UserActionAbout getUserActionAbout() {
        return userActionAbout;
    }

    @Override
    public String toString() {
        return pageAddress;
    }
}
