package model;

import javax.persistence.*;

/**
 * Created by Karn on 2017/2/14.
 */
@Entity
@Table(name = "blog_comment", schema = "CodingDiaryDB", catalog = "")
@IdClass(BlogCommentEntityPK.class)
public class BlogCommentEntity {
    private int commentBlogId;
    private int commentIdNum;
    private BlogEntity blogByCommentBlogId;
    private UsercommentEntity usercommentByCommentIdNum;

    @Id
    @Column(name = "commentBlogID", nullable = false)
    public int getCommentBlogId() {
        return commentBlogId;
    }

    public void setCommentBlogId(int commentBlogId) {
        this.commentBlogId = commentBlogId;
    }

    @Id
    @Column(name = "commentIDNum", nullable = false)
    public int getCommentIdNum() {
        return commentIdNum;
    }

    public void setCommentIdNum(int commentIdNum) {
        this.commentIdNum = commentIdNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlogCommentEntity that = (BlogCommentEntity) o;

        if (commentBlogId != that.commentBlogId) return false;
        if (commentIdNum != that.commentIdNum) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = commentBlogId;
        result = 31 * result + commentIdNum;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "commentBlogID", referencedColumnName = "blogID", nullable = false)
    public BlogEntity getBlogByCommentBlogId() {
        return blogByCommentBlogId;
    }

    public void setBlogByCommentBlogId(BlogEntity blogByCommentBlogId) {
        this.blogByCommentBlogId = blogByCommentBlogId;
    }

    @ManyToOne
    @JoinColumn(name = "commentIDNum", referencedColumnName = "commentID", nullable = false)
    public UsercommentEntity getUsercommentByCommentIdNum() {
        return usercommentByCommentIdNum;
    }

    public void setUsercommentByCommentIdNum(UsercommentEntity usercommentByCommentIdNum) {
        this.usercommentByCommentIdNum = usercommentByCommentIdNum;
    }
}
