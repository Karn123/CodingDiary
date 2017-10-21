package model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Karn on 2017/2/14.
 */
public class PraiseBlogEntityPK implements Serializable {
    private int praiseFromUserId;
    private int praiseBlogId;

    @Column(name = "praiseFromUserID", nullable = false)
    @Id
    public int getPraiseFromUserId() {
        return praiseFromUserId;
    }

    public void setPraiseFromUserId(int praiseFromUserId) {
        this.praiseFromUserId = praiseFromUserId;
    }

    @Column(name = "praiseBlogID", nullable = false)
    @Id
    public int getPraiseBlogId() {
        return praiseBlogId;
    }

    public void setPraiseBlogId(int praiseBlogId) {
        this.praiseBlogId = praiseBlogId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PraiseBlogEntityPK that = (PraiseBlogEntityPK) o;

        if (praiseFromUserId != that.praiseFromUserId) return false;
        if (praiseBlogId != that.praiseBlogId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = praiseFromUserId;
        result = 31 * result + praiseBlogId;
        return result;
    }
}
