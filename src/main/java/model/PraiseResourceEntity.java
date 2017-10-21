package model;

import javax.persistence.*;

/**
 * Created by Karn on 2017/2/14.
 */
@Entity
@Table(name = "praise_resource", schema = "CodingDiaryDB", catalog = "")
@IdClass(PraiseResourceEntityPK.class)
public class PraiseResourceEntity {
    private int praiseFromUserId;
    private int praiseResourceId;
    private UserinfoEntity userinfoByPraiseFromUserId;
    private ResourceEntity resourceByPraiseResourceId;
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
    @Column(name = "praiseResourceID", nullable = false)
    public int getPraiseResourceId() {
        return praiseResourceId;
    }

    public void setPraiseResourceId(int praiseResourceId) {
        this.praiseResourceId = praiseResourceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PraiseResourceEntity that = (PraiseResourceEntity) o;

        if (praiseFromUserId != that.praiseFromUserId) return false;
        if (praiseResourceId != that.praiseResourceId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = praiseFromUserId;
        result = 31 * result + praiseResourceId;
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
    @JoinColumn(name = "praiseResourceID", referencedColumnName = "resourceID", nullable = false)
    public ResourceEntity getResourceByPraiseResourceId() {
        return resourceByPraiseResourceId;
    }

    public void setResourceByPraiseResourceId(ResourceEntity resourceByPraiseResourceId) {
        this.resourceByPraiseResourceId = resourceByPraiseResourceId;
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
