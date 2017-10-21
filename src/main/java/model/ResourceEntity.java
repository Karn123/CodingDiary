package model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by Karn on 2017/2/14.
 */
@Entity
@Table(name = "resource", schema = "CodingDiaryDB", catalog = "")
public class ResourceEntity {
    private int resourceId;
    private String resourceName;
    private String resourceDescription;
    private String resourcePath;
    private Integer praiseNum;
    private Integer commentNum;
    private Timestamp uploadTime;
    private Integer downloadCount;
    private Integer forwardNum;
    private Integer collectNum;
    private Integer browseNum;
    private Collection<PraiseResourceEntity> praiseResourcesByResourceId;
    private UserinfoEntity userinfoByUploaderId;
    private TfEntity tfByIsLegal;
    private Collection<ResourceCollectEntity> resourceCollectsByResourceId;
    private Collection<ResourceCommentEntity> resourceCommentsByResourceId;
    private Collection<ResourceForwardEntity> resourceForwardsByResourceId;
    private Collection<ResourceTagEntity> resourceTagsByResourceId;
    private Double resourceScore;
    private ResourceRecommendValueEntity resourceRecommendValueByResourceId;
    private String resourceSize;

    @Basic
    @Column(name = "resourceSize")
    public String getResourceSize() {
        return resourceSize;
    }

    public void setResourceSize(String resourceSize) {
        this.resourceSize = resourceSize;
    }

    @OneToOne(mappedBy = "resourceByResourceId")
    public ResourceRecommendValueEntity getResourceRecommendValueByResourceId() {
        return resourceRecommendValueByResourceId;
    }

    public void setResourceRecommendValueByResourceId(ResourceRecommendValueEntity resourceRecommendValueByResourceId) {
        this.resourceRecommendValueByResourceId = resourceRecommendValueByResourceId;
    }

    @Basic
    @Column(name = "resourceScore")
    public Double getResourceScore() {
        return resourceScore;
    }

    public void setResourceScore(Double resourceScore) {
        this.resourceScore = resourceScore;
    }


    public ResourceEntity(UserinfoEntity userinfoEntity, String resourceName, String resourceDescription, String resourceSize,String resourcePath, Timestamp timestamp) {
        this.userinfoByUploaderId = userinfoEntity;
        this.resourceName = resourceName;
        this.resourceDescription = resourceDescription;
        this.resourceSize = resourceSize;
        this.resourcePath = resourcePath;
        this.uploadTime = timestamp;
    }

    public ResourceEntity(){}

    @Id
    @Column(name = "resourceID", nullable = false)
    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    @Basic
    @Column(name = "resourceName", nullable = false, length = 300)
    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    @Basic
    @Column(name = "resourceDescription", nullable = false, length = 800)
    public String getResourceDescription() {
        return resourceDescription;
    }

    public void setResourceDescription(String resourceDescription) {
        this.resourceDescription = resourceDescription;
    }

