package model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by Karn on 2017/2/14.
 */
@Entity
@Table(name = "userinfo", schema = "CodingDiaryDB", catalog = "")
public class UserinfoEntity {
    private int userId;
    private String teleNum;
    private String nickName;
    private String userPassword;
    private String email;
    private Integer age;
    private Integer userLevel;
    private Integer xp;
    private String headPortrait;
    private String realName;
    private Integer fansNum;
    private Integer entranceYear;
    private Integer entranceMonth;
    private Integer estimateGraduateDateYear;
    private Integer estimateGraduateMonth;
    private String instituteName;
    private String universityName;
    private Timestamp registerTime;
    private Integer isCertified;
    private Collection<AdministratorEntity> administratorsByUserId;
    private Collection<BlogEntity> blogsByUserId;
    private Collection<BlogCollectEntity> blogCollectsByUserId;
    private Collection<BlogForwardEntity> blogForwardsByUserId;
    private Collection<ChatMsgEntity> chatMsgsByUserId;
    private Collection<ChatMsgEntity> chatMsgsByUserId_0;
    private Collection<ForumpostCollectEntity> forumpostCollectsByUserId;
    private Collection<ForumpostFloorEntity> forumpostFloorsByUserId;
    private Collection<ForumpostForwardEntity> forumpostForwardsByUserId;
    private Collection<NotificationMessageEntity> notificationMessagesByUserId;
    private Collection<NotificationMessageEntity> notificationMessagesByUserId_0;
    private Collection<PraiseBlogEntity> praiseBlogsByUserId;
    private Collection<PraiseCommentEntity> praiseCommentsByUserId;
    private Collection<PraiseForumpostEntity> praiseForumpostsByUserId;
    private Collection<PraiseResourceEntity> praiseResourcesByUserId;
    private Collection<ResourceEntity> resourcesByUserId;
    private Collection<ResourceCollectEntity> resourceCollectsByUserId;
    private Collection<ResourceForwardEntity> resourceForwardsByUserId;
    private Collection<UserActionHistoryEntity> userActionHistoriesByUserId;
    private Collection<UsercommentEntity> usercommentsByUserId;
    private Collection<UserfansEntity> userfanssByUserId;
    private Collection<UserfansEntity> userfanssByUserId_0;
    private GenderEntity genderBySex;
    private TfEntity tfByIsGotBlocked;

    @Id
    @Column(name = "userID", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "teleNum", nullable = true, length = 20)
    public String getTeleNum() {
        return teleNum;
    }

    public void setTeleNum(String teleNum) {
        this.teleNum = teleNum;
    }

