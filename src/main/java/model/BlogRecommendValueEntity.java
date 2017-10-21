package model;

import javax.persistence.*;

/**
 * Created by Karn on 2017/3/6.
 */
@Entity
@Table(name = "blog_recommend_value", schema = "CodingDiaryDB", catalog = "")
public class BlogRecommendValueEntity {
    private int blogId;
    private double browseValue;
    private double collectValue;
    private double praiseValue;
    private double forwardValue;
    private double totalValue;
    private double todayValue;
    private BlogEntity blogByBlogId;
    private TfEntity tfByIsNew;

    @Id
    @Column(name = "blogID", nullable = false)
    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    @Basic
    @Column(name = "browseValue", nullable = false, precision = 0)
    public double getBrowseValue() {
        return browseValue;
    }

    public void setBrowseValue(double browseValue) {
        this.browseValue = browseValue;
    }

    @Basic
    @Column(name = "collectValue", nullable = false, precision = 0)
    public double getCollectValue() {
        return collectValue;
    }

    public void setCollectValue(double collectValue) {
        this.collectValue = collectValue;
    }

    @Basic
    @Column(name = "praiseValue", nullable = false, precision = 0)
    public double getPraiseValue() {
        return praiseValue;
    }

    public void setPraiseValue(double praiseValue) {
        this.praiseValue = praiseValue;
    }

    @Basic
    @Column(name = "forwardValue", nullable = false, precision = 0)
    public double getForwardValue() {
        return forwardValue;
    }

    public void setForwardValue(double forwardValue) {
        this.forwardValue = forwardValue;
    }

    @Basic
    @Column(name = "totalValue", nullable = false, precision = 0)
    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    @Basic
    @Column(name = "todayValue", nullable = false, precision = 0)
    public double getTodayValue() {
        return todayValue;
    }

    public void setTodayValue(double todayValue) {
        this.todayValue = todayValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlogRecommendValueEntity that = (BlogRecommendValueEntity) o;

        if (blogId != that.blogId) return false;
        if (Double.compare(that.browseValue, browseValue) != 0) return false;
        if (Double.compare(that.collectValue, collectValue) != 0) return false;
        if (Double.compare(that.praiseValue, praiseValue) != 0) return false;
        if (Double.compare(that.forwardValue, forwardValue) != 0) return false;
        if (Double.compare(that.totalValue, totalValue) != 0) return false;
        if (Double.compare(that.todayValue, todayValue) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = blogId;
        temp = Double.doubleToLongBits(browseValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(collectValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(praiseValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(forwardValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(totalValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(todayValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @OneToOne
    @JoinColumn(name = "blogID", referencedColumnName = "blogID", nullable = false)
    public BlogEntity getBlogByBlogId() {
        return blogByBlogId;
    }

    public void setBlogByBlogId(BlogEntity blogByBlogId) {
        this.blogByBlogId = blogByBlogId;
    }

    @ManyToOne
    @JoinColumn(name = "isNew", referencedColumnName = "TFID", nullable = false)
    public TfEntity getTfByIsNew() {
        return tfByIsNew;
    }

    public void setTfByIsNew(TfEntity tfByIsNew) {
        this.tfByIsNew = tfByIsNew;
    }
}
