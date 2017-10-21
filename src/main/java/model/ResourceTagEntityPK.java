package model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Karn on 2017/2/14.
 */
public class ResourceTagEntityPK implements Serializable {
    private int resourceIdNum;
    private int tagIdNum;

    @Column(name = "resourceIDNum", nullable = false)
    @Id
    public int getResourceIdNum() {
        return resourceIdNum;
    }

    public void setResourceIdNum(int resourceIdNum) {
        this.resourceIdNum = resourceIdNum;
    }

    @Column(name = "tagIDNum", nullable = false)
    @Id
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

        ResourceTagEntityPK that = (ResourceTagEntityPK) o;

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
}
