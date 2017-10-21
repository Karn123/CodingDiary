package model;

import javax.persistence.*;

/**
 * Created by Karn on 2017/2/14.
 */
@Entity
@Table(name = "praise_blog", schema = "CodingDiaryDB", catalog = "")
@IdClass(PraiseBlogEntityPK.class)
public class PraiseBlogEntity {
    private int praiseFromUserId;
    private int praiseBlogId;
    private UserinfoEntity userinfoByPraiseFromUserId;
    private BlogEntity blogByPraiseBlogId;
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
    @Column(name = "praiseBlogID", nullable = false)
    public int getPraiseBlogId() {
        return praiseBlogId;
    }

    public void setPraiseBlogId(int praiseBlogId) {
        this.praiseBlogId = praiseBlogId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PraiseBlogEntity that = (PraiseBlogEntity) o;

        if (praiseFromUserId != that.praiseFromUserId) return false;
        if (praiseBlogId != that.praiseBlogId) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = praiseFromUserId;
        result = 31 * result + praiseBlogId;
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
    @JoinColumn(name = "praiseBlogID", referencedColumnName = "blogID", nullable = false)
    public BlogEntity getBlogByPraiseBlogId() {
        return blogByPraiseBlogId;
    }

    public void setBlogByPraiseBlogId(BlogEntity blogByPraiseBlogId) {
        this.blogByPraiseBlogId = blogByPraiseBlogId;
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
