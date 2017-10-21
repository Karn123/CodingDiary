package service.chattingSystem;

import controller.ChatMsg;
import model.ChatMsgEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by joy12 on 2017/2/27.
 */
@Service
public interface ChattingRecordService {
    public void record(String msg_id, String msg, Integer from_id, Integer to_id);
    public void setRead(String msg_id);
    public List<ChatMsgEntity> getUnreadMsgs(Integer from_id, Integer to_id);
    public List<ChatMsgEntity> getHistoryAWeek(Integer my_id, Integer friend_id);
}
