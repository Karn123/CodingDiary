package model;

import javax.persistence.*;

/**
 * Created by Karn on 2017/3/6.
 */
@Entity
@Table(name = "resource_recommend_new", schema = "CodingDiaryDB", catalog = "")
@IdClass(ResourceRecommendNewEntityPK.class)
public class ResourceRecommendNewEntity {
    private int tagId;
    private int recommendResourceId;
    private TagEntity tagByTagId;
    private ResourceEntity resourceByRecommendResourceId;

    @Id
    @Column(name = "tagID", nullable = false)
    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    @Id
    @Column(name = "recommendResourceID", nullable = false)
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

        ResourceRecommendNewEntity that = (ResourceRecommendNewEntity) o;

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

    @ManyToOne
    @JoinColumn(name = "tagID", referencedColumnName = "tagID", nullable = false)
    public TagEntity getTagByTagId() {
        return tagByTagId;
    }

    public void setTagByTagId(TagEntity tagByTagId) {
        this.tagByTagId = tagByTagId;
    }

    @ManyToOne
    @JoinColumn(name = "recommendResourceID", referencedColumnName = "resourceID", nullable = false)
    public ResourceEntity getResourceByRecommendResourceId() {
        return resourceByRecommendResourceId;
    }

    public void setResourceByRecommendResourceId(ResourceEntity resourceByRecommendResourceId) {
        this.resourceByRecommendResourceId = resourceByRecommendResourceId;
    }
}
