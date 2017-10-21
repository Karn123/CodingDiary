package model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Karn on 2017/2/14.
 */
public class PraiseCommentEntityPK implements Serializable {
    private int praiseFromUserId;
    private int praiseCommentId;

    @Column(name = "praiseFromUserID", nullable = false)
    @Id
    public int getPraiseFromUserId() {
        return praiseFromUserId;
    }

    public void setPraiseFromUserId(int praiseFromUserId) {
        this.praiseFromUserId = praiseFromUserId;
    }

    @Column(name = "praiseCommentID", nullable = false)
    @Id
    public int getPraiseCommentId() {
        return praiseCommentId;
    }

    public void setPraiseCommentId(int praiseCommentId) {
        this.praiseCommentId = praiseCommentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PraiseCommentEntityPK that = (PraiseCommentEntityPK) o;

        if (praiseFromUserId != that.praiseFromUserId) return false;
        if (praiseCommentId != that.praiseCommentId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = praiseFromUserId;
        result = 31 * result + praiseCommentId;
        return result;
    }
}
