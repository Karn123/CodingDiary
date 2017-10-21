package common.simpleChatMsg;

import model.ChatMsgEntity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by joy12 on 2017/8/11.
 */
public class SimpleChatMsg{
    private String msgId;
    private String msgContent;
//    private String msgDate;
    private Timestamp msgDate;
    private int fromUserId;
    private int toUserId;
    private boolean isRead;

    public SimpleChatMsg() {
    }

    public SimpleChatMsg(ChatMsgEntity chatMsgEntity) {
        this.msgId = chatMsgEntity.getMsgId();
        this.msgContent = chatMsgEntity.getMsgContent();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        sdf.format(chatMsgEntity.getMsgDate().getTime());
        this.msgDate = chatMsgEntity.getMsgDate();
        this.fromUserId = chatMsgEntity.getUserinfoByMsgFromUserId().getUserId();
        this.toUserId = chatMsgEntity.getUserinfoByMsgToUserId().getUserId();
        switch (chatMsgEntity.getTfByIsRead().getTfid()){
            case 0:
                this.isRead = false;
                break;
            case 1:
                this.isRead = true;
                break;
        }
    }


    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public Timestamp getMsgDate() {
        return msgDate;
    }

    public void setMsgDate(Timestamp msgDate) {
        this.msgDate = msgDate;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    public int getToUserId() {
        return toUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
    }

    public boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(boolean read) {
        isRead = read;
    }
}
