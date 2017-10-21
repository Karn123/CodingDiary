package model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Karn on 2017/2/14.
 */
public class ForumpostCollectEntityPK implements Serializable {
    private int forumpostId;
    private int collectorId;

    @Column(name = "forumpostID", nullable = false)
    @Id
    public int getForumpostId() {
        return forumpostId;
    }

    public void setForumpostId(int forumpostId) {
        this.forumpostId = forumpostId;
    }

    @Column(name = "collectorID", nullable = false)
    @Id
    public int getCollectorId() {
        return collectorId;
    }

    public void setCollectorId(int collectorId) {
        this.collectorId = collectorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ForumpostCollectEntityPK that = (ForumpostCollectEntityPK) o;

        if (forumpostId != that.forumpostId) return false;
        if (collectorId != that.collectorId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = forumpostId;
        result = 31 * result + collectorId;
        return result;
    }
}
