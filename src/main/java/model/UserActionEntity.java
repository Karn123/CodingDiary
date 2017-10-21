package model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Karn on 2017/2/14.
 */
@Entity
@Table(name = "user_action", schema = "CodingDiaryDB", catalog = "")
public class UserActionEntity {
    private int actionId;
    private String actionType;
    private Collection<NotificationMessageEntity> notificationMessagesByActionId;
    private Collection<UserActionHistoryEntity> userActionHistoriesByActionId;

    @Id
    @Column(name = "actionID", nullable = false)
    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    @Basic
    @Column(name = "actionType", nullable = false, length = 30)
    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserActionEntity that = (UserActionEntity) o;

        if (actionId != that.actionId) return false;
        if (actionType != null ? !actionType.equals(that.actionType) : that.actionType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = actionId;
        result = 31 * result + (actionType != null ? actionType.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "userActionByNotiMsgActionType")
    public Collection<NotificationMessageEntity> getNotificationMessagesByActionId() {
        return notificationMessagesByActionId;
    }

    public void setNotificationMessagesByActionId(Collection<NotificationMessageEntity> notificationMessagesByActionId) {
        this.notificationMessagesByActionId = notificationMessagesByActionId;
    }

    @OneToMany(mappedBy = "userActionByActionId")
    public Collection<UserActionHistoryEntity> getUserActionHistoriesByActionId() {
        return userActionHistoriesByActionId;
    }

    public void setUserActionHistoriesByActionId(Collection<UserActionHistoryEntity> userActionHistoriesByActionId) {
        this.userActionHistoriesByActionId = userActionHistoriesByActionId;
    }
}
