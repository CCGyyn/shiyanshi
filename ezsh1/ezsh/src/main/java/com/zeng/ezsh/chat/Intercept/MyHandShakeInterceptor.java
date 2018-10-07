package com.zeng.ezsh.chat.Intercept;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.zeng.ezsh.wy.entity.ResidentialUser;
import com.zeng.ezsh.wy.utils.AccessTokenUtil;

public class MyHandShakeInterceptor implements HandshakeInterceptor {

	@Override
	public void afterHandshake(ServerHttpRequest arg0, ServerHttpResponse arg1,
			WebSocketHandler arg2, Exception arg3) {
		System.out.println("***************握手结束，连接已创建**********");
	}

	@Override
	public boolean beforeHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler webSocketHandler,
			Map<String, Object> map) throws Exception {
		System.out.println("**************开始进行握手******************");
		String accessToken = (String) ((ServletServerHttpRequest) request).getServletRequest().getSession(false).getAttribute("token");
		ResidentialUser user = AccessTokenUtil.parserAccessTokenToModel(accessToken);
		
        if(user!=null){
            map.put("uid", user.getUserId());//为服务器创建WebSocketSession做准备
            System.out.println("用户id："+user.getUserId()+" 被加入");
        }else{
            System.out.println("user为空");
            return false;
        }
        return true;
	}

}
