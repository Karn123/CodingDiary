package model;

import javax.persistence.*;

/**
 * Created by Karn on 2017/3/6.
 */
@Entity
@Table(name = "forumpost_recommend", schema = "CodingDiaryDB", catalog = "")
@IdClass(ForumpostRecommendEntityPK.class)
public class ForumpostRecommendEntity {
    private int tagId;
    private int recommendForumpostId;
    private TagEntity tagByTagId;
    private ForumpostEntity forumpostByRecommendForumpostId;

    @Id
    @Column(name = "tagID", nullable = false)
    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    @Id
    @Column(name = "recommendForumpostID", nullable = false)
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

        ForumpostRecommendEntity that = (ForumpostRecommendEntity) o;

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

    @ManyToOne
    @JoinColumn(name = "tagID", referencedColumnName = "tagID", nullable = false)
    public TagEntity getTagByTagId() {
        return tagByTagId;
    }

    public void setTagByTagId(TagEntity tagByTagId) {
        this.tagByTagId = tagByTagId;
    }

    @ManyToOne
    @JoinColumn(name = "recommendForumpostID", referencedColumnName = "forumpostId", nullable = false)
    public ForumpostEntity getForumpostByRecommendForumpostId() {
        return forumpostByRecommendForumpostId;
    }

    public void setForumpostByRecommendForumpostId(ForumpostEntity forumpostByRecommendForumpostId) {
        this.forumpostByRecommendForumpostId = forumpostByRecommendForumpostId;
    }
}
