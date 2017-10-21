package model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Karn on 2017/2/14.
 */
public class BlogTagEntityPK implements Serializable {
    private int blogIdNum;
    private int tagIdNum;

    @Column(name = "BlogIDNum", nullable = false)
    @Id
    public int getBlogIdNum() {
        return blogIdNum;
    }

    public void setBlogIdNum(int blogIdNum) {
        this.blogIdNum = blogIdNum;
    }

    @Column(name = "TagIDNum", nullable = false)
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

        BlogTagEntityPK that = (BlogTagEntityPK) o;

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
}
