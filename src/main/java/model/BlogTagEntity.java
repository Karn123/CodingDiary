package model;

import javax.persistence.*;

/**
 * Created by Karn on 2017/2/14.
 */
@Entity
@Table(name = "blog_tag", schema = "CodingDiaryDB", catalog = "")
@IdClass(BlogTagEntityPK.class)
public class BlogTagEntity {
    private int blogIdNum;
    private int tagIdNum;
    private BlogEntity blogByBlogIdNum;
    private TagEntity tagByTagIdNum;

    @Id
    @Column(name = "BlogIDNum", nullable = false)
    public int getBlogIdNum() {
        return blogIdNum;
    }

    public void setBlogIdNum(int blogIdNum) {
        this.blogIdNum = blogIdNum;
    }

    @Id
    @Column(name = "TagIDNum", nullable = false)
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

        BlogTagEntity that = (BlogTagEntity) o;

        if (blogIdNum != that.blogIdNum) return false;
        if (tagIdNum != that.tagIdNum) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = blogIdNum;
        result = 31 * result + tagIdNum;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "BlogIDNum", referencedColumnName = "blogID", nullable = false)
    public BlogEntity getBlogByBlogIdNum() {
        return blogByBlogIdNum;
    }

    public void setBlogByBlogIdNum(BlogEntity blogByBlogIdNum) {
        this.blogByBlogIdNum = blogByBlogIdNum;
    }

    @ManyToOne
    @JoinColumn(name = "TagIDNum", referencedColumnName = "tagID", nullable = false)
    public TagEntity getTagByTagIdNum() {
        return tagByTagIdNum;
    }

    public void setTagByTagIdNum(TagEntity tagByTagIdNum) {
        this.tagByTagIdNum = tagByTagIdNum;
    }
}
