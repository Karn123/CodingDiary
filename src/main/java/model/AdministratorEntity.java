package model;

import javax.persistence.*;

/**
 * Created by Karn on 2017/2/14.
 */
@Entity
@Table(name = "administrator", schema = "CodingDiaryDB", catalog = "")
public class AdministratorEntity {
    private int id;
    private UserinfoEntity userinfoByAdminUserId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdministratorEntity that = (AdministratorEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "adminUserID", referencedColumnName = "userID", nullable = false)
    public UserinfoEntity getUserinfoByAdminUserId() {
        return userinfoByAdminUserId;
    }

    public void setUserinfoByAdminUserId(UserinfoEntity userinfoByAdminUserId) {
        this.userinfoByAdminUserId = userinfoByAdminUserId;
    }
}
