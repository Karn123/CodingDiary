package model;

import javax.persistence.*;

/**
 * Created by Karn on 2017/2/14.
 */
@Entity
@Table(name = "forumpost_floor_comment", schema = "CodingDiaryDB", catalog = "")
@IdClass(ForumpostFloorCommentEntityPK.class)
public class ForumpostFloorCommentEntity {
    private int commentForumpostId;
    private int commentToFloorId;
    private int commentIdNum;
    private ForumpostEntity forumpostByCommentForumpostId;
    private ForumpostFloorEntity forumpostFloorByCommentToFloorId;
    private UsercommentEntity usercommentByCommentIdNum;

    @Id
    @Column(name = "commentForumpostID", nullable = false)
    public int getCommentForumpostId() {
        return commentForumpostId;
    }

    public void setCommentForumpostId(int commentForumpostId) {
        this.commentForumpostId = commentForumpostId;
    }

    @Id
    @Column(name = "commentToFloorID", nullable = false)
    public int getCommentToFloorId() {
        return commentToFloorId;
    }

    public void setCommentToFloorId(int commentToFloorId) {
        this.commentToFloorId = commentToFloorId;
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

        ForumpostFloorCommentEntity that = (ForumpostFloorCommentEntity) o;

        if (commentForumpostId != that.commentForumpostId) return false;
        if (commentToFloorId != that.commentToFloorId) return false;
        if (commentIdNum != that.commentIdNum) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = commentForumpostId;
        result = 31 * result + commentToFloorId;
        result = 31 * result + commentIdNum;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "commentForumpostID", referencedColumnName = "forumpost_id", nullable = false)
    public ForumpostEntity getForumpostByCommentForumpostId() {
        return forumpostByCommentForumpostId;
    }

    public void setForumpostByCommentForumpostId(ForumpostEntity forumpostByCommentForumpostId) {
        this.forumpostByCommentForumpostId = forumpostByCommentForumpostId;
    }

    @ManyToOne
    @JoinColumn(name = "commentToFloorID", referencedColumnName = "floorID", nullable = false)
    public ForumpostFloorEntity getForumpostFloorByCommentToFloorId() {
        return forumpostFloorByCommentToFloorId;
    }

    public void setForumpostFloorByCommentToFloorId(ForumpostFloorEntity forumpostFloorByCommentToFloorId) {
        this.forumpostFloorByCommentToFloorId = forumpostFloorByCommentToFloorId;
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
