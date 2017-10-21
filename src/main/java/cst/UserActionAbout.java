package cst;

/**
 * Created by 11022 on 2017/2/13.
 */
public enum UserActionAbout {
    NONE("None"),
    BLOG("Blog"),
    RESOURCE("Resource"),
    FORUMPOST("Forumpost"),
    LEGALITY("Legality"),
    USERCOMMENT("Comment"),
    OTHERPAGE("OtherPage");
    private String name;

    private UserActionAbout(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
