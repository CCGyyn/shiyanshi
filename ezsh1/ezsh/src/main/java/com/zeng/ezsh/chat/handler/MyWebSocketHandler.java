package com.zeng.ezsh.chat.handler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.net.ntp.TimeStamp;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zeng.ezsh.chat.entity.Message;
/**
 * WebSocket 消息处理类 
 * 拦截路径 /chat 
 * 配置文件路径 /ezsh/src/main/resources/webSocket/spring-webSocket.xml
 * @author y
 *
 */
@Controller
public class MyWebSocketHandler implements WebSocketHandler {

	public static final Map<Integer, WebSocketSession> userSocketSessionMap; 
	//类被加载时创建此Map
	static {
		userSocketSessionMap = new HashMap<Integer, WebSocketSession>();
	}
	
	/**
     * 在关闭连接这个切面增加去除userSocketSessionMap中当前处于close状态的WebSocketSession，
     * 让新创建的WebSocketSession(open状态)可以加入到userSocketSessionMap中
     * @param webSocketSession
     * @param closeStatus
     * @throws Exception
     */
	@Override
	public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus)
			throws Exception {
		System.out.println("**********用户：[id:"+webSocketSession.getAttributes().get("uid")+"] 关闭连接****");
		Iterator<Map.Entry<Integer,WebSocketSession>> iterator = userSocketSessionMap.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Integer,WebSocketSession> entry = iterator.next();
            if(entry.getValue().getAttributes().get("uid")==webSocketSession.getAttributes().get("uid")){
                userSocketSessionMap.remove(webSocketSession.getAttributes().get("uid"));
                System.out.println("WebSocket in staticMap:" + webSocketSession.getAttributes().get("uid") + "removed");
            }
        }
	}
	//握手实现连接后
	@Override
	public void afterConnectionEstablished(WebSocketSession webSocketSession)
			throws Exception {
		int uid = (int) webSocketSession.getAttributes().get("uid");
		if(userSocketSessionMap.get(uid) == null){
			userSocketSessionMap.put(uid, webSocketSession);
		}
	}
	//发送信息前的处理
	@Override
	public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> message)
			throws Exception {
		if(message.getPayloadLength()==0)
			return;
		
		//得到Socket通道中的数据并转化为Message对象	
		Message msg = new Gson().fromJson(message.getPayload().toString(), Message.class);
		//设置时间戳
		TimeStamp now = new TimeStamp(System.currentTimeMillis());
		msg.setMessageDate(now);
		//将信息保存到数据库
		
		//设置屏蔽
		
		//发送信息 遍历发送给所有在线用户
		while(userSocketSessionMap.entrySet().iterator().hasNext()){
			WebSocketSession session = userSocketSessionMap.entrySet().iterator().next().getValue();
			if(session != null && session.isOpen()){
				session.sendMessage(new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
			}
		}
	}

	@Override
	public void handleTransportError(WebSocketSession arg0, Throwable arg1)
			throws Exception {

	}
	
	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

}