    @Basic
    @Column(name = "nickName", nullable = false, length = 100)
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Basic
    @Column(name = "userPassword", nullable = false, length = 40)
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 40)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "age", nullable = true)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "userLevel", nullable = true)
    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    @Basic
    @Column(name = "XP", nullable = true)
    public Integer getXp() {
        return xp;
    }

    public void setXp(Integer xp) {
        this.xp = xp;
    }

    @Basic
    @Column(name = "headPortrait", nullable = true, length = 1000)
    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    @Basic
    @Column(name = "realName", nullable = true, length = 30)
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Basic
    @Column(name = "fansNum", nullable = true)
    public Integer getFansNum() {
        return fansNum;
    }

    public void setFansNum(Integer fansNum) {
        this.fansNum = fansNum;
    }

    @Basic
    @Column(name = "entranceYear", nullable = true)
    public Integer getEntranceYear() {
        return entranceYear;
    }

    public void setEntranceYear(Integer entranceYear) {
        this.entranceYear = entranceYear;
    }

    @Basic
    @Column(name = "entranceMonth", nullable = true)
    public Integer getEntranceMonth() {
        return entranceMonth;
    }

    public void setEntranceMonth(Integer entranceMonth) {
        this.entranceMonth = entranceMonth;
    }

    @Basic
    @Column(name = "estimateGraduateDateYear", nullable = true)
    public Integer getEstimateGraduateDateYear() {
        return estimateGraduateDateYear;
    }

    public void setEstimateGraduateDateYear(Integer estimateGraduateDateYear) {
        this.estimateGraduateDateYear = estimateGraduateDateYear;
    }

    @Basic
    @Column(name = "estimateGraduateMonth", nullable = true)
    public Integer getEstimateGraduateMonth() {
        return estimateGraduateMonth;
    }

    public void setEstimateGraduateMonth(Integer estimateGraduateMonth) {
        this.estimateGraduateMonth = estimateGraduateMonth;
    }

    @Basic
    @Column(name = "instituteName", nullable = true, length = 100)
    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    @Basic
    @Column(name = "universityName", nullable = true, length = 11)
    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    @Basic
    @Column(name = "registerTime", nullable = true)
    public Timestamp getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
    }

    @Basic
    @Column(name="isCertified", nullable = true)
    public Integer getIsCertified(){return isCertified;}

    public void setIsCertified(Integer isCertified){this.isCertified=isCertified;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserinfoEntity that = (UserinfoEntity) o;

        if (userId != that.userId) return false;
        if (teleNum != null ? !teleNum.equals(that.teleNum) : that.teleNum != null) return false;
        if (nickName != null ? !nickName.equals(that.nickName) : that.nickName != null) return false;
        if (userPassword != null ? !userPassword.equals(that.userPassword) : that.userPassword != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (age != null ? !age.equals(that.age) : that.age != null) return false;
        if (userLevel != null ? !userLevel.equals(that.userLevel) : that.userLevel != null) return false;
        if (xp != null ? !xp.equals(that.xp) : that.xp != null) return false;
        if (headPortrait != null ? !headPortrait.equals(that.headPortrait) : that.headPortrait != null) return false;
        if (realName != null ? !realName.equals(that.realName) : that.realName != null) return false;
        if (fansNum != null ? !fansNum.equals(that.fansNum) : that.fansNum != null) return false;
        if (entranceYear != null ? !entranceYear.equals(that.entranceYear) : that.entranceYear != null) return false;
        if (entranceMonth != null ? !entranceMonth.equals(that.entranceMonth) : that.entranceMonth != null)
            return false;
        if (estimateGraduateDateYear != null ? !estimateGraduateDateYear.equals(that.estimateGraduateDateYear) : that.estimateGraduateDateYear != null)
            return false;
        if (estimateGraduateMonth != null ? !estimateGraduateMonth.equals(that.estimateGraduateMonth) : that.estimateGraduateMonth != null)
            return false;
        if (instituteName != null ? !instituteName.equals(that.instituteName) : that.instituteName != null)
            return false;
        if (universityName != null ? !universityName.equals(that.universityName) : that.universityName != null)
            return false;
        if (registerTime != null ? !registerTime.equals(that.registerTime) : that.registerTime != null) return false;
        if (isCertified != null ? !isCertified.equals(that.isCertified) : that.isCertified != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (teleNum != null ? teleNum.hashCode() : 0);
        result = 31 * result + (nickName != null ? nickName.hashCode() : 0);
        result = 31 * result + (userPassword != null ? userPassword.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (userLevel != null ? userLevel.hashCode() : 0);
        result = 31 * result + (xp != null ? xp.hashCode() : 0);
        result = 31 * result + (headPortrait != null ? headPortrait.hashCode() : 0);
        result = 31 * result + (realName != null ? realName.hashCode() : 0);
        result = 31 * result + (fansNum != null ? fansNum.hashCode() : 0);
        result = 31 * result + (entranceYear != null ? entranceYear.hashCode() : 0);
        result = 31 * result + (entranceMonth != null ? entranceMonth.hashCode() : 0);
        result = 31 * result + (estimateGraduateDateYear != null ? estimateGraduateDateYear.hashCode() : 0);
        result = 31 * result + (estimateGraduateMonth != null ? estimateGraduateMonth.hashCode() : 0);
        result = 31 * result + (instituteName != null ? instituteName.hashCode() : 0);
        result = 31 * result + (universityName != null ? universityName.hashCode() : 0);
        result = 31 * result + (registerTime != null ? registerTime.hashCode() : 0);
        result = 31 * result + (isCertified != null ? isCertified.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "userinfoByAdminUserId")
    public Collection<AdministratorEntity> getAdministratorsByUserId() {
        return administratorsByUserId;
    }

    public void setAdministratorsByUserId(Collection<AdministratorEntity> administratorsByUserId) {
        this.administratorsByUserId = administratorsByUserId;
    }

    @OneToMany(mappedBy = "userinfoByAuthorId")
    public Collection<BlogEntity> getBlogsByUserId() {
        return blogsByUserId;
    }

    public void setBlogsByUserId(Collection<BlogEntity> blogsByUserId) {
        this.blogsByUserId = blogsByUserId;
    }

    @OneToMany(mappedBy = "userinfoByCollectorId")
    public Collection<BlogCollectEntity> getBlogCollectsByUserId() {
        return blogCollectsByUserId;
    }

    public void setBlogCollectsByUserId(Collection<BlogCollectEntity> blogCollectsByUserId) {
        this.blogCollectsByUserId = blogCollectsByUserId;
    }

    @OneToMany(mappedBy = "userinfoByForwarderId")
    public Collection<BlogForwardEntity> getBlogForwardsByUserId() {
        return blogForwardsByUserId;
    }

    public void setBlogForwardsByUserId(Collection<BlogForwardEntity> blogForwardsByUserId) {
        this.blogForwardsByUserId = blogForwardsByUserId;
    }

    @OneToMany(mappedBy = "userinfoByMsgFromUserId")
    public Collection<ChatMsgEntity> getChatMsgsByUserId() {
        return chatMsgsByUserId;
    }

    public void setChatMsgsByUserId(Collection<ChatMsgEntity> chatMsgsByUserId) {
        this.chatMsgsByUserId = chatMsgsByUserId;
    }

    @OneToMany(mappedBy = "userinfoByMsgToUserId")
    public Collection<ChatMsgEntity> getChatMsgsByUserId_0() {
        return chatMsgsByUserId_0;
    }

    public void setChatMsgsByUserId_0(Collection<ChatMsgEntity> chatMsgsByUserId_0) {
        this.chatMsgsByUserId_0 = chatMsgsByUserId_0;
    }

    @OneToMany(mappedBy = "userinfoByCollectorId")
    public Collection<ForumpostCollectEntity> getForumpostCollectsByUserId() {
        return forumpostCollectsByUserId;
    }

    public void setForumpostCollectsByUserId(Collection<ForumpostCollectEntity> forumpostCollectsByUserId) {
        this.forumpostCollectsByUserId = forumpostCollectsByUserId;
    }

    @OneToMany(mappedBy = "userinfoByAuthorId")
    public Collection<ForumpostFloorEntity> getForumpostFloorsByUserId() {
        return forumpostFloorsByUserId;
    }

    public void setForumpostFloorsByUserId(Collection<ForumpostFloorEntity> forumpostFloorsByUserId) {
        this.forumpostFloorsByUserId = forumpostFloorsByUserId;
    }

    @OneToMany(mappedBy = "userinfoByForwarderId")
    public Collection<ForumpostForwardEntity> getForumpostForwardsByUserId() {
        return forumpostForwardsByUserId;
    }

    public void setForumpostForwardsByUserId(Collection<ForumpostForwardEntity> forumpostForwardsByUserId) {
        this.forumpostForwardsByUserId = forumpostForwardsByUserId;
    }

    @OneToMany(mappedBy = "userinfoBySenderId")
    public Collection<NotificationMessageEntity> getNotificationMessagesByUserId() {
        return notificationMessagesByUserId;
    }

    public void setNotificationMessagesByUserId(Collection<NotificationMessageEntity> notificationMessagesByUserId) {
        this.notificationMessagesByUserId = notificationMessagesByUserId;
    }

    @OneToMany(mappedBy = "userinfoByReceiverId")
    public Collection<NotificationMessageEntity> getNotificationMessagesByUserId_0() {
        return notificationMessagesByUserId_0;
    }

    public void setNotificationMessagesByUserId_0(Collection<NotificationMessageEntity> notificationMessagesByUserId_0) {
        this.notificationMessagesByUserId_0 = notificationMessagesByUserId_0;
    }

    @OneToMany(mappedBy = "userinfoByPraiseFromUserId")
    public Collection<PraiseBlogEntity> getPraiseBlogsByUserId() {
        return praiseBlogsByUserId;
    }

    public void setPraiseBlogsByUserId(Collection<PraiseBlogEntity> praiseBlogsByUserId) {
        this.praiseBlogsByUserId = praiseBlogsByUserId;
    }

    @OneToMany(mappedBy = "userinfoByPraiseFromUserId")
    public Collection<PraiseCommentEntity> getPraiseCommentsByUserId() {
        return praiseCommentsByUserId;
    }

    public void setPraiseCommentsByUserId(Collection<PraiseCommentEntity> praiseCommentsByUserId) {
        this.praiseCommentsByUserId = praiseCommentsByUserId;
    }

    @OneToMany(mappedBy = "userinfoByPraiseFromUserId")
    public Collection<PraiseForumpostEntity> getPraiseForumpostsByUserId() {
        return praiseForumpostsByUserId;
    }

    public void setPraiseForumpostsByUserId(Collection<PraiseForumpostEntity> praiseForumpostsByUserId) {
        this.praiseForumpostsByUserId = praiseForumpostsByUserId;
    }

    @OneToMany(mappedBy = "userinfoByPraiseFromUserId")
    public Collection<PraiseResourceEntity> getPraiseResourcesByUserId() {
        return praiseResourcesByUserId;
    }

    public void setPraiseResourcesByUserId(Collection<PraiseResourceEntity> praiseResourcesByUserId) {
        this.praiseResourcesByUserId = praiseResourcesByUserId;
    }

    @OneToMany(mappedBy = "userinfoByUploaderId")
    public Collection<ResourceEntity> getResourcesByUserId() {
        return resourcesByUserId;
    }

    public void setResourcesByUserId(Collection<ResourceEntity> resourcesByUserId) {
        this.resourcesByUserId = resourcesByUserId;
    }

    @OneToMany(mappedBy = "userinfoByCollectorId")
    public Collection<ResourceCollectEntity> getResourceCollectsByUserId() {
        return resourceCollectsByUserId;
    }

    public void setResourceCollectsByUserId(Collection<ResourceCollectEntity> resourceCollectsByUserId) {
        this.resourceCollectsByUserId = resourceCollectsByUserId;
    }

    @OneToMany(mappedBy = "userinfoByForwarderId")
    public Collection<ResourceForwardEntity> getResourceForwardsByUserId() {
        return resourceForwardsByUserId;
    }

    public void setResourceForwardsByUserId(Collection<ResourceForwardEntity> resourceForwardsByUserId) {
        this.resourceForwardsByUserId = resourceForwardsByUserId;
    }

    @OneToMany(mappedBy = "userinfoByUserId")
    public Collection<UserActionHistoryEntity> getUserActionHistoriesByUserId() {
        return userActionHistoriesByUserId;
    }

    public void setUserActionHistoriesByUserId(Collection<UserActionHistoryEntity> userActionHistoriesByUserId) {
        this.userActionHistoriesByUserId = userActionHistoriesByUserId;
    }

    @OneToMany(mappedBy = "userinfoByCommenterId")
    public Collection<UsercommentEntity> getUsercommentsByUserId() {
        return usercommentsByUserId;
    }

    public void setUsercommentsByUserId(Collection<UsercommentEntity> usercommentsByUserId) {
        this.usercommentsByUserId = usercommentsByUserId;
    }

    @OneToMany(mappedBy = "userinfoByUserIdNum")
    public Collection<UserfansEntity> getUserfanssByUserId() {
        return userfanssByUserId;
    }

    public void setUserfanssByUserId(Collection<UserfansEntity> userfanssByUserId) {
        this.userfanssByUserId = userfanssByUserId;
    }

    @OneToMany(mappedBy = "userinfoByFanId")
    public Collection<UserfansEntity> getUserfanssByUserId_0() {
        return userfanssByUserId_0;
    }

    public void setUserfanssByUserId_0(Collection<UserfansEntity> userfanssByUserId_0) {
        this.userfanssByUserId_0 = userfanssByUserId_0;
    }

    @ManyToOne
    @JoinColumn(name = "sex", referencedColumnName = "genderID")
    public GenderEntity getGenderBySex() {
        return genderBySex;
    }

    public void setGenderBySex(GenderEntity genderBySex) {
        this.genderBySex = genderBySex;
    }

    @ManyToOne
    @JoinColumn(name = "isGotBlocked", referencedColumnName = "TFID")
    public TfEntity getTfByIsGotBlocked() {
        return tfByIsGotBlocked;
    }

    public void setTfByIsGotBlocked(TfEntity tfByIsGotBlocked) {
        this.tfByIsGotBlocked = tfByIsGotBlocked;
    }
}
