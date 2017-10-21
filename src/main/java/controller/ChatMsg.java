package controller;

import common.simpleChatMsg.SimpleChatMsg;
import cst.Constants;
import model.ChatMsgEntity;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.chattingSystem.ChattingRecordService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by joy12 on 2017/2/27.
 */
@Controller
public class ChatMsg {
    @Resource
    ChattingRecordService chattingRecordService;

    /* 保存消息 */
    @RequestMapping(value = "/jsp/saveChatMsg")
    @ResponseBody
    public void saveMsg(HttpServletRequest request, HttpServletResponse response,
                        @RequestParam("msgId") String msgId,@RequestParam("msgContent") String msgContent){
        Integer from_id = Integer.parseInt(request.getSession().getAttribute(Constants.USER_Id).toString());
        Integer to_id = Integer.parseInt(request.getSession().getAttribute("this_id").toString());
        chattingRecordService.record(msgId,msgContent,from_id,to_id);
    }

    /* 设置已读 */
    @RequestMapping(value = "/jsp/setMsgsRead")
    @ResponseBody
    public void setMsgRead(HttpServletRequest request, HttpServletResponse response,
                        @RequestParam("msgIdArray") String msgIdsJSON){
        JSONArray ja = JSONArray.fromObject(msgIdsJSON);
        for (int i = 0; i < ja.size(); i++) {
            chattingRecordService.setRead(ja.getString(i));
        }
    }

    /* 获取历史消息和未读消息 */
    @RequestMapping(value = "/jsp/getInitialMsgs")
    @ResponseBody
    public Map getInitialMsgs(HttpServletRequest request, HttpServletResponse response){
        Map result = new HashMap();
        Integer my_id = Integer.parseInt(request.getSession().getAttribute(Constants.USER_Id).toString());
        Integer friend_id = Integer.parseInt(request.getSession().getAttribute("this_id").toString());
        List<ChatMsgEntity> unreadMsgs = chattingRecordService.getUnreadMsgs(friend_id,my_id);
        List<ChatMsgEntity> historyMsgs = chattingRecordService.getHistoryAWeek(my_id,friend_id);

        List<SimpleChatMsg> unreadList = new ArrayList<SimpleChatMsg>();
        List<SimpleChatMsg> historyList = new ArrayList<SimpleChatMsg>();

        if (historyMsgs != null && historyMsgs.size()>0){
            System.out.println("有" + historyMsgs.size() + "历史记录");
            result.put("historyRetCode", "0000");
            for (int i=0; i<historyMsgs.size(); i++){
                SimpleChatMsg simpleChatMsg = new SimpleChatMsg(historyMsgs.get(i));
                historyList.add(simpleChatMsg);
            }
        }else {
            System.out.println("无历史记录");
            result.put("historyRetCode", "0001");
        }
        result.put("historyList",historyList);

        if (unreadMsgs != null && unreadMsgs.size()>0){
            System.out.println("有" + unreadMsgs.size() + "未读消息");
            result.put("unreadRetCode", "0000");
            for (int i=0; i<unreadMsgs.size(); i++){
                SimpleChatMsg simpleChatMsg = new SimpleChatMsg(unreadMsgs.get(i));
                unreadList.add(simpleChatMsg);
            }
        }else {
            System.out.println("无未读消息");
            result.put("unreadRetCode", "0001");
        }
        result.put("unreadList", unreadList);
        return result;
    }

}
