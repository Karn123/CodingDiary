package model;

import javax.persistence.*;

/**
 * Created by Karn on 2017/2/14.
 */
@Entity
@Table(name = "forumpost_forward", schema = "CodingDiaryDB", catalog = "")
@IdClass(ForumpostForwardEntityPK.class)
public class ForumpostForwardEntity {
    private int forumpostId;
    private int forwarderId;
    private ForumpostEntity forumpostByForumpostId;
    private UserinfoEntity userinfoByForwarderId;

    @Id
    @Column(name = "forumpostID", nullable = false)
    public int getForumpostId() {
        return forumpostId;
    }

    public void setForumpostId(int forumpostId) {
        this.forumpostId = forumpostId;
    }

    @Id
    @Column(name = "forwarderID", nullable = false)
    public int getForwarderId() {
        return forwarderId;
    }

    public void setForwarderId(int forwarderId) {
        this.forwarderId = forwarderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ForumpostForwardEntity that = (ForumpostForwardEntity) o;

        if (forumpostId != that.forumpostId) return false;
        if (forwarderId != that.forwarderId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = forumpostId;
        result = 31 * result + forwarderId;
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
    @JoinColumn(name = "forwarderID", referencedColumnName = "userID", nullable = false)
    public UserinfoEntity getUserinfoByForwarderId() {
        return userinfoByForwarderId;
    }

    public void setUserinfoByForwarderId(UserinfoEntity userinfoByForwarderId) {
        this.userinfoByForwarderId = userinfoByForwarderId;
    }
}