    @Basic
    @Column(name = "resourcePath", nullable = false, length = 800)
    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
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
    @Column(name = "uploadTime", nullable = false)
    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Timestamp uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Basic
    @Column(name = "downloadCount", nullable = true)
    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
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
    @Column(name = "collectNum", nullable = true)
    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }

    @Basic
    @Column(name = "browseNum", nullable = true)
    public Integer getBrowseNum() {
        return browseNum;
    }

    public void setBrowseNum(Integer browseNum) {
        this.browseNum = browseNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourceEntity that = (ResourceEntity) o;

        if (resourceId != that.resourceId) return false;
        if (resourceName != null ? !resourceName.equals(that.resourceName) : that.resourceName != null) return false;
        if (resourceDescription != null ? !resourceDescription.equals(that.resourceDescription) : that.resourceDescription != null)
            return false;
        if (resourcePath != null ? !resourcePath.equals(that.resourcePath) : that.resourcePath != null) return false;
        if (praiseNum != null ? !praiseNum.equals(that.praiseNum) : that.praiseNum != null) return false;
        if (commentNum != null ? !commentNum.equals(that.commentNum) : that.commentNum != null) return false;
        if (uploadTime != null ? !uploadTime.equals(that.uploadTime) : that.uploadTime != null) return false;
        if (downloadCount != null ? !downloadCount.equals(that.downloadCount) : that.downloadCount != null)
            return false;
        if (forwardNum != null ? !forwardNum.equals(that.forwardNum) : that.forwardNum != null) return false;
        if (collectNum != null ? !collectNum.equals(that.collectNum) : that.collectNum != null) return false;
        if (browseNum != null ? !browseNum.equals(that.browseNum) : that.browseNum != null) return false;
        if (resourceScore != null ? !resourceScore.equals(that.resourceScore) : that.resourceScore != null)
            return false;
        if (resourceSize != null ? !resourceSize.equals(that.resourceSize) : that.resourceSize != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = resourceId;
        result = 31 * result + (resourceName != null ? resourceName.hashCode() : 0);
        result = 31 * result + (resourceDescription != null ? resourceDescription.hashCode() : 0);
        result = 31 * result + (resourcePath != null ? resourcePath.hashCode() : 0);
        result = 31 * result + (praiseNum != null ? praiseNum.hashCode() : 0);
        result = 31 * result + (commentNum != null ? commentNum.hashCode() : 0);
        result = 31 * result + (uploadTime != null ? uploadTime.hashCode() : 0);
        result = 31 * result + (downloadCount != null ? downloadCount.hashCode() : 0);
        result = 31 * result + (forwardNum != null ? forwardNum.hashCode() : 0);
        result = 31 * result + (collectNum != null ? collectNum.hashCode() : 0);
        result = 31 * result + (browseNum != null ? browseNum.hashCode() : 0);
        result = 31 * result + (resourceScore != null ? resourceScore.hashCode() : 0);
        result = 31 * result + (resourceSize != null ? resourceSize.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "resourceByPraiseResourceId")
    public Collection<PraiseResourceEntity> getPraiseResourcesByResourceId() {
        return praiseResourcesByResourceId;
    }

    public void setPraiseResourcesByResourceId(Collection<PraiseResourceEntity> praiseResourcesByResourceId) {
        this.praiseResourcesByResourceId = praiseResourcesByResourceId;
    }

    @ManyToOne
    @JoinColumn(name = "uploaderID", referencedColumnName = "userID", nullable = false)
    public UserinfoEntity getUserinfoByUploaderId() {
        return userinfoByUploaderId;
    }

    public void setUserinfoByUploaderId(UserinfoEntity userinfoByUploaderId) {
        this.userinfoByUploaderId = userinfoByUploaderId;
    }

    @ManyToOne
    @JoinColumn(name = "isLegal", referencedColumnName = "TFID")
    public TfEntity getTfByIsLegal() {
        return tfByIsLegal;
    }

    public void setTfByIsLegal(TfEntity tfByIsLegal) {
        this.tfByIsLegal = tfByIsLegal;
    }

    @OneToMany(mappedBy = "resourceByCollectResourceId")
    public Collection<ResourceCollectEntity> getResourceCollectsByResourceId() {
        return resourceCollectsByResourceId;
    }

    public void setResourceCollectsByResourceId(Collection<ResourceCollectEntity> resourceCollectsByResourceId) {
        this.resourceCollectsByResourceId = resourceCollectsByResourceId;
    }

    @OneToMany(mappedBy = "resourceByCommentResourceId")
    public Collection<ResourceCommentEntity> getResourceCommentsByResourceId() {
        return resourceCommentsByResourceId;
    }

    public void setResourceCommentsByResourceId(Collection<ResourceCommentEntity> resourceCommentsByResourceId) {
        this.resourceCommentsByResourceId = resourceCommentsByResourceId;
    }

    @OneToMany(mappedBy = "resourceByForwardResourceId")
    public Collection<ResourceForwardEntity> getResourceForwardsByResourceId() {
        return resourceForwardsByResourceId;
    }

    public void setResourceForwardsByResourceId(Collection<ResourceForwardEntity> resourceForwardsByResourceId) {
        this.resourceForwardsByResourceId = resourceForwardsByResourceId;
    }

    @OneToMany(mappedBy = "resourceByResourceIdNum")
    public Collection<ResourceTagEntity> getResourceTagsByResourceId() {
        return resourceTagsByResourceId;
    }

    public void setResourceTagsByResourceId(Collection<ResourceTagEntity> resourceTagsByResourceId) {
        this.resourceTagsByResourceId = resourceTagsByResourceId;
    }
}
