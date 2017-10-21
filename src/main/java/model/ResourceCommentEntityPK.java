package model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Karn on 2017/2/14.
 */
public class ResourceCommentEntityPK implements Serializable {
    private int commentResourceId;
    private int commentIdNum;

    @Column(name = "commentResourceID", nullable = false)
    @Id
    public int getCommentResourceId() {
        return commentResourceId;
    }

    public void setCommentResourceId(int commentResourceId) {
        this.commentResourceId = commentResourceId;
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

        ResourceCommentEntityPK that = (ResourceCommentEntityPK) o;

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
}
