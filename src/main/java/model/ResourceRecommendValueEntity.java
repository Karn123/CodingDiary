package model;

import javax.persistence.*;

/**
 * Created by Karn on 2017/3/6.
 */
@Entity
@Table(name = "resource_recommend_value", schema = "CodingDiaryDB", catalog = "")
public class ResourceRecommendValueEntity {
    private int resourceId;
    private double browseValue;
    private double collectValue;
    private double praiseValue;
    private double forwardValue;
    private double totalValue;
    private double todayValue;
    private int isNew;
    private ResourceEntity resourceByResourceId;
    private TfEntity tfByIsNew;

    @Id
    @Column(name = "resourceID", nullable = false)
    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    @Basic
    @Column(name = "browseValue", nullable = false, precision = 0)
    public double getBrowseValue() {
        return browseValue;
    }

    public void setBrowseValue(double browseValue) {
        this.browseValue = browseValue;
    }

    @Basic
    @Column(name = "collectValue", nullable = false, precision = 0)
    public double getCollectValue() {
        return collectValue;
    }

    public void setCollectValue(double collectValue) {
        this.collectValue = collectValue;
    }

    @Basic
    @Column(name = "praiseValue", nullable = false, precision = 0)
    public double getPraiseValue() {
        return praiseValue;
    }

    public void setPraiseValue(double praiseValue) {
        this.praiseValue = praiseValue;
    }

    @Basic
    @Column(name = "forwardValue", nullable = false, precision = 0)
    public double getForwardValue() {
        return forwardValue;
    }

    public void setForwardValue(double forwardValue) {
        this.forwardValue = forwardValue;
    }

    @Basic
    @Column(name = "totalValue", nullable = false, precision = 0)
    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    @Basic
    @Column(name = "todayValue", nullable = false, precision = 0)
    public double getTodayValue() {
        return todayValue;
    }

    public void setTodayValue(double todayValue) {
        this.todayValue = todayValue;
    }

    @Basic
    @Column(name = "isNew", nullable = false)
    public int getIsNew() {
        return isNew;
    }

    public void setIsNew(int isNew) {
        this.isNew = isNew;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourceRecommendValueEntity that = (ResourceRecommendValueEntity) o;

        if (resourceId != that.resourceId) return false;
        if (Double.compare(that.browseValue, browseValue) != 0) return false;
        if (Double.compare(that.collectValue, collectValue) != 0) return false;
        if (Double.compare(that.praiseValue, praiseValue) != 0) return false;
        if (Double.compare(that.forwardValue, forwardValue) != 0) return false;
        if (Double.compare(that.totalValue, totalValue) != 0) return false;
        if (Double.compare(that.todayValue, todayValue) != 0) return false;
        if (isNew != that.isNew) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = resourceId;
        temp = Double.doubleToLongBits(browseValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(collectValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(praiseValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(forwardValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(totalValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(todayValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + isNew;
        return result;
    }

    @OneToOne
    @JoinColumn(name = "resourceID", referencedColumnName = "resourceID", nullable = false)
    public ResourceEntity getResourceByResourceId() {
        return resourceByResourceId;
    }

    public void setResourceByResourceId(ResourceEntity resourceByResourceId) {
        this.resourceByResourceId = resourceByResourceId;
    }

    @ManyToOne
    @JoinColumn(name = "isNew", referencedColumnName = "TFID", nullable = false)
    public TfEntity getTfByIsNew() {
        return tfByIsNew;
    }

    public void setTfByIsNew(TfEntity tfByIsNew) {
        this.tfByIsNew = tfByIsNew;
    }
}
