package model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Karn on 2017/2/14.
 */
public class BlogForwardEntityPK implements Serializable {
    private int forwardBlogId;
    private int forwarderId;

    @Column(name = "forwardBlogID", nullable = false)
    @Id
    public int getForwardBlogId() {
        return forwardBlogId;
    }

    public void setForwardBlogId(int forwardBlogId) {
        this.forwardBlogId = forwardBlogId;
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

        BlogForwardEntityPK that = (BlogForwardEntityPK) o;

        if (forwardBlogId != that.forwardBlogId) return false;
        if (forwarderId != that.forwarderId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = forwardBlogId;
        result = 31 * result + forwarderId;
        return result;
    }
}
