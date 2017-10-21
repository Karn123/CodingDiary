package model;

import javax.persistence.*;

/**
 * Created by Karn on 2017/3/6.
 */
@Entity
@Table(name = "user_interest", schema = "CodingDiaryDB", catalog = "")
@IdClass(UserInterestEntityPK.class)
public class UserInterestEntity {
    private int userId;
    private int tagId;
    private int interestCount;
    private int chance;
    private UserinfoEntity userinfoByUserId;
    private TagEntity tagByTagId;

    @Id
    @Column(name = "userID", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
    @Column(name = "interestCount", nullable = false)
    public int getInterestCount() {
        return interestCount;
    }

    public void setInterestCount(int interestCount) {
        this.interestCount = interestCount;
    }

    @Basic
    @Column(name = "chance", nullable = false)
    public int getChance() {
        return chance;
    }

    public void setChance(int chance) {
        this.chance = chance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInterestEntity that = (UserInterestEntity) o;

        if (userId != that.userId) return false;
        if (tagId != that.tagId) return false;
        if (interestCount != that.interestCount) return false;
        if (chance != that.chance) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + tagId;
        result = 31 * result + interestCount;
        result = 31 * result + chance;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "userID", referencedColumnName = "userID", nullable = false)
    public UserinfoEntity getUserinfoByUserId() {
        return userinfoByUserId;
    }

    public void setUserinfoByUserId(UserinfoEntity userinfoByUserId) {
        this.userinfoByUserId = userinfoByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "tagID", referencedColumnName = "tagID", nullable = false)
    public TagEntity getTagByTagId() {
        return tagByTagId;
    }

    public void setTagByTagId(TagEntity tagByTagId) {
        this.tagByTagId = tagByTagId;
    }
}
