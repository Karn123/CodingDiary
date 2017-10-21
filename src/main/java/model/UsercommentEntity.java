package model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by Karn on 2017/2/14.
 */
@Entity
@Table(name = "usercomment", schema = "CodingDiaryDB", catalog = "")
public class UsercommentEntity {
    private int commentId;
    private int commenterId;
    private String commentContent;
    private Integer praiseNum;
    private Timestamp commentTime;
    private Collection<BlogCommentEntity> blogCommentsByCommentId;
    private Collection<ForumpostFloorCommentEntity> forumpostFloorCommentsByCommentId;
    private Collection<PraiseCommentEntity> praiseCommentsByCommentId;
    private Collection<ResourceCommentEntity> resourceCommentsByCommentId;
    private UserinfoEntity userinfoByCommenterId;

    public UsercommentEntity() {
    }

    public UsercommentEntity(int commenterId, String commentContent, Timestamp commentTime) {
        this.commenterId = commenterId;
        this.commentContent = commentContent;
        this.commentTime = commentTime;
    }

    @Id
    @Column(name = "commentID", nullable = false)
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    @Basic
    @Column(name = "commenterID", nullable = false)
    public int getCommenterId() {
        return commenterId;
    }

    public void setCommenterId(int commenterId) {
        this.commenterId = commenterId;
    }

    @Basic
    @Column(name = "commentContent", nullable = false, length = 2000)
    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    @Basic
    @Column(name = "praiseNum", nullable = true)
    public Integer getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }

    @Basic
    @Column(name = "commentTime", nullable = false)
    public Timestamp getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Timestamp commentTime) {
        this.commentTime = commentTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsercommentEntity that = (UsercommentEntity) o;

        if (commentId != that.commentId) return false;
        if (commenterId != that.commenterId) return false;
        if (commentContent != null ? !commentContent.equals(that.commentContent) : that.commentContent != null)
            return false;
        if (praiseNum != null ? !praiseNum.equals(that.praiseNum) : that.praiseNum != null) return false;
        if (commentTime != null ? !commentTime.equals(that.commentTime) : that.commentTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = commentId;
        result = 31 * result + commenterId;
        result = 31 * result + (commentContent != null ? commentContent.hashCode() : 0);
        result = 31 * result + (praiseNum != null ? praiseNum.hashCode() : 0);
        result = 31 * result + (commentTime != null ? commentTime.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "usercommentByCommentIdNum")
    public Collection<BlogCommentEntity> getBlogCommentsByCommentId() {
        return blogCommentsByCommentId;
    }

    public void setBlogCommentsByCommentId(Collection<BlogCommentEntity> blogCommentsByCommentId) {
        this.blogCommentsByCommentId = blogCommentsByCommentId;
    }

    @OneToMany(mappedBy = "usercommentByCommentIdNum")
    public Collection<ForumpostFloorCommentEntity> getForumpostFloorCommentsByCommentId() {
        return forumpostFloorCommentsByCommentId;
    }

    public void setForumpostFloorCommentsByCommentId(Collection<ForumpostFloorCommentEntity> forumpostFloorCommentsByCommentId) {
        this.forumpostFloorCommentsByCommentId = forumpostFloorCommentsByCommentId;
    }

    @OneToMany(mappedBy = "usercommentByPraiseCommentId")
    public Collection<PraiseCommentEntity> getPraiseCommentsByCommentId() {
        return praiseCommentsByCommentId;
    }

    public void setPraiseCommentsByCommentId(Collection<PraiseCommentEntity> praiseCommentsByCommentId) {
        this.praiseCommentsByCommentId = praiseCommentsByCommentId;
    }

    @OneToMany(mappedBy = "usercommentByCommentIdNum")
    public Collection<ResourceCommentEntity> getResourceCommentsByCommentId() {
        return resourceCommentsByCommentId;
    }

    public void setResourceCommentsByCommentId(Collection<ResourceCommentEntity> resourceCommentsByCommentId) {
        this.resourceCommentsByCommentId = resourceCommentsByCommentId;
    }

    @ManyToOne
    @JoinColumn(name = "commenterID", referencedColumnName = "userID", nullable = false)
    public UserinfoEntity getUserinfoByCommenterId() {
        return userinfoByCommenterId;
    }

    public void setUserinfoByCommenterId(UserinfoEntity userinfoByCommenterId) {
        this.userinfoByCommenterId = userinfoByCommenterId;
    }
}
