package model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Karn on 2017/3/6.
 */
public class PraiseForumpostFloorEntityPK implements Serializable {
    private int praiseFromUserId;
    private int praiseForumpostFloorId;

    @Column(name = "praiseFromUserID", nullable = false)
    @Id
    public int getPraiseFromUserId() {
        return praiseFromUserId;
    }

    public void setPraiseFromUserId(int praiseFromUserId) {
        this.praiseFromUserId = praiseFromUserId;
    }

    @Column(name = "praiseForumpostFloorID", nullable = false)
    @Id
    public int getPraiseForumpostFloorId() {
        return praiseForumpostFloorId;
    }

    public void setPraiseForumpostFloorId(int praiseForumpostFloorId) {
        this.praiseForumpostFloorId = praiseForumpostFloorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PraiseForumpostFloorEntityPK that = (PraiseForumpostFloorEntityPK) o;

        if (praiseFromUserId != that.praiseFromUserId) return false;
        if (praiseForumpostFloorId != that.praiseForumpostFloorId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = praiseFromUserId;
        result = 31 * result + praiseForumpostFloorId;
        return result;
    }
}
