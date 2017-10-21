package controller;
import cst.Constants;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
@ServerEndpoint(value = "/WebSocket",configurator = GetHttpSessionConfigurator.class)
public class WebSocket {

    //静态变量，用来记录当前在线连接数
    private static int onlineCount = 0;
    private static Map<Integer,WebSocket> webSocketSet = new HashMap<Integer,WebSocket>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    private HttpSession httpSession;
    private Integer my_id;
    private Integer to_id;

    /**
     * 连接建立成功调用的方法
     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config){
        this.session = session;
        httpSession = (HttpSession) config.getUserProperties().get(
                HttpSession.class.getName());
        my_id = Integer.parseInt(httpSession.getAttribute(Constants.USER_Id).toString());
        to_id = Integer.parseInt(httpSession.getAttribute("this_id").toString());
        System.out.println("my_id::" + my_id + "  to_id::" + to_id);
        webSocketSet.put(my_id,this);
        addOnlineCount(); //在线数加
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
    }
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){
        webSocketSet.remove(this); //从set中删除
        subOnlineCount(); //在线数减
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }
    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("服务器收到来自客户端" + my_id + "的消息:" + message);
        System.out.println("发送给:" + to_id);
        //群发消息
//        for(WebSocket item: webSocketSet){
//            try {
//                item.sendMessage(message);
//            } catch (IOException e) {
//                e.printStackTrace();
//                continue;
//            }
//        }
        try {
            if (webSocketSet.containsKey(this.to_id)){
                WebSocket to_user = webSocketSet.get(this.to_id);
                to_user.sendMessage(message);
                System.out.println("发送完成");
                this.sendMessage("msg_has_been_read");
            }else{
                System.out.println(to_id + "离线，消息不发送");
                this.sendMessage("msg_has_not_been_read");
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }
    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException{
        this.session.getBasicRemote().sendText(message);
//this.session.getAsyncRemote().sendText(message);
    }
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }
    public static synchronized void addOnlineCount() {
        WebSocket.onlineCount++;
    }
    public static synchronized void subOnlineCount() {
        WebSocket.onlineCount--;
    }
}
