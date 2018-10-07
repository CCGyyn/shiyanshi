package com.zeng.ezsh.chat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.zeng.ezsh.chat.Intercept.MyHandShakeInterceptor;
import com.zeng.ezsh.chat.handler.MyWebSocketHandler;
//@Component
//@EnableWebSocket
public class MyWebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer{

//	@Autowired
//	MyWebSocketHandler handler;
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
//		webSocketHandlerRegistry.addHandler(handler, "/chat").addInterceptors(new MyHandShakeInterceptor());
	}
//
//	
//	
}
