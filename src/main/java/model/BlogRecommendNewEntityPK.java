package model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Karn on 2017/3/6.
 */
public class BlogRecommendNewEntityPK implements Serializable {
    private int tagId;
    private int recommendBlogId;

    @Column(name = "tagID", nullable = false)
    @Id
    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    @Column(name = "recommendBlogID", nullable = false)
    @Id
    public int getRecommendBlogId() {
        return recommendBlogId;
    }

    public void setRecommendBlogId(int recommendBlogId) {
        this.recommendBlogId = recommendBlogId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlogRecommendNewEntityPK that = (BlogRecommendNewEntityPK) o;

        if (tagId != that.tagId) return false;
        if (recommendBlogId != that.recommendBlogId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tagId;
        result = 31 * result + recommendBlogId;
        return result;
    }
}
