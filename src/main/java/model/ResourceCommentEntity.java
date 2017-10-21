package model;

import javax.persistence.*;

/**
 * Created by Karn on 2017/2/14.
 */
@Entity
@Table(name = "resource_comment", schema = "CodingDiaryDB", catalog = "")
@IdClass(ResourceCommentEntityPK.class)
public class ResourceCommentEntity {
    private int commentResourceId;
    private int commentIdNum;
    private ResourceEntity resourceByCommentResourceId;
    private UsercommentEntity usercommentByCommentIdNum;

    @Id
    @Column(name = "commentResourceID", nullable = false)
    public int getCommentResourceId() {
        return commentResourceId;
    }

    public void setCommentResourceId(int commentResourceId) {
        this.commentResourceId = commentResourceId;
    }

    @Id
    @Column(name = "commentIDNum", nullable = false)
    public int getCommentIdNum() {
        return commentIdNum;
    }

    public void setCommentIdNum(int commentIdNum) {
        this.commentIdNum = commentIdNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourceCommentEntity that = (ResourceCommentEntity) o;

        if (commentResourceId != that.commentResourceId) return false;
        if (commentIdNum != that.commentIdNum) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = commentResourceId;
        result = 31 * result + commentIdNum;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "commentResourceID", referencedColumnName = "resourceID", nullable = false)
    public ResourceEntity getResourceByCommentResourceId() {
        return resourceByCommentResourceId;
    }

    public void setResourceByCommentResourceId(ResourceEntity resourceByCommentResourceId) {
        this.resourceByCommentResourceId = resourceByCommentResourceId;
    }

    @ManyToOne
    @JoinColumn(name = "commentIDNum", referencedColumnName = "commentID", nullable = false)
    public UsercommentEntity getUsercommentByCommentIdNum() {
        return usercommentByCommentIdNum;
    }

    public void setUsercommentByCommentIdNum(UsercommentEntity usercommentByCommentIdNum) {
        this.usercommentByCommentIdNum = usercommentByCommentIdNum;
    }
}
