package com.my.framework.websocket;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
// http://blog.naver.com/PostView.nhn?blogId=beabeak&logNo=220471878778&parentCategoryNo=&categoryNo=86&viewDate=&isShowPopularPosts=true&from=search
public class SocketHandler extends TextWebSocketHandler implements InitializingBean {

	private final Logger log = LoggerFactory.getLogger(getClass());
	private Set<WebSocketSession> sessionSet = new HashSet<WebSocketSession>();

	public SocketHandler() {
		super();
		this.log.info("create SocketHandler instance...");
	}



	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		sessionSet.remove(session);
		this.log.info("웹소켓 연결이 닫혔을 때 호출 - remove session.");
	}



	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		sessionSet.add(session);
		this.log.info("웹소켓 연결이 열리고 사용이 준비될 때 호출 - add session.");
	}



	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		super.handleMessage(session, message);
		this.log.info("클라이언트로부터 메시지가 도착했을 때 호출 - receive message : " + message.toString());
	}



	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		super.handleTransportError(session, exception);
		this.log.error("전송 에러 발생할 때 호출 - web socket error", exception);
	}



	@Override
	public boolean supportsPartialMessages() {
		this.log.info("WebsocketHandler가 부분 메시지를 처리할 때 호출 - call method!");
		return super.supportsPartialMessages();
	}

	public void sendMessage(String message) {
		for (WebSocketSession session : this.sessionSet) {
			if (session.isOpen()) {
				try {
					session.sendMessage(new TextMessage(message));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Thread thread = new Thread() {
			int i = 0;

			@Override
			public void run() {
				while (true) {
					try {
						sendMessage("send message index " + i++);
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
						break;
					}
				}
			}
		};
	}

}
