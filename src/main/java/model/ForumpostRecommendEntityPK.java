package model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Karn on 2017/3/6.
 */
public class ForumpostRecommendEntityPK implements Serializable {
    private int tagId;
    private int recommendForumpostId;

    @Column(name = "tagID", nullable = false)
    @Id
    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    @Column(name = "recommendForumpostID", nullable = false)
    @Id
    public int getRecommendForumpostId() {
        return recommendForumpostId;
    }

    public void setRecommendForumpostId(int recommendForumpostId) {
        this.recommendForumpostId = recommendForumpostId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ForumpostRecommendEntityPK that = (ForumpostRecommendEntityPK) o;

        if (tagId != that.tagId) return false;
        if (recommendForumpostId != that.recommendForumpostId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tagId;
        result = 31 * result + recommendForumpostId;
        return result;
    }
}
