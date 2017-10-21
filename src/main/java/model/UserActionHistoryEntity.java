package model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Karn on 2017/2/14.
 */
@Entity
@Table(name = "user_action_history", schema = "CodingDiaryDB", catalog = "")
@IdClass(UserActionHistoryEntityPK.class)
public class UserActionHistoryEntity {
    private int userId;
    private int actionId;
    private int actionAboutId;
    private int aboutThemeId;
    private Timestamp actionTime;
    private UserinfoEntity userinfoByUserId;
    private UserActionEntity userActionByActionId;
    private UserActionAboutEntity userActionAboutByActionAboutId;

    @Id
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "action_id", nullable = false)
    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    @Id
    @Column(name = "action_about_id", nullable = false)
    public int getActionAboutId() {
        return actionAboutId;
    }

    public void setActionAboutId(int actionAboutId) {
        this.actionAboutId = actionAboutId;
    }

    @Id
    @Column(name = "about_theme_id", nullable = false)
    public int getAboutThemeId() {
        return aboutThemeId;
    }

    public void setAboutThemeId(int aboutThemeId) {
        this.aboutThemeId = aboutThemeId;
    }

    @Id
    @Column(name = "action_time", nullable = false)
    public Timestamp getActionTime() {
        return actionTime;
    }

    public void setActionTime(Timestamp actionTime) {
        this.actionTime = actionTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserActionHistoryEntity that = (UserActionHistoryEntity) o;

        if (userId != that.userId) return false;
        if (actionId != that.actionId) return false;
        if (actionAboutId != that.actionAboutId) return false;
        if (aboutThemeId != that.aboutThemeId) return false;
        if (actionTime != null ? !actionTime.equals(that.actionTime) : that.actionTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + actionId;
        result = 31 * result + actionAboutId;
        result = 31 * result + aboutThemeId;
        result = 31 * result + (actionTime != null ? actionTime.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userID", nullable = false)
    public UserinfoEntity getUserinfoByUserId() {
        return userinfoByUserId;
    }

    public void setUserinfoByUserId(UserinfoEntity userinfoByUserId) {
        this.userinfoByUserId = userinfoByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "action_id", referencedColumnName = "actionID", nullable = false)
    public UserActionEntity getUserActionByActionId() {
        return userActionByActionId;
    }

    public void setUserActionByActionId(UserActionEntity userActionByActionId) {
        this.userActionByActionId = userActionByActionId;
    }

    @ManyToOne
    @JoinColumn(name = "action_about_id", referencedColumnName = "actionAboutID", nullable = false)
    public UserActionAboutEntity getUserActionAboutByActionAboutId() {
        return userActionAboutByActionAboutId;
    }

    public void setUserActionAboutByActionAboutId(UserActionAboutEntity userActionAboutByActionAboutId) {
        this.userActionAboutByActionAboutId = userActionAboutByActionAboutId;
    }
}
