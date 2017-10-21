package model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Karn on 2017/2/14.
 */
public class ForumpostForwardEntityPK implements Serializable {
    private int forumpostId;
    private int forwarderId;

    @Column(name = "forumpostID", nullable = false)
    @Id
    public int getForumpostId() {
        return forumpostId;
    }

    public void setForumpostId(int forumpostId) {
        this.forumpostId = forumpostId;
    }

    @Column(name = "forwarderID", nullable = false)
    @Id
    public int getForwarderId() {
        return forwarderId;
    }

    public void setForwarderId(int forwarderId) {
        this.forwarderId = forwarderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ForumpostForwardEntityPK that = (ForumpostForwardEntityPK) o;

        if (forumpostId != that.forumpostId) return false;
        if (forwarderId != that.forwarderId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = forumpostId;
        result = 31 * result + forwarderId;
        return result;
    }
}
