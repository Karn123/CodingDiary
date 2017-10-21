package model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Karn on 2017/2/14.
 */
public class UserActionHistoryEntityPK implements Serializable {
    private int userId;
    private int actionId;
    private int actionAboutId;
    private int aboutThemeId;
    private Timestamp actionTime;

    @Column(name = "user_id", nullable = false)
    @Id
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "action_id", nullable = false)
    @Id
    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    @Column(name = "action_about_id", nullable = false)
    @Id
    public int getActionAboutId() {
        return actionAboutId;
    }

    public void setActionAboutId(int actionAboutId) {
        this.actionAboutId = actionAboutId;
    }

    @Column(name = "about_theme_id", nullable = false)
    @Id
    public int getAboutThemeId() {
        return aboutThemeId;
    }

    public void setAboutThemeId(int aboutThemeId) {
        this.aboutThemeId = aboutThemeId;
    }

    @Column(name = "action_time", nullable = false)
    @Id
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

        UserActionHistoryEntityPK that = (UserActionHistoryEntityPK) o;

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
}
