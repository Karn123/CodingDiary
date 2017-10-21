package model;

import javax.persistence.*;

/**
 * Created by Karn on 2017/2/14.
 */
@Entity
@Table(name = "resource_tag", schema = "CodingDiaryDB", catalog = "")
@IdClass(ResourceTagEntityPK.class)
public class ResourceTagEntity {
    private int resourceIdNum;
    private int tagIdNum;
    private ResourceEntity resourceByResourceIdNum;
    private TagEntity tagByTagIdNum;

    @Id
    @Column(name = "resourceIDNum", nullable = false)
    public int getResourceIdNum() {
        return resourceIdNum;
    }

    public void setResourceIdNum(int resourceIdNum) {
        this.resourceIdNum = resourceIdNum;
    }

    @Id
    @Column(name = "tagIDNum", nullable = false)
    public int getTagIdNum() {
        return tagIdNum;
    }

    public void setTagIdNum(int tagIdNum) {
        this.tagIdNum = tagIdNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourceTagEntity that = (ResourceTagEntity) o;

        if (resourceIdNum != that.resourceIdNum) return false;
        if (tagIdNum != that.tagIdNum) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = resourceIdNum;
        result = 31 * result + tagIdNum;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "resourceIDNum", referencedColumnName = "resourceID", nullable = false)
    public ResourceEntity getResourceByResourceIdNum() {
        return resourceByResourceIdNum;
    }

    public void setResourceByResourceIdNum(ResourceEntity resourceByResourceIdNum) {
        this.resourceByResourceIdNum = resourceByResourceIdNum;
    }

    @ManyToOne
    @JoinColumn(name = "tagIDNum", referencedColumnName = "tagID", nullable = false)
    public TagEntity getTagByTagIdNum() {
        return tagByTagIdNum;
    }

    public void setTagByTagIdNum(TagEntity tagByTagIdNum) {
        this.tagByTagIdNum = tagByTagIdNum;
    }
}
