package model;

import javax.persistence.*;

/**
 * Created by Karn on 2017/2/14.
 */
@Entity
@Table(name = "userfans", schema = "CodingDiaryDB", catalog = "")
@IdClass(UserfansEntityPK.class)
public class UserfansEntity {
    private int userIdNum;
    private int fanId;
    private UserinfoEntity userinfoByUserIdNum;
    private UserinfoEntity userinfoByFanId;

    @Id
    @Column(name = "userIDNum", nullable = false)
    public int getUserIdNum() {
        return userIdNum;
    }

    public void setUserIdNum(int userIdNum) {
        this.userIdNum = userIdNum;
    }

    @Id
    @Column(name = "fanID", nullable = false)
    public int getFanId() {
        return fanId;
    }

    public void setFanId(int fanId) {
        this.fanId = fanId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserfansEntity that = (UserfansEntity) o;

        if (userIdNum != that.userIdNum) return false;
        if (fanId != that.fanId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userIdNum;
        result = 31 * result + fanId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "userIDNum", referencedColumnName = "userID", nullable = false)
    public UserinfoEntity getUserinfoByUserIdNum() {
        return userinfoByUserIdNum;
    }

    public void setUserinfoByUserIdNum(UserinfoEntity userinfoByUserIdNum) {
        this.userinfoByUserIdNum = userinfoByUserIdNum;
    }

    @ManyToOne
    @JoinColumn(name = "fanID", referencedColumnName = "userID", nullable = false)
    public UserinfoEntity getUserinfoByFanId() {
        return userinfoByFanId;
    }

    public void setUserinfoByFanId(UserinfoEntity userinfoByFanId) {
        this.userinfoByFanId = userinfoByFanId;
    }
}
