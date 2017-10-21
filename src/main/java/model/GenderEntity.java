package model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Karn on 2017/2/14.
 */
@Entity
@Table(name = "gender", schema = "CodingDiaryDB", catalog = "")
public class GenderEntity {
    private int genderId;
    private String genderName;
    private Collection<UserinfoEntity> userinfosByGenderId;

    @Id
    @Column(name = "genderID", nullable = false)
    public int getGenderId() {
        return genderId;
    }

    public void setGenderId(int genderId) {
        this.genderId = genderId;
    }

    @Basic
    @Column(name = "genderName", nullable = false, length = 1)
    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GenderEntity that = (GenderEntity) o;

        if (genderId != that.genderId) return false;
        if (genderName != null ? !genderName.equals(that.genderName) : that.genderName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = genderId;
        result = 31 * result + (genderName != null ? genderName.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "genderBySex")
    public Collection<UserinfoEntity> getUserinfosByGenderId() {
        return userinfosByGenderId;
    }

    public void setUserinfosByGenderId(Collection<UserinfoEntity> userinfosByGenderId) {
        this.userinfosByGenderId = userinfosByGenderId;
    }
}
