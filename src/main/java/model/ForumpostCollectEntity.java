package model;

import javax.persistence.*;

/**
 * Created by Karn on 2017/2/14.
 */
@Entity
@Table(name = "forumpost_collect", schema = "CodingDiaryDB", catalog = "")
@IdClass(ForumpostCollectEntityPK.class)
public class ForumpostCollectEntity {
    private int forumpostId;
    private int collectorId;
    private ForumpostEntity forumpostByForumpostId;
    private UserinfoEntity userinfoByCollectorId;
    private TfEntity tfByIsCollected;

    @Id
    @Column(name = "forumpostID", nullable = false)
    public int getForumpostId() {
        return forumpostId;
    }

    public void setForumpostId(int forumpostId) {
        this.forumpostId = forumpostId;
    }

    @Id
    @Column(name = "collectorID", nullable = false)
    public int getCollectorId() {
        return collectorId;
    }

    public void setCollectorId(int collectorId) {
        this.collectorId = collectorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ForumpostCollectEntity that = (ForumpostCollectEntity) o;

        if (forumpostId != that.forumpostId) return false;
        if (collectorId != that.collectorId) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = forumpostId;
        result = 31 * result + collectorId;
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
    @JoinColumn(name = "collectorID", referencedColumnName = "userID", nullable = false)
    public UserinfoEntity getUserinfoByCollectorId() {
        return userinfoByCollectorId;
    }

    public void setUserinfoByCollectorId(UserinfoEntity userinfoByCollectorId) {
        this.userinfoByCollectorId = userinfoByCollectorId;
    }

    @ManyToOne
    @JoinColumn(name = "isCollected", referencedColumnName = "TFID")
    public TfEntity getTfByIsCollected() {
        return tfByIsCollected;
    }

    public void setTfByIsCollected(TfEntity tfByIsCollected) {
        this.tfByIsCollected = tfByIsCollected;
    }
}
