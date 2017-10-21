package model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Karn on 2017/2/14.
 */
@Entity
@Table(name = "TF", schema = "CodingDiaryDB", catalog = "")
public class TfEntity {
    private int tfid;
    private String tfOption;
    private Collection<BlogEntity> blogsByTfid;
    private Collection<BlogCollectEntity> blogCollectsByTfid;
    private Collection<ForumpostCollectEntity> forumpostCollectsByTfid;
    private Collection<ForumpostFloorEntity> forumpostFloorsByTfid;
    private Collection<PraiseBlogEntity> praiseBlogsByTfid;
    private Collection<PraiseCommentEntity> praiseCommentsByTfid;
    private Collection<PraiseForumpostEntity> praiseForumpostsByTfid;
    private Collection<PraiseResourceEntity> praiseResourcesByTfid;
    private Collection<ResourceEntity> resourcesByTfid;
    private Collection<ResourceCollectEntity> resourceCollectsByTfid;
    private Collection<UserinfoEntity> userinfosByTfid;

    @Id
    @Column(name = "TFID", nullable = false)
    public int getTfid() {
        return tfid;
    }

    public void setTfid(int tfid) {
        this.tfid = tfid;
    }

    @Basic
    @Column(name = "TFOption", nullable = false, length = 30)
    public String getTfOption() {
        return tfOption;
    }

    public void setTfOption(String tfOption) {
        this.tfOption = tfOption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TfEntity tfEntity = (TfEntity) o;

        if (tfid != tfEntity.tfid) return false;
        if (tfOption != null ? !tfOption.equals(tfEntity.tfOption) : tfEntity.tfOption != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tfid;
        result = 31 * result + (tfOption != null ? tfOption.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "tfByIsLegal")
    public Collection<BlogEntity> getBlogsByTfid() {
        return blogsByTfid;
    }

    public void setBlogsByTfid(Collection<BlogEntity> blogsByTfid) {
        this.blogsByTfid = blogsByTfid;
    }

    @OneToMany(mappedBy = "tfByIsCollected")
    public Collection<BlogCollectEntity> getBlogCollectsByTfid() {
        return blogCollectsByTfid;
    }

    public void setBlogCollectsByTfid(Collection<BlogCollectEntity> blogCollectsByTfid) {
        this.blogCollectsByTfid = blogCollectsByTfid;
    }

    @OneToMany(mappedBy = "tfByIsCollected")
    public Collection<ForumpostCollectEntity> getForumpostCollectsByTfid() {
        return forumpostCollectsByTfid;
    }

    public void setForumpostCollectsByTfid(Collection<ForumpostCollectEntity> forumpostCollectsByTfid) {
        this.forumpostCollectsByTfid = forumpostCollectsByTfid;
    }

    @OneToMany(mappedBy = "tfByIsLegal")
    public Collection<ForumpostFloorEntity> getForumpostFloorsByTfid() {
        return forumpostFloorsByTfid;
    }

    public void setForumpostFloorsByTfid(Collection<ForumpostFloorEntity> forumpostFloorsByTfid) {
        this.forumpostFloorsByTfid = forumpostFloorsByTfid;
    }

    @OneToMany(mappedBy = "tfByIsPraised")
    public Collection<PraiseBlogEntity> getPraiseBlogsByTfid() {
        return praiseBlogsByTfid;
    }

    public void setPraiseBlogsByTfid(Collection<PraiseBlogEntity> praiseBlogsByTfid) {
        this.praiseBlogsByTfid = praiseBlogsByTfid;
    }

    @OneToMany(mappedBy = "tfByIsPraised")
    public Collection<PraiseCommentEntity> getPraiseCommentsByTfid() {
        return praiseCommentsByTfid;
    }

    public void setPraiseCommentsByTfid(Collection<PraiseCommentEntity> praiseCommentsByTfid) {
        this.praiseCommentsByTfid = praiseCommentsByTfid;
    }

    @OneToMany(mappedBy = "tfByIsPraised")
    public Collection<PraiseForumpostEntity> getPraiseForumpostsByTfid() {
        return praiseForumpostsByTfid;
    }

    public void setPraiseForumpostsByTfid(Collection<PraiseForumpostEntity> praiseForumpostsByTfid) {
        this.praiseForumpostsByTfid = praiseForumpostsByTfid;
    }

    @OneToMany(mappedBy = "tfByIsPraised")
    public Collection<PraiseResourceEntity> getPraiseResourcesByTfid() {
        return praiseResourcesByTfid;
    }

    public void setPraiseResourcesByTfid(Collection<PraiseResourceEntity> praiseResourcesByTfid) {
        this.praiseResourcesByTfid = praiseResourcesByTfid;
    }

    @OneToMany(mappedBy = "tfByIsLegal")
    public Collection<ResourceEntity> getResourcesByTfid() {
        return resourcesByTfid;
    }

    public void setResourcesByTfid(Collection<ResourceEntity> resourcesByTfid) {
        this.resourcesByTfid = resourcesByTfid;
    }

    @OneToMany(mappedBy = "tfByIsCollected")
    public Collection<ResourceCollectEntity> getResourceCollectsByTfid() {
        return resourceCollectsByTfid;
    }

    public void setResourceCollectsByTfid(Collection<ResourceCollectEntity> resourceCollectsByTfid) {
        this.resourceCollectsByTfid = resourceCollectsByTfid;
    }

    @OneToMany(mappedBy = "tfByIsGotBlocked")
    public Collection<UserinfoEntity> getUserinfosByTfid() {
        return userinfosByTfid;
    }

    public void setUserinfosByTfid(Collection<UserinfoEntity> userinfosByTfid) {
        this.userinfosByTfid = userinfosByTfid;
    }
}
