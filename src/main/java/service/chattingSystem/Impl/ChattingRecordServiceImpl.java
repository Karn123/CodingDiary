package service.chattingSystem.Impl;
import controller.ChatMsg;
import model.ChatMsgEntity;
import model.TfEntity;
import model.UserinfoEntity;
import org.springframework.stereotype.Service;
import service.common.impl.CommServiceImpl;
import service.chattingSystem.ChattingRecordService;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by joy12 on 2017/2/27.
 */
@Service("ChattingRecordService")
public class ChattingRecordServiceImpl extends CommServiceImpl implements ChattingRecordService{
    /* 保存消息到数据库 */
    @Override
    public void record(String msg_id, String msg, Integer from_id, Integer to_id) {
        System.out.println("开始保存消息到数据库，msg：" + msg + "  from:" + from_id + "  to:" + to_id);
        UserinfoEntity from = baseDAO.findById(from_id,UserinfoEntity.class);
        UserinfoEntity to = baseDAO.findById(to_id,UserinfoEntity.class);
        Timestamp time = new Timestamp(new Date().getTime());
        System.out.println(time);
        ChatMsgEntity record = new ChatMsgEntity();

//        TfEntity read = new TfEntity();
//        read.setTfid(0);
//        record.setTfByIsRead(read);

        record.setMsgId(msg_id);
        record.setMsgContent(msg);
        record.setMsgDate(time);
        record.setUserinfoByMsgFromUserId(from);
        record.setUserinfoByMsgToUserId(to);
        baseDAO.save(record);
    }

    @Override
    public void setRead(String msg_id) {
        ChatMsgEntity chatMsg = baseDAO.findById(msg_id,ChatMsgEntity.class);
        TfEntity read = baseDAO.findById(1,TfEntity.class);
        chatMsg.setTfByIsRead(read);
        baseDAO.update(chatMsg);
    }

    /* 链接建立时（打开对话框时），获取未读信息 */
    @Override
    public List<ChatMsgEntity> getUnreadMsgs(Integer from_id, Integer to_id) {
        List<ChatMsgEntity> chatMsgEntities = baseDAO.findByHQL("from ChatMsgEntity where userinfoByMsgFromUserId.userId=? and userinfoByMsgToUserId.userId=?",
                new Object[]{from_id,to_id});
        List<ChatMsgEntity> msgs = null;

        TfEntity tfEntity = baseDAO.findById(1,TfEntity.class);
        if(!chatMsgEntities.isEmpty()){
            msgs = new ArrayList<ChatMsgEntity>();
            for(ChatMsgEntity chatMsgEntity:chatMsgEntities){
                if(chatMsgEntity.getTfByIsRead().getTfid()==0){
                    msgs.add(chatMsgEntity);
                    chatMsgEntity.setTfByIsRead(tfEntity);
                    baseDAO.update(chatMsgEntity);
                }
            }
            if (!msgs.isEmpty()){
                Collections.sort(msgs, new Comparator<ChatMsgEntity>() {
                    @Override
                    public int compare(ChatMsgEntity o1, ChatMsgEntity o2) {
                        if (o1.getMsgDate().after(o2.getMsgDate()))
                            return 1;
                        else if (o1.getMsgDate().before(o2.getMsgDate()))
                            return -1;
                        else return 0;
                    }
                });
            }
        }else{
            System.out.println("无未读消息");
        }

        return msgs;
    }

    @Override
    public List<ChatMsgEntity> getHistoryAWeek(Integer my_id, Integer friend_id) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)-7);

        Timestamp timeStandard = new Timestamp(calendar.getTimeInMillis());
        List<ChatMsgEntity> chatMsgEntities = new ArrayList<ChatMsgEntity>();
        chatMsgEntities.addAll(baseDAO.findByHQL("from ChatMsgEntity where userinfoByMsgFromUserId.userId=? and userinfoByMsgToUserId.userId=?",
                new Object[]{my_id,friend_id}));
        chatMsgEntities.addAll(baseDAO.findByHQL("from ChatMsgEntity where userinfoByMsgFromUserId.userId=? and userinfoByMsgToUserId.userId=? and tfByIsRead.tfid=?",
                new Object[]{friend_id,my_id,1}));

        if(!chatMsgEntities.isEmpty()){
            Iterator iterator = chatMsgEntities.iterator();
            while (iterator.hasNext()){
//                UserinfoEntity from = chatMsgEntity.getUserinfoByMsgFromUserId();
//                UserinfoEntity to = chatMsgEntity.getUserinfoByMsgToUserId();
                //只保留一周内的已读信息
                ChatMsgEntity chatMsgEntity = (ChatMsgEntity) iterator.next();
                if(chatMsgEntity.getMsgDate().before(timeStandard)){
                    iterator.remove();
                }
            }

            if (!chatMsgEntities.isEmpty()){
                Collections.sort(chatMsgEntities, new Comparator<ChatMsgEntity>() {
                    @Override
                    public int compare(ChatMsgEntity o1, ChatMsgEntity o2) {
                        if (o1.getMsgDate().after(o2.getMsgDate()))
                            return 1;
                        else if (o1.getMsgDate().before(o2.getMsgDate()))
                            return -1;
                        else return 0;
                    }
                });
            }
        }else{
            System.out.println("无历史记录");
        }



        return chatMsgEntities;
    }
}
