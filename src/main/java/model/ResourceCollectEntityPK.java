package model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Karn on 2017/2/14.
 */
public class ResourceCollectEntityPK implements Serializable {
    private int collectResourceId;
    private int collectorId;

    public ResourceCollectEntityPK(int collector, int resourceID) {
        this.collectorId = collector;
        this.collectResourceId = resourceID;
    }

    public ResourceCollectEntityPK() {
    }

    @Column(name = "collectResourceID", nullable = false)
    @Id
    public int getCollectResourceId() {
        return collectResourceId;
    }

    public void setCollectResourceId(int collectResourceId) {
        this.collectResourceId = collectResourceId;
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

        ResourceCollectEntityPK that = (ResourceCollectEntityPK) o;

        if (collectResourceId != that.collectResourceId) return false;
        if (collectorId != that.collectorId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = collectResourceId;
        result = 31 * result + collectorId;
        return result;
    }
}
