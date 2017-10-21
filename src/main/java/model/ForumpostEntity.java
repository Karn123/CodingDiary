package model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by Karn on 2017/2/14.
 */
@Entity
@Table(name = "forumpost", schema = "CodingDiaryDB", catalog = "")
public class ForumpostEntity {
    private int forumpostId;
    private String forumpostTitle;

    private ForumpostFloorEntity forumpostFloorByFirstFloorId;
    private Collection<ForumpostCollectEntity> forumpostCollectsByForumpostId;
    private Collection<ForumpostFloorCommentEntity> forumpostFloorCommentsByForumpostId;
    private Collection<ForumpostForwardEntity> forumpostForwardsByForumpostId;
    private Collection<ForumpostTagEntity> forumpostTagsByForumpostId;
    private Timestamp publishTime;
    private UserinfoEntity userinfoByAuthorId;
    private Integer collectNum;
    private ForumpostRecommendValueEntity forumpostRecommendValueByForumpostId;

    @OneToOne(mappedBy = "forumpostByForumpostId")
    public ForumpostRecommendValueEntity getForumpostRecommendValueByForumpostId() {
        return forumpostRecommendValueByForumpostId;
    }

    public void setForumpostRecommendValueByForumpostId(ForumpostRecommendValueEntity forumpostRecommendValueByForumpostId) {
        this.forumpostRecommendValueByForumpostId = forumpostRecommendValueByForumpostId;
    }

    @Basic
    @Column(name = "collectNum")
    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }

    @ManyToOne
    @JoinColumn(name = "authorId", referencedColumnName = "userID", nullable = false)
    public UserinfoEntity getUserinfoByAuthorId() {
        return userinfoByAuthorId;
    }

    public void setUserinfoByAuthorId(UserinfoEntity userinfoByAuthorId) {
        this.userinfoByAuthorId = userinfoByAuthorId;
    }

    @Basic
    @Column(name = "publishTime", nullable = false)
    public Timestamp getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Timestamp publishTime) {
        this.publishTime = publishTime;
    }

    @Id
    @Column(name = "forumpostId", nullable = false)
    public int getForumpostId() {
        return forumpostId;
    }

    public void setForumpostId(int forumpostId) {
        this.forumpostId = forumpostId;
    }

    @Basic
    @Column(name = "forumpostTitle", nullable = false, length = 100)
    public String getForumpostTitle() {
        return forumpostTitle;
    }

    public void setForumpostTitle(String forumpostTitle) {
        this.forumpostTitle = forumpostTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ForumpostEntity that = (ForumpostEntity) o;

        if (forumpostId != that.forumpostId) return false;
        if (forumpostTitle != null ? !forumpostTitle.equals(that.forumpostTitle) : that.forumpostTitle != null)
            return false;
        if (publishTime != null ? ! publishTime.equals(that.publishTime) : that.publishTime != null)
            return false;
        if (collectNum != null ? ! collectNum.equals(that.collectNum) : that.collectNum != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = forumpostId;
        result = 31 * result + (forumpostTitle != null ? forumpostTitle.hashCode() : 0);
        result = 31 * result + (publishTime != null ? publishTime.hashCode() : 0);
        result = 31 * result + (collectNum != null ? collectNum.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "firstFloorId", referencedColumnName = "floorID", nullable = false)
    public ForumpostFloorEntity getForumpostFloorByFirstFloorId() {
        return forumpostFloorByFirstFloorId;
    }

    public void setForumpostFloorByFirstFloorId(ForumpostFloorEntity forumpostFloorByFirstFloorId) {
        this.forumpostFloorByFirstFloorId = forumpostFloorByFirstFloorId;
    }

    @OneToMany(mappedBy = "forumpostByForumpostId")
    public Collection<ForumpostCollectEntity> getForumpostCollectsByForumpostId() {
        return forumpostCollectsByForumpostId;
    }

    public void setForumpostCollectsByForumpostId(Collection<ForumpostCollectEntity> forumpostCollectsByForumpostId) {
        this.forumpostCollectsByForumpostId = forumpostCollectsByForumpostId;
    }

    @OneToMany(mappedBy = "forumpostByCommentForumpostId")
    public Collection<ForumpostFloorCommentEntity> getForumpostFloorCommentsByForumpostId() {
        return forumpostFloorCommentsByForumpostId;
    }

    public void setForumpostFloorCommentsByForumpostId(Collection<ForumpostFloorCommentEntity> forumpostFloorCommentsByForumpostId) {
        this.forumpostFloorCommentsByForumpostId = forumpostFloorCommentsByForumpostId;
    }

    @OneToMany(mappedBy = "forumpostByForumpostId")
    public Collection<ForumpostForwardEntity> getForumpostForwardsByForumpostId() {
        return forumpostForwardsByForumpostId;
    }

    public void setForumpostForwardsByForumpostId(Collection<ForumpostForwardEntity> forumpostForwardsByForumpostId) {
        this.forumpostForwardsByForumpostId = forumpostForwardsByForumpostId;
    }

    @OneToMany(mappedBy = "forumpostByForumpostId")
    public Collection<ForumpostTagEntity> getForumpostTagsByForumpostId() {
        return forumpostTagsByForumpostId;
    }

    public void setForumpostTagsByForumpostId(Collection<ForumpostTagEntity> forumpostTagsByForumpostId) {
        this.forumpostTagsByForumpostId = forumpostTagsByForumpostId;
    }
}
