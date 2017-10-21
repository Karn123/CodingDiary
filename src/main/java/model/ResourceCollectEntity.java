package model;

import javax.persistence.*;

/**
 * Created by Karn on 2017/2/14.
 */
@Entity
@Table(name = "resource_collect", schema = "CodingDiaryDB", catalog = "")
@IdClass(ResourceCollectEntityPK.class)
public class ResourceCollectEntity {
    private int collectResourceId;
    private int collectorId;
    private ResourceEntity resourceByCollectResourceId;
    private UserinfoEntity userinfoByCollectorId;
    private TfEntity tfByIsCollected;

    @Id
    @Column(name = "collectResourceID", nullable = false)
    public int getCollectResourceId() {
        return collectResourceId;
    }

    public void setCollectResourceId(int collectResourceId) {
        this.collectResourceId = collectResourceId;
    }

    @Id
    @Column(name = "collectorID", nullable = false)
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

        ResourceCollectEntity that = (ResourceCollectEntity) o;

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

    @ManyToOne
    @JoinColumn(name = "collectResourceID", referencedColumnName = "resourceID", nullable = false)
    public ResourceEntity getResourceByCollectResourceId() {
        return resourceByCollectResourceId;
    }

    public void setResourceByCollectResourceId(ResourceEntity resourceByCollectResourceId) {
        this.resourceByCollectResourceId = resourceByCollectResourceId;
    }

    @ManyToOne
    @JoinColumn(name = "collectorID", referencedColumnName = "userID", nullable = false)
    public UserinfoEntity getUserinfoByCollectorId() {
        return userinfoByCollectorId;
    }

    public void setUserinfoByCollectorId(UserinfoEntity userinfoByCollectorId) {
        this.userinfoByCollectorId = userinfoByCollectorId;
    }

    @ManyToOne
    @JoinColumn(name = "isCollected", referencedColumnName = "TFID")
    public TfEntity getTfByIsCollected() {
        return tfByIsCollected;
    }

    public void setTfByIsCollected(TfEntity tfByIsCollected) {
        this.tfByIsCollected = tfByIsCollected;
    }
}
