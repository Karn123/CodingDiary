package model;

import javax.persistence.*;

/**
 * Created by Karn on 2017/2/14.
 */
@Entity
@Table(name = "blog_forward", schema = "CodingDiaryDB", catalog = "")
@IdClass(BlogForwardEntityPK.class)
public class BlogForwardEntity {
    private int forwardBlogId;
    private int forwarderId;
    private String forwardMsg;
    private BlogEntity blogByForwardBlogId;
    private UserinfoEntity userinfoByForwarderId;

    @Id
    @Column(name = "forwardBlogID", nullable = false)
    public int getForwardBlogId() {
        return forwardBlogId;
    }

    public void setForwardBlogId(int forwardBlogId) {
        this.forwardBlogId = forwardBlogId;
    }

    @Id
    @Column(name = "forwarderID", nullable = false)
    public int getForwarderId() {
        return forwarderId;
    }

    public void setForwarderId(int forwarderId) {
        this.forwarderId = forwarderId;
    }

    @Basic
    @Column(name = "forwardMsg", nullable = true, length = 1000)
    public String getForwardMsg() {
        return forwardMsg;
    }

    public void setForwardMsg(String forwardMsg) {
        this.forwardMsg = forwardMsg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlogForwardEntity that = (BlogForwardEntity) o;

        if (forwardBlogId != that.forwardBlogId) return false;
        if (forwarderId != that.forwarderId) return false;
        if (forwardMsg != null ? !forwardMsg.equals(that.forwardMsg) : that.forwardMsg != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = forwardBlogId;
        result = 31 * result + forwarderId;
        result = 31 * result + (forwardMsg != null ? forwardMsg.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "forwardBlogID", referencedColumnName = "blogID", nullable = false)
    public BlogEntity getBlogByForwardBlogId() {
        return blogByForwardBlogId;
    }

    public void setBlogByForwardBlogId(BlogEntity blogByForwardBlogId) {
        this.blogByForwardBlogId = blogByForwardBlogId;
    }

    @ManyToOne
    @JoinColumn(name = "forwarderID", referencedColumnName = "userID", nullable = false)
    public UserinfoEntity getUserinfoByForwarderId() {
        return userinfoByForwarderId;
    }

    public void setUserinfoByForwarderId(UserinfoEntity userinfoByForwarderId) {
        this.userinfoByForwarderId = userinfoByForwarderId;
    }
}
