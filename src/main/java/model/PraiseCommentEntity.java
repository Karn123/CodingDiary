package model;

import javax.persistence.*;

/**
 * Created by Karn on 2017/2/14.
 */
@Entity
@Table(name = "praise_comment", schema = "CodingDiaryDB", catalog = "")
@IdClass(PraiseCommentEntityPK.class)
public class PraiseCommentEntity {
    private int praiseFromUserId;
    private int praiseCommentId;
    private UserinfoEntity userinfoByPraiseFromUserId;
    private UsercommentEntity usercommentByPraiseCommentId;
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
    @Column(name = "praiseCommentID", nullable = false)
    public int getPraiseCommentId() {
        return praiseCommentId;
    }

    public void setPraiseCommentId(int praiseCommentId) {
        this.praiseCommentId = praiseCommentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PraiseCommentEntity that = (PraiseCommentEntity) o;

        if (praiseFromUserId != that.praiseFromUserId) return false;
        if (praiseCommentId != that.praiseCommentId) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = praiseFromUserId;
        result = 31 * result + praiseCommentId;
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
    @JoinColumn(name = "praiseCommentID", referencedColumnName = "commentID", nullable = false)
    public UsercommentEntity getUsercommentByPraiseCommentId() {
        return usercommentByPraiseCommentId;
    }

    public void setUsercommentByPraiseCommentId(UsercommentEntity usercommentByPraiseCommentId) {
        this.usercommentByPraiseCommentId = usercommentByPraiseCommentId;
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
