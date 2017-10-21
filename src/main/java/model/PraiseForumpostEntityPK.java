package model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Karn on 2017/2/14.
 */
public class PraiseForumpostEntityPK implements Serializable {
    private int praiseFromUserId;
    private int praiseForumpostId;

    @Column(name = "praiseFromUserID", nullable = false)
    @Id
    public int getPraiseFromUserId() {
        return praiseFromUserId;
    }

    public void setPraiseFromUserId(int praiseFromUserId) {
        this.praiseFromUserId = praiseFromUserId;
    }

    @Column(name = "praiseForumpostID", nullable = false)
    @Id
    public int getPraiseForumpostFloorId() {
        return praiseForumpostId;
    }

    public void setPraiseForumpostFloorId(int praiseForumpostId) {
        this.praiseForumpostId = praiseForumpostId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PraiseForumpostEntityPK that = (PraiseForumpostEntityPK) o;

        if (praiseFromUserId != that.praiseFromUserId) return false;
        if (praiseForumpostId != that.praiseForumpostId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = praiseFromUserId;
        result = 31 * result + praiseForumpostId;
        return result;
    }
}
