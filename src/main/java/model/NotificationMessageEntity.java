package model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Karn on 2017/2/14.
 */
@Entity
@Table(name = "notification_message", schema = "CodingDiaryDB", catalog = "")
public class NotificationMessageEntity {
    private int notiMsgId;
    private int notiMsgThemeId;
    private String notiMsgContent;
    private Timestamp notiMsgTime;
    private UserinfoEntity userinfoBySenderId;
    private UserinfoEntity userinfoByReceiverId;
    private UserActionEntity userActionByNotiMsgActionType;
    private UserActionAboutEntity userActionAboutByNotiMsgAboutType;
    private TfEntity tfByIsRead;

    @ManyToOne
    @JoinColumn(name = "isRead", referencedColumnName = "TFID")
    public TfEntity getTfByIsRead() {
        return tfByIsRead;
    }

    public void setTfByIsRead(TfEntity tfByIsRead) {
        this.tfByIsRead = tfByIsRead;
    }

    @Id
    @Column(name = "notiMsgID", nullable = false)
    public int getNotiMsgId() {
        return notiMsgId;
    }

    public void setNotiMsgId(int notiMsgId) {
        this.notiMsgId = notiMsgId;
    }

    @Basic
    @Column(name = "notiMsgThemeID", nullable = false)
    public int getNotiMsgThemeId() {
        return notiMsgThemeId;
    }

    public void setNotiMsgThemeId(int notiMsgThemeId) {
        this.notiMsgThemeId = notiMsgThemeId;
    }

    @Basic
    @Column(name = "notiMsgContent", nullable = false, length = 200)
    public String getNotiMsgContent() {
        return notiMsgContent;
    }

    public void setNotiMsgContent(String notiMsgContent) {
        this.notiMsgContent = notiMsgContent;
    }

    @Basic
    @Column(name = "notiMsgTime", nullable = false)
    public Timestamp getNotiMsgTime() {
        return notiMsgTime;
    }

    public void setNotiMsgTime(Timestamp notiMsgTime) {
        this.notiMsgTime = notiMsgTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotificationMessageEntity that = (NotificationMessageEntity) o;

        if (notiMsgId != that.notiMsgId) return false;
        if (notiMsgThemeId != that.notiMsgThemeId) return false;
        if (notiMsgContent != null ? !notiMsgContent.equals(that.notiMsgContent) : that.notiMsgContent != null)
            return false;
        if (notiMsgTime != null ? !notiMsgTime.equals(that.notiMsgTime) : that.notiMsgTime != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = notiMsgId;
        result = 31 * result + (notiMsgContent != null ? notiMsgContent.hashCode() : 0);
        result = 31 * result + (notiMsgTime != null ? notiMsgTime.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "senderID", referencedColumnName = "userID", nullable = false)
    public UserinfoEntity getUserinfoBySenderId() {
        return userinfoBySenderId;
    }

    public void setUserinfoBySenderId(UserinfoEntity userinfoBySenderId) {
        this.userinfoBySenderId = userinfoBySenderId;
    }

    @ManyToOne
    @JoinColumn(name = "receiverID", referencedColumnName = "userID", nullable = false)
    public UserinfoEntity getUserinfoByReceiverId() {
        return userinfoByReceiverId;
    }

    public void setUserinfoByReceiverId(UserinfoEntity userinfoByReceiverId) {
        this.userinfoByReceiverId = userinfoByReceiverId;
    }

    @ManyToOne
    @JoinColumn(name = "notiMsgActionType", referencedColumnName = "actionID", nullable = false)
    public UserActionEntity getUserActionByNotiMsgActionType() {
        return userActionByNotiMsgActionType;
    }

    public void setUserActionByNotiMsgActionType(UserActionEntity userActionByNotiMsgActionType) {
        this.userActionByNotiMsgActionType = userActionByNotiMsgActionType;
    }

    @ManyToOne
    @JoinColumn(name = "notiMsgAboutType", referencedColumnName = "actionAboutID", nullable = false)
    public UserActionAboutEntity getUserActionAboutByNotiMsgAboutType() {
        return userActionAboutByNotiMsgAboutType;
    }

    public void setUserActionAboutByNotiMsgAboutType(UserActionAboutEntity userActionAboutByNotiMsgAboutType) {
        this.userActionAboutByNotiMsgAboutType = userActionAboutByNotiMsgAboutType;
    }
}
