package model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Karn on 2017/2/14.
 */
public class ResourceForwardEntityPK implements Serializable {
    private int forwardResourceId;
    private int forwarderId;
    private Timestamp forwardTime;

    @Column(name = "forwardResourceID", nullable = false)
    @Id
    public int getForwardResourceId() {
        return forwardResourceId;
    }

    public void setForwardResourceId(int forwardResourceId) {
        this.forwardResourceId = forwardResourceId;
    }

    @Column(name = "forwarderID", nullable = false)
    @Id
    public int getForwarderId() {
        return forwarderId;
    }

    public void setForwarderId(int forwarderId) {
        this.forwarderId = forwarderId;
    }

    @Column(name = "forwardTime", nullable = false)
    @Id
    public Timestamp getForwardTime() {
        return forwardTime;
    }

    public void setForwardTime(Timestamp forwardTime) {
        this.forwardTime = forwardTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourceForwardEntityPK that = (ResourceForwardEntityPK) o;

        if (forwardResourceId != that.forwardResourceId) return false;
        if (forwarderId != that.forwarderId) return false;
        if (forwardTime != null ? !forwardTime.equals(that.forwardTime) : that.forwardTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = forwardResourceId;
        result = 31 * result + forwarderId;
        result = 31 * result + (forwardTime != null ? forwardTime.hashCode() : 0);
        return result;
    }
}
