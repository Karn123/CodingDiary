package model;

import javax.persistence.*;

/**
 * Created by Karn on 2017/3/6.
 */
@Entity
@Table(name = "blog_recommend", schema = "CodingDiaryDB", catalog = "")
@IdClass(BlogRecommendEntityPK.class)
public class BlogRecommendEntity {
    private int tagId;
    private int recommendBlogId;
    private TagEntity tagByTagId;
    private BlogEntity blogByRecommendBlogId;

    @Id
    @Column(name = "tagID", nullable = false)
    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    @Id
    @Column(name = "recommendBlogID", nullable = false)
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

        BlogRecommendEntity that = (BlogRecommendEntity) o;

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

    @ManyToOne
    @JoinColumn(name = "tagID", referencedColumnName = "tagID", nullable = false)
    public TagEntity getTagByTagId() {
        return tagByTagId;
    }

    public void setTagByTagId(TagEntity tagByTagId) {
        this.tagByTagId = tagByTagId;
    }

    @ManyToOne
    @JoinColumn(name = "recommendBlogID", referencedColumnName = "blogID", nullable = false)
    public BlogEntity getBlogByRecommendBlogId() {
        return blogByRecommendBlogId;
    }

    public void setBlogByRecommendBlogId(BlogEntity blogByRecommendBlogId) {
        this.blogByRecommendBlogId = blogByRecommendBlogId;
    }
}
