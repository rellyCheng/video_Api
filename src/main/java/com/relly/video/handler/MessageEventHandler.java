package com.relly.video.handler;


import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MessageEventHandler {
    private static SocketIOServer server;
    static ArrayList<UUID> listClient = new ArrayList<>();
    static Map<Integer,UUID> mapClient = new HashMap<>();

    @Autowired
    public MessageEventHandler(SocketIOServer server){
        this.server = server;
    }

    /**
     * 链接客户端,方便后面发送消息时查找到对应的目标client
     */
    @OnConnect
    public void onConnect(SocketIOClient client){
        UUID clientId = client.getSessionId();
        listClient.add(clientId);
        Integer userId = Integer.valueOf(client.getHandshakeData().getSingleUrlParam("userId"));
        mapClient.put(userId,clientId);
        System.out.println("用户"+userId+"。客户端:" + clientId + "连接成功");
    }

    /**
     * 添加@OnDisconnect事件，客户端断开连接时调用，刷新客户端信息
     */
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        UUID clientId = client.getSessionId();
        listClient.remove(clientId);
        String userId = client.getHandshakeData().getSingleUrlParam("userId");
        mapClient.remove(userId);
        System.out.println("客户端:" + clientId + "断开连接");
    }

    //消息接收入口，当接收到消息后，查找发送目标客户端，并且向该客户端发送消息
    @OnEvent(value = "messageevent")
    public void onEvent(SocketIOClient client, AckRequest request, String data)
    {
        System.out.println(request.isAckRequested());
        System.out.println("发来消息：" + data);
        server.getClient(client.getSessionId()).sendEvent("messageevent", "back data");
    }

    /**
     * 向客户端推消息
     */
    public static void sendnNewMatchEvent(List<Integer> userIdList) {
        //房间Id
        String roomId = String.valueOf(userIdList.get(0)+userIdList.get(1)+new Date().getTime());
        UUID clientId;
        if (!userIdList.isEmpty()){
            for (Integer userId:userIdList) {
                clientId = mapClient.get(userId);
                if (server.getClient(clientId) == null)
                    continue;
                //推送通知，和视频聊天房间Id
                server.getClient(clientId).sendEvent("news", roomId, 1);
            }
        }
    }

    /**
     * 向客户端推消息
     */
    public static void sendnNewMsg(UUID clientId,String msg) {
        server.getClient(clientId).sendEvent("news", msg, 1);
    }

}
