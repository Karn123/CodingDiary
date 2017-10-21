package model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by Karn on 2017/2/14.
 */
@Entity
@Table(name = "forumpost_floor", schema = "CodingDiaryDB", catalog = "")
public class ForumpostFloorEntity {
    private int floorId;
    private String floorContent;
    private Integer praiseNum;
    private Integer forwardNum;
    private Integer commentNum;
    private Integer browseNum;
    private Timestamp publishTime;
    private Integer collectNum;
    private Collection<ForumpostEntity> forumpostsByFloorId;
    private UserinfoEntity userinfoByAuthorId;
    private TfEntity tfByIsLegal;
    private Collection<ForumpostFloorCommentEntity> forumpostFloorCommentsByFloorId;
    private Collection<PraiseForumpostEntity> praiseForumpostsByFloorId;
    private ForumpostFloorEntity nextFloorByFloorID;

    @ManyToOne
    @JoinColumn(name = "nextFloorID", referencedColumnName = "floorID")
    public ForumpostFloorEntity getNextFloorByFloorID() {
        return nextFloorByFloorID;
    }

    public void setNextFloorByFloorID(ForumpostFloorEntity nextFloorByFloorID) {
        this.nextFloorByFloorID = nextFloorByFloorID;
    }

    @Id
    @Column(name = "floorID", nullable = false)
    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }

    @Basic
    @Column(name = "floorContent", nullable = false, length = -1)
    public String getFloorContent() {
        return floorContent;
    }

    public void setFloorContent(String floorContent) {
        this.floorContent = floorContent;
    }

    @Basic
    @Column(name = "praiseNum", nullable = true)
    public Integer getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }

    @Basic
    @Column(name = "forwardNum", nullable = true)
    public Integer getForwardNum() {
        return forwardNum;
    }

    public void setForwardNum(Integer forwardNum) {
        this.forwardNum = forwardNum;
    }

    @Basic
    @Column(name = "commentNum", nullable = true)
    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    @Basic
    @Column(name = "browseNum", nullable = true)
    public Integer getBrowseNum() {
        return browseNum;
    }

    public void setBrowseNum(Integer browseNum) {
        this.browseNum = browseNum;
    }

    @Basic
    @Column(name = "publishTime", nullable = false)
    public Timestamp getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Timestamp publishTime) {
        this.publishTime = publishTime;
    }

    @Basic
    @Column(name = "collectNum", nullable = true)
    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ForumpostFloorEntity that = (ForumpostFloorEntity) o;

        if (floorId != that.floorId) return false;
        if (floorContent != null ? !floorContent.equals(that.floorContent) : that.floorContent != null) return false;
        if (praiseNum != null ? !praiseNum.equals(that.praiseNum) : that.praiseNum != null) return false;
        if (forwardNum != null ? !forwardNum.equals(that.forwardNum) : that.forwardNum != null) return false;
        if (commentNum != null ? !commentNum.equals(that.commentNum) : that.commentNum != null) return false;
        if (browseNum != null ? !browseNum.equals(that.browseNum) : that.browseNum != null) return false;
        if (publishTime != null ? !publishTime.equals(that.publishTime) : that.publishTime != null) return false;
        if (collectNum != null ? !collectNum.equals(that.collectNum) : that.collectNum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = floorId;
        result = 31 * result + (floorContent != null ? floorContent.hashCode() : 0);
        result = 31 * result + (praiseNum != null ? praiseNum.hashCode() : 0);
        result = 31 * result + (forwardNum != null ? forwardNum.hashCode() : 0);
        result = 31 * result + (commentNum != null ? commentNum.hashCode() : 0);
        result = 31 * result + (browseNum != null ? browseNum.hashCode() : 0);
        result = 31 * result + (publishTime != null ? publishTime.hashCode() : 0);
        result = 31 * result + (collectNum != null ? collectNum.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "forumpostFloorByFirstFloorId")
    public Collection<ForumpostEntity> getForumpostsByFloorId() {
        return forumpostsByFloorId;
    }

    public void setForumpostsByFloorId(Collection<ForumpostEntity> forumpostsByFloorId) {
        this.forumpostsByFloorId = forumpostsByFloorId;
    }

    @ManyToOne
    @JoinColumn(name = "authorID", referencedColumnName = "userID", nullable = false)
    public UserinfoEntity getUserinfoByAuthorId() {
        return userinfoByAuthorId;
    }

    public void setUserinfoByAuthorId(UserinfoEntity userinfoByAuthorId) {
        this.userinfoByAuthorId = userinfoByAuthorId;
    }

    @ManyToOne
    @JoinColumn(name = "isLegal", referencedColumnName = "TFID")
    public TfEntity getTfByIsLegal() {
        return tfByIsLegal;
    }

    public void setTfByIsLegal(TfEntity tfByIsLegal) {
        this.tfByIsLegal = tfByIsLegal;
    }

    @OneToMany(mappedBy = "forumpostFloorByCommentToFloorId")
    public Collection<ForumpostFloorCommentEntity> getForumpostFloorCommentsByFloorId() {
        return forumpostFloorCommentsByFloorId;
    }

    public void setForumpostFloorCommentsByFloorId(Collection<ForumpostFloorCommentEntity> forumpostFloorCommentsByFloorId) {
        this.forumpostFloorCommentsByFloorId = forumpostFloorCommentsByFloorId;
    }

    @OneToMany(mappedBy = "forumpostFloorByPraiseForumpostFloorId")
    public Collection<PraiseForumpostEntity> getPraiseForumpostsByFloorId() {
        return praiseForumpostsByFloorId;
    }

    public void setPraiseForumpostsByFloorId(Collection<PraiseForumpostEntity> praiseForumpostsByFloorId) {
        this.praiseForumpostsByFloorId = praiseForumpostsByFloorId;
    }
}
