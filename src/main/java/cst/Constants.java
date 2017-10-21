package cst;

/**
 * Created by Karn on 2017/1/27.
 */
public class Constants {
    // HttpSession中存放用户登陆信息的key
    public static final String USER_Id = "userID";
    public static final String USER_HEADPORTRAIT = "userHeadPortrait";
    public static final String USER_INTEREST = "userInterest";
    //发表时每一次的标签个数
    public static final int tagPageSize = 4;
    //单个博客页面一页评论的个数
    public static final int blogCommentPageSize = 100;

    //单个资源页面一页评论的个数
    public static final int resourceCommentPageSize = 10;
    public static final int forumpostListPageSize=5;
    public static final int blogListPageSize = 5;
    //缩略字数
    public static final int abbreviatedWordsNumber = 50;

    public static final int paginationNumber = 5;

    public static final String defaultUserHeadPortrait="images/users/user-blog-900x900.jpg";

    public class TableName {
        public final static String BLOG = "Blog";
        public final static String FORUMPOST = "Forumpost";
        public final static String RESOURCE = "Resource";
        public final static String USERCOMMENT = "Comment";
    }

    public class TFAttribute{
        public final static int T = 1;
        public final static int F = 0;
        public final static int BLOCKED = 2;
        public final static int DELETED = 3;
    }
}
