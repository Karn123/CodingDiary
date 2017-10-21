package model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Karn on 2017/2/14.
 */
@Entity
@Table(name = "chat_msg", schema = "CodingDiaryDB", catalog = "")
public class ChatMsgEntity {
    private String msgId;
    private String msgContent;
    private Timestamp msgDate;
    private UserinfoEntity userinfoByMsgFromUserId;
    private UserinfoEntity userinfoByMsgToUserId;
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
    @Column(name = "msg_id", nullable = false)
    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    @Basic
    @Column(name = "msg_content", nullable = false, length = 500)
    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    @Basic
    @Column(name = "msg_date", nullable = false)
    public Timestamp getMsgDate() {
        return msgDate;
    }

    public void setMsgDate(Timestamp msgDate) {
        this.msgDate = msgDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatMsgEntity that = (ChatMsgEntity) o;

        if (msgId != null ? !msgId.equals(that.msgId) : that.msgId != null) return false;
        if (msgContent != null ? !msgContent.equals(that.msgContent) : that.msgContent != null) return false;
        if (msgDate != null ? !msgDate.equals(that.msgDate) : that.msgDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (msgId != null ? msgId.hashCode() : 0);
        result = 31 * result + (msgContent != null ? msgContent.hashCode() : 0);
        result = 31 * result + (msgDate != null ? msgDate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "msg_from_user_id", referencedColumnName = "userID", nullable = false)
    public UserinfoEntity getUserinfoByMsgFromUserId() {
        return userinfoByMsgFromUserId;
    }

    public void setUserinfoByMsgFromUserId(UserinfoEntity userinfoByMsgFromUserId) {
        this.userinfoByMsgFromUserId = userinfoByMsgFromUserId;
    }

    @ManyToOne
    @JoinColumn(name = "msg_to_user_id", referencedColumnName = "userID", nullable = false)
    public UserinfoEntity getUserinfoByMsgToUserId() {
        return userinfoByMsgToUserId;
    }

    public void setUserinfoByMsgToUserId(UserinfoEntity userinfoByMsgToUserId) {
        this.userinfoByMsgToUserId = userinfoByMsgToUserId;
    }
}
