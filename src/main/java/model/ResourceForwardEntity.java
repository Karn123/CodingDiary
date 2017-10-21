package model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Karn on 2017/2/14.
 */
@Entity
@Table(name = "resource_forward", schema = "CodingDiaryDB", catalog = "")
@IdClass(ResourceForwardEntityPK.class)
public class ResourceForwardEntity {
    private int forwardResourceId;
    private int forwarderId;
    private String forwardMsg;
    private Timestamp forwardTime;
    private ResourceEntity resourceByForwardResourceId;
    private UserinfoEntity userinfoByForwarderId;

    @Id
    @Column(name = "forwardResourceID", nullable = false)
    public int getForwardResourceId() {
        return forwardResourceId;
    }

    public void setForwardResourceId(int forwardResourceId) {
        this.forwardResourceId = forwardResourceId;
    }

    @Id
    @Column(name = "forwarderID", nullable = false)
    public int getForwarderId() {
        return forwarderId;
    }

    public void setForwarderId(int forwarderId) {
        this.forwarderId = forwarderId;
    }

    @Basic
    @Column(name = "forwardMsg", nullable = true, length = 1000)
    public String getForwardMsg() {
        return forwardMsg;
    }

    public void setForwardMsg(String forwardMsg) {
        this.forwardMsg = forwardMsg;
    }

    @Id
    @Column(name = "forwardTime", nullable = false)
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

        ResourceForwardEntity that = (ResourceForwardEntity) o;

        if (forwardResourceId != that.forwardResourceId) return false;
        if (forwarderId != that.forwarderId) return false;
        if (forwardMsg != null ? !forwardMsg.equals(that.forwardMsg) : that.forwardMsg != null) return false;
        if (forwardTime != null ? !forwardTime.equals(that.forwardTime) : that.forwardTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = forwardResourceId;
        result = 31 * result + forwarderId;
        result = 31 * result + (forwardMsg != null ? forwardMsg.hashCode() : 0);
        result = 31 * result + (forwardTime != null ? forwardTime.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "forwardResourceID", referencedColumnName = "resourceID", nullable = false)
    public ResourceEntity getResourceByForwardResourceId() {
        return resourceByForwardResourceId;
    }

    public void setResourceByForwardResourceId(ResourceEntity resourceByForwardResourceId) {
        this.resourceByForwardResourceId = resourceByForwardResourceId;
    }

    @ManyToOne
    @JoinColumn(name = "forwarderID", referencedColumnName = "userID", nullable = false)
    public UserinfoEntity getUserinfoByForwarderId() {
        return userinfoByForwarderId;
    }

    public void setUserinfoByForwarderId(UserinfoEntity userinfoByForwarderId) {
        this.userinfoByForwarderId = userinfoByForwarderId;
    }
}
