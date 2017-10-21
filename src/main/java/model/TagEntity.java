package model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Karn on 2017/2/14.
 */
@Entity
@Table(name = "tag", schema = "CodingDiaryDB", catalog = "")
public class TagEntity {
    private int tagId;
    private String tagName;
    private Collection<BlogTagEntity> blogTagsByTagId;
    private Collection<ForumpostTagEntity> forumpostTagsByTagId;
    private Collection<ResourceTagEntity> resourceTagsByTagId;

    public TagEntity(){
    }

    public TagEntity(String tagName) {
        this.tagName = tagName;
    }


    @Id
    @Column(name = "tagID", nullable = false)
    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    @Basic
    @Column(name = "tagName", nullable = false, length = 20)
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagEntity tagEntity = (TagEntity) o;

        if (tagId != tagEntity.tagId) return false;
        if (tagName != null ? !tagName.equals(tagEntity.tagName) : tagEntity.tagName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tagId;
        result = 31 * result + (tagName != null ? tagName.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "tagByTagIdNum")
    public Collection<BlogTagEntity> getBlogTagsByTagId() {
        return blogTagsByTagId;
    }

    public void setBlogTagsByTagId(Collection<BlogTagEntity> blogTagsByTagId) {
        this.blogTagsByTagId = blogTagsByTagId;
    }

    @OneToMany(mappedBy = "tagByTagId")
    public Collection<ForumpostTagEntity> getForumpostTagsByTagId() {
        return forumpostTagsByTagId;
    }

    public void setForumpostTagsByTagId(Collection<ForumpostTagEntity> forumpostTagsByTagId) {
        this.forumpostTagsByTagId = forumpostTagsByTagId;
    }

    @OneToMany(mappedBy = "tagByTagIdNum")
    public Collection<ResourceTagEntity> getResourceTagsByTagId() {
        return resourceTagsByTagId;
    }

    public void setResourceTagsByTagId(Collection<ResourceTagEntity> resourceTagsByTagId) {
        this.resourceTagsByTagId = resourceTagsByTagId;
    }
}
