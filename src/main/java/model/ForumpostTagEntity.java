package model;

import javax.persistence.*;

/**
 * Created by Karn on 2017/2/14.
 */
@Entity
@Table(name = "forumpost_tag", schema = "CodingDiaryDB", catalog = "")
@IdClass(ForumpostTagEntityPK.class)
public class ForumpostTagEntity {
    private int forumpostId;
    private int tagId;
    private ForumpostEntity forumpostByForumpostId;
    private TagEntity tagByTagId;

    @Id
    @Column(name = "forumpostID", nullable = false)
    public int getForumpostId() {
        return forumpostId;
    }

    public void setForumpostId(int forumpostId) {
        this.forumpostId = forumpostId;
    }

    @Id
    @Column(name = "tagID", nullable = false)
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

        ForumpostTagEntity that = (ForumpostTagEntity) o;

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

    @ManyToOne
    @JoinColumn(name = "forumpostID", referencedColumnName = "forumpost_id", nullable = false)
    public ForumpostEntity getForumpostByForumpostId() {
        return forumpostByForumpostId;
    }

    public void setForumpostByForumpostId(ForumpostEntity forumpostByForumpostId) {
        this.forumpostByForumpostId = forumpostByForumpostId;
    }

    @ManyToOne
    @JoinColumn(name = "tagID", referencedColumnName = "tagID", nullable = false)
    public TagEntity getTagByTagId() {
        return tagByTagId;
    }

    public void setTagByTagId(TagEntity tagByTagId) {
        this.tagByTagId = tagByTagId;
    }
}
