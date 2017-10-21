package model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Karn on 2017/2/14.
 */
public class PraiseResourceEntityPK implements Serializable {
    private int praiseFromUserId;
    private int praiseResourceId;

    public PraiseResourceEntityPK() {
    }

    public PraiseResourceEntityPK(int praise_user_id, int resourceID) {
        this.praiseFromUserId = praise_user_id;
        this.praiseResourceId = resourceID;
    }

    @Column(name = "praiseFromUserID", nullable = false)
    @Id
    public int getPraiseFromUserId() {
        return praiseFromUserId;
    }

    public void setPraiseFromUserId(int praiseFromUserId) {
        this.praiseFromUserId = praiseFromUserId;
    }

    @Column(name = "praiseResourceID", nullable = false)
    @Id
    public int getPraiseResourceId() {
        return praiseResourceId;
    }

    public void setPraiseResourceId(int praiseResourceId) {
        this.praiseResourceId = praiseResourceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PraiseResourceEntityPK that = (PraiseResourceEntityPK) o;

        if (praiseFromUserId != that.praiseFromUserId) return false;
        if (praiseResourceId != that.praiseResourceId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = praiseFromUserId;
        result = 31 * result + praiseResourceId;
        return result;
    }
}
