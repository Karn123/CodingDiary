package model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Karn on 2017/2/14.
 */
public class ForumpostFloorCommentEntityPK implements Serializable {
    private int commentForumpostId;
    private int commentToFloorId;
    private int commentIdNum;

    @Column(name = "commentForumpostID", nullable = false)
    @Id
    public int getCommentForumpostId() {
        return commentForumpostId;
    }

    public void setCommentForumpostId(int commentForumpostId) {
        this.commentForumpostId = commentForumpostId;
    }

    @Column(name = "commentToFloorID", nullable = false)
    @Id
    public int getCommentToFloorId() {
        return commentToFloorId;
    }

    public void setCommentToFloorId(int commentToFloorId) {
        this.commentToFloorId = commentToFloorId;
    }

    @Column(name = "commentIDNum", nullable = false)
    @Id
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

        ForumpostFloorCommentEntityPK that = (ForumpostFloorCommentEntityPK) o;

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
}
