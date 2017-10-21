package model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Karn on 2017/2/14.
 */
public class BlogCommentEntityPK implements Serializable {
    private int commentBlogId;
    private int commentIdNum;

    @Column(name = "commentBlogID", nullable = false)
    @Id
    public int getCommentBlogId() {
        return commentBlogId;
    }

    public void setCommentBlogId(int commentBlogId) {
        this.commentBlogId = commentBlogId;
    }

    @Column(name = "commentIDNum", nullable = false)
    @Id
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

        BlogCommentEntityPK that = (BlogCommentEntityPK) o;

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
}
