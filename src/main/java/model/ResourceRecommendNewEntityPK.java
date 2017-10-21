package model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Karn on 2017/3/6.
 */
public class ResourceRecommendNewEntityPK implements Serializable {
    private int tagId;
    private int recommendResourceId;

    @Column(name = "tagID", nullable = false)
    @Id
    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    @Column(name = "recommendResourceID", nullable = false)
    @Id
    public int getRecommendResourceId() {
        return recommendResourceId;
    }

    public void setRecommendResourceId(int recommendResourceId) {
        this.recommendResourceId = recommendResourceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourceRecommendNewEntityPK that = (ResourceRecommendNewEntityPK) o;

        if (tagId != that.tagId) return false;
        if (recommendResourceId != that.recommendResourceId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tagId;
        result = 31 * result + recommendResourceId;
        return result;
    }
}
