package model;

import javax.persistence.*;

/**
 * Created by Karn on 2017/3/6.
 */
@Entity
@Table(name = "user_parameter", schema = "CodingDiaryDB", catalog = "")
public class UserParameterEntity {
    private int userId;
    private double browseParameter;
    private double collectParameter;
    private double praiseParameter;
    private double forwardParameter;
    private UserinfoEntity userinfoByUserId;

    @Id
    @Column(name = "userID", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "browseParameter", nullable = false, precision = 0)
    public double getBrowseParameter() {
        return browseParameter;
    }

    public void setBrowseParameter(double browseParameter) {
        this.browseParameter = browseParameter;
    }

    @Basic
    @Column(name = "collectParameter", nullable = false, precision = 0)
    public double getCollectParameter() {
        return collectParameter;
    }

    public void setCollectParameter(double collectParameter) {
        this.collectParameter = collectParameter;
    }

    @Basic
    @Column(name = "praiseParameter", nullable = false, precision = 0)
    public double getPraiseParameter() {
        return praiseParameter;
    }

    public void setPraiseParameter(double praiseParameter) {
        this.praiseParameter = praiseParameter;
    }

    @Basic
    @Column(name = "forwardParameter", nullable = false, precision = 0)
    public double getForwardParameter() {
        return forwardParameter;
    }

    public void setForwardParameter(double forwardParameter) {
        this.forwardParameter = forwardParameter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserParameterEntity that = (UserParameterEntity) o;

        if (userId != that.userId) return false;
        if (Double.compare(that.browseParameter, browseParameter) != 0) return false;
        if (Double.compare(that.collectParameter, collectParameter) != 0) return false;
        if (Double.compare(that.praiseParameter, praiseParameter) != 0) return false;
        if (Double.compare(that.forwardParameter, forwardParameter) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = userId;
        temp = Double.doubleToLongBits(browseParameter);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(collectParameter);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(praiseParameter);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(forwardParameter);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @OneToOne
    @JoinColumn(name = "userID", referencedColumnName = "userID", nullable = false)
    public UserinfoEntity getUserinfoByUserId() {
        return userinfoByUserId;
    }

    public void setUserinfoByUserId(UserinfoEntity userinfoByUserId) {
        this.userinfoByUserId = userinfoByUserId;
    }
}
