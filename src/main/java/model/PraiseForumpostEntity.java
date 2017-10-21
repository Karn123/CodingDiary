package model;

import javax.persistence.*;

/**
 * Created by Karn on 2017/2/14.
 */
@Entity
@Table(name = "praise_forumpost", schema = "CodingDiaryDB", catalog = "")
@IdClass(PraiseForumpostEntityPK.class)
public class PraiseForumpostEntity {
    private int praiseFromUserId;
    private int praiseForumpostFloorId;
    private UserinfoEntity userinfoByPraiseFromUserId;
    private ForumpostFloorEntity forumpostFloorByPraiseForumpostFloorId;
    private TfEntity tfByIsPraised;

    @Id
    @Column(name = "praiseFromUserID", nullable = false)
    public int getPraiseFromUserId() {
        return praiseFromUserId;
    }

    public void setPraiseFromUserId(int praiseFromUserId) {
        this.praiseFromUserId = praiseFromUserId;
    }

    @Id
    @Column(name = "praiseForumpostFloorID", nullable = false)
    public int getPraiseForumpostFloorId() {
        return praiseForumpostFloorId;
    }

    public void setPraiseForumpostFloorId(int praiseForumpostId) {
        this.praiseForumpostFloorId = praiseForumpostId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PraiseForumpostEntity that = (PraiseForumpostEntity) o;

        if (praiseFromUserId != that.praiseFromUserId) return false;
        if (praiseForumpostFloorId != that.praiseForumpostFloorId) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = praiseFromUserId;
        result = 31 * result + praiseForumpostFloorId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "praiseFromUserID", referencedColumnName = "userID", nullable = false)
    public UserinfoEntity getUserinfoByPraiseFromUserId() {
        return userinfoByPraiseFromUserId;
    }

    public void setUserinfoByPraiseFromUserId(UserinfoEntity userinfoByPraiseFromUserId) {
        this.userinfoByPraiseFromUserId = userinfoByPraiseFromUserId;
    }

    @ManyToOne
    @JoinColumn(name = "praiseForumpostFloorID", referencedColumnName = "floorID", nullable = false)
    public ForumpostFloorEntity getForumpostFloorByPraiseForumpostFloorId() {
        return forumpostFloorByPraiseForumpostFloorId;
    }

    public void setForumpostFloorByPraiseForumpostFloorId(ForumpostFloorEntity forumpostFloorByPraiseForumpostId) {
        this.forumpostFloorByPraiseForumpostFloorId = forumpostFloorByPraiseForumpostId;
    }

    @ManyToOne
    @JoinColumn(name = "isPraised", referencedColumnName = "TFID")
    public TfEntity getTfByIsPraised() {
        return tfByIsPraised;
    }

    public void setTfByIsPraised(TfEntity tfByIsPraised) {
        this.tfByIsPraised = tfByIsPraised;
    }
}
