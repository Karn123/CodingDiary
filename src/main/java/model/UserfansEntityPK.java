package model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Karn on 2017/2/14.
 */
public class UserfansEntityPK implements Serializable {
    private int userIdNum;
    private int fanId;

    @Column(name = "userIDNum", nullable = false)
    @Id
    public int getUserIdNum() {
        return userIdNum;
    }

    public void setUserIdNum(int userIdNum) {
        this.userIdNum = userIdNum;
    }

    @Column(name = "fanID", nullable = false)
    @Id
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

        UserfansEntityPK that = (UserfansEntityPK) o;

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
}
