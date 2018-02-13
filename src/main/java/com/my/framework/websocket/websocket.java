package com.my.framework.websocket;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//http://nowonbun.tistory.com/285
@ServerEndpoint("/websocketTest")
public class websocket {

	private Logger log = LoggerFactory.getLogger(websocket.class);

	@OnOpen
	public void handleOpen() {
		log.debug("서버 측에서 클라이언트와 웹 소켓이 연결되면 호출되는 이벤트 - client is now connected...");
	}

	@OnMessage
	public String handleMessage(String message) {
		log.debug("웹 소켓으로부터 메시지가 오면 호출되는 이벤트 - " + message);
		String replaymessage = "echo " + message;
		return replaymessage;
	}

	@OnClose
	public void handleClose() {
		log.debug("웹 소켓이 닫히면 호출되는 이벤트");
	}

	@OnError
	public void handleError(Throwable t) {
		log.error("웹 소켓이 에러가 나면 호출되는 이벤트");
		t.printStackTrace();
	}
}
