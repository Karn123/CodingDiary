package model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Karn on 2017/2/14.
 */
public class BlogCollectEntityPK implements Serializable {
    private int collectBlogId;
    private int collectorId;

    @Column(name = "collectBlogID", nullable = false)
    @Id
    public int getCollectBlogId() {
        return collectBlogId;
    }

    public void setCollectBlogId(int collectBlogId) {
        this.collectBlogId = collectBlogId;
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

        BlogCollectEntityPK that = (BlogCollectEntityPK) o;

        if (collectBlogId != that.collectBlogId) return false;
        if (collectorId != that.collectorId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = collectBlogId;
        result = 31 * result + collectorId;
        return result;
    }
}
