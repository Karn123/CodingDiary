package model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Karn on 2017/2/14.
 */
@Entity
@Table(name = "user_action_about", schema = "CodingDiaryDB", catalog = "")
public class UserActionAboutEntity {
    private int actionAboutId;
    private String actionType;
    private Collection<NotificationMessageEntity> notificationMessagesByActionAboutId;

    @Id
    @Column(name = "actionAboutID", nullable = false)
    public int getActionAboutId() {
        return actionAboutId;
    }

    public void setActionAboutId(int actionAboutId) {
        this.actionAboutId = actionAboutId;
    }

    @Basic
    @Column(name = "actionType", nullable = false, length = 20)
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

        UserActionAboutEntity that = (UserActionAboutEntity) o;

        if (actionAboutId != that.actionAboutId) return false;
        if (actionType != null ? !actionType.equals(that.actionType) : that.actionType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = actionAboutId;
        result = 31 * result + (actionType != null ? actionType.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "userActionAboutByNotiMsgAboutType")
    public Collection<NotificationMessageEntity> getNotificationMessagesByActionAboutId() {
        return notificationMessagesByActionAboutId;
    }

    public void setNotificationMessagesByActionAboutId(Collection<NotificationMessageEntity> notificationMessagesByActionAboutId) {
        this.notificationMessagesByActionAboutId = notificationMessagesByActionAboutId;
    }
}
