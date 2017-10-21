package model;

import javax.persistence.*;

/**
 * Created by Karn on 2017/2/15.
 */
@Entity
@Table(name = "resource_score", schema = "CodingDiaryDB", catalog = "")
@IdClass(ResourceScoreEntityPK.class)
public class ResourceScoreEntity {
    private int userId;
    private int resourceId;
    private double score;
    private UserinfoEntity userinfoByUserId;
    private ResourceEntity resourceByResourceId;

    @Id
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "resource_id", nullable = false)
    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    @Basic
    @Column(name = "score", nullable = false, precision = 0)
    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourceScoreEntity that = (ResourceScoreEntity) o;

        if (userId != that.userId) return false;
        if (resourceId != that.resourceId) return false;
        if (Double.compare(that.score, score) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = userId;
        result = 31 * result + resourceId;
        temp = Double.doubleToLongBits(score);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userID", nullable = false)
    public UserinfoEntity getUserinfoByUserId() {
        return userinfoByUserId;
    }

    public void setUserinfoByUserId(UserinfoEntity userinfoByUserId) {
        this.userinfoByUserId = userinfoByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "resource_id", referencedColumnName = "resourceID", nullable = false)
    public ResourceEntity getResourceByResourceId() {
        return resourceByResourceId;
    }

    public void setResourceByResourceId(ResourceEntity resourceByResourceId) {
        this.resourceByResourceId = resourceByResourceId;
    }
}
