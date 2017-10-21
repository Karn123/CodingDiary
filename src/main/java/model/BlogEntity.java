package model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by Karn on 2017/2/14.
 */
@Entity
@Table(name = "blog", schema = "CodingDiaryDB", catalog = "")
public class BlogEntity {
    private int blogId;
    private String blogTitle;
    private Integer praiseNum;
    private Integer commentNum;
    private Integer forwardNum;
    private Integer browseNum;
    private Timestamp publishTime;
    private Integer collectNum;
    private UserinfoEntity userinfoByAuthorId;
    private TfEntity tfByIsLegal;
    private Collection<BlogCollectEntity> blogCollectsByBlogId;
    private Collection<BlogCommentEntity> blogCommentsByBlogId;
    private BlogContentEntity blogContentByBlogId;
    private Collection<BlogForwardEntity> blogForwardsByBlogId;
    private Collection<BlogTagEntity> blogTagsByBlogId;
    private Collection<PraiseBlogEntity> praiseBlogsByBlogId;
    private BlogRecommendValueEntity blogRecommendValueByBlogId;

    @OneToOne(mappedBy = "blogByBlogId")
    public BlogRecommendValueEntity getBlogRecommendValueByBlogId() {
        return blogRecommendValueByBlogId;
    }

    public void setBlogRecommendValueByBlogId(BlogRecommendValueEntity blogRecommendValueByBlogId) {
        this.blogRecommendValueByBlogId = blogRecommendValueByBlogId;
    }

    public BlogEntity() {
    }

    @Id
    @Column(name = "blogID", nullable = false)
    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public BlogEntity(String blogTitle,Timestamp publishTime) {
        this.blogTitle = blogTitle;
        this.publishTime = publishTime;
    }

    @Basic
    @Column(name = "blogTitle", nullable = false, length = 100)
    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    @Basic
    @Column(name = "praiseNum", nullable = true)
    public Integer getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }

    @Basic
    @Column(name = "commentNum", nullable = true)
    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    @Basic
    @Column(name = "forwardNum", nullable = true)
    public Integer getForwardNum() {
        return forwardNum;
    }

    public void setForwardNum(Integer forwardNum) {
        this.forwardNum = forwardNum;
    }

    @Basic
    @Column(name = "browseNum", nullable = true)
    public Integer getBrowseNum() {
        return browseNum;
    }

    public void setBrowseNum(Integer browseNum) {
        this.browseNum = browseNum;
    }

    @Basic
    @Column(name = "publishTime", nullable = false)
    public Timestamp getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Timestamp publishTime) {
        this.publishTime = publishTime;
    }

    @Basic
    @Column(name = "collectNum", nullable = true)
    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlogEntity that = (BlogEntity) o;

        if (blogId != that.blogId) return false;
        if (blogTitle != null ? !blogTitle.equals(that.blogTitle) : that.blogTitle != null) return false;
        if (praiseNum != null ? !praiseNum.equals(that.praiseNum) : that.praiseNum != null) return false;
        if (commentNum != null ? !commentNum.equals(that.commentNum) : that.commentNum != null) return false;
        if (forwardNum != null ? !forwardNum.equals(that.forwardNum) : that.forwardNum != null) return false;
        if (browseNum != null ? !browseNum.equals(that.browseNum) : that.browseNum != null) return false;
        if (publishTime != null ? !publishTime.equals(that.publishTime) : that.publishTime != null) return false;
        if (collectNum != null ? !collectNum.equals(that.collectNum) : that.collectNum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = blogId;
        result = 31 * result + (blogTitle != null ? blogTitle.hashCode() : 0);
        result = 31 * result + (praiseNum != null ? praiseNum.hashCode() : 0);
        result = 31 * result + (commentNum != null ? commentNum.hashCode() : 0);
        result = 31 * result + (forwardNum != null ? forwardNum.hashCode() : 0);
        result = 31 * result + (browseNum != null ? browseNum.hashCode() : 0);
        result = 31 * result + (publishTime != null ? publishTime.hashCode() : 0);
        result = 31 * result + (collectNum != null ? collectNum.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "authorID", referencedColumnName = "userID", nullable = false)
    public UserinfoEntity getUserinfoByAuthorId() {
        return userinfoByAuthorId;
    }

    public void setUserinfoByAuthorId(UserinfoEntity userinfoByAuthorId) {
        this.userinfoByAuthorId = userinfoByAuthorId;
    }

    @ManyToOne
    @JoinColumn(name = "isLegal", referencedColumnName = "TFID")
    public TfEntity getTfByIsLegal() {
        return tfByIsLegal;
    }

    public void setTfByIsLegal(TfEntity tfByIsLegal) {
        this.tfByIsLegal = tfByIsLegal;
    }

    @OneToMany(mappedBy = "blogByCollectBlogId")
    public Collection<BlogCollectEntity> getBlogCollectsByBlogId() {
        return blogCollectsByBlogId;
    }

    public void setBlogCollectsByBlogId(Collection<BlogCollectEntity> blogCollectsByBlogId) {
        this.blogCollectsByBlogId = blogCollectsByBlogId;
    }

    @OneToMany(mappedBy = "blogByCommentBlogId")
    public Collection<BlogCommentEntity> getBlogCommentsByBlogId() {
        return blogCommentsByBlogId;
    }

    public void setBlogCommentsByBlogId(Collection<BlogCommentEntity> blogCommentsByBlogId) {
        this.blogCommentsByBlogId = blogCommentsByBlogId;
    }

    @OneToOne(mappedBy = "blogByBlogId")
    public BlogContentEntity getBlogContentByBlogId() {
        return blogContentByBlogId;
    }

    public void setBlogContentByBlogId(BlogContentEntity blogContentByBlogId) {
        this.blogContentByBlogId = blogContentByBlogId;
    }

    @OneToMany(mappedBy = "blogByForwardBlogId")
    public Collection<BlogForwardEntity> getBlogForwardsByBlogId() {
        return blogForwardsByBlogId;
    }

    public void setBlogForwardsByBlogId(Collection<BlogForwardEntity> blogForwardsByBlogId) {
        this.blogForwardsByBlogId = blogForwardsByBlogId;
    }

    @OneToMany(mappedBy = "blogByBlogIdNum")
    public Collection<BlogTagEntity> getBlogTagsByBlogId() {
        return blogTagsByBlogId;
    }

    public void setBlogTagsByBlogId(Collection<BlogTagEntity> blogTagsByBlogId) {
        this.blogTagsByBlogId = blogTagsByBlogId;
    }

    @OneToMany(mappedBy = "blogByPraiseBlogId")
    public Collection<PraiseBlogEntity> getPraiseBlogsByBlogId() {
        return praiseBlogsByBlogId;
    }

    public void setPraiseBlogsByBlogId(Collection<PraiseBlogEntity> praiseBlogsByBlogId) {
        this.praiseBlogsByBlogId = praiseBlogsByBlogId;
    }
}
