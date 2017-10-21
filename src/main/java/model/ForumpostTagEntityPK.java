package model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Karn on 2017/2/14.
 */
public class ForumpostTagEntityPK implements Serializable {
    private int forumpostId;
    private int tagId;

    @Column(name = "forumpostID", nullable = false)
    @Id
    public int getForumpostId() {
        return forumpostId;
    }

    public void setForumpostId(int forumpostId) {
        this.forumpostId = forumpostId;
    }

    @Column(name = "tagID", nullable = false)
    @Id
    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ForumpostTagEntityPK that = (ForumpostTagEntityPK) o;

        if (forumpostId != that.forumpostId) return false;
        if (tagId != that.tagId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = forumpostId;
        result = 31 * result + tagId;
        return result;
    }
}
