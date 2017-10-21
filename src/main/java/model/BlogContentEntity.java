package model;

import javax.persistence.*;

/**
 * Created by Karn on 2017/2/14.
 */
@Entity
@Table(name = "blog_content", schema = "CodingDiaryDB", catalog = "")
public class BlogContentEntity {
    private int blogId;
    private String blogContent;
    private BlogEntity blogByBlogId;

    public BlogContentEntity() {

    }

    public BlogContentEntity(String blogContent) {
        this.blogContent = blogContent;
    }

    @Id
    @Column(name = "blog_id", nullable = false)
    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    @Basic
    @Column(name = "blogContent", nullable = false, length = -1)
    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlogContentEntity that = (BlogContentEntity) o;

        if (blogId != that.blogId) return false;
        if (blogContent != null ? !blogContent.equals(that.blogContent) : that.blogContent != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = blogId;
        result = 31 * result + (blogContent != null ? blogContent.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "blog_id", referencedColumnName = "blogID", nullable = false)
    public BlogEntity getBlogByBlogId() {
        return blogByBlogId;
    }

    public void setBlogByBlogId(BlogEntity blogByBlogId) {
        this.blogByBlogId = blogByBlogId;
    }
}
