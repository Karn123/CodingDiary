package model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Karn on 2017/2/15.
 */
public class ResourceScoreEntityPK implements Serializable {
    private int userId;
    private int resourceId;

    public ResourceScoreEntityPK(int userId, int resourceId) {
        this.userId = userId;
        this.resourceId = resourceId;
    }

    public ResourceScoreEntityPK(){
    }

    @Column(name = "user_id", nullable = false)
    @Id
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "resource_id", nullable = false)
    @Id
    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourceScoreEntityPK that = (ResourceScoreEntityPK) o;

        if (userId != that.userId) return false;
        if (resourceId != that.resourceId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + resourceId;
        return result;
    }
}
