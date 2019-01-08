package com.android.qrcode.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@ServerEndpoint("/QRcodeScanner/{userName}")
public class QRcodeScanner {
	private static Map<String,Session> sessionsMap = new ConcurrentHashMap<>();
	Gson gson = new Gson();
	
	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession) throws IOException {
		// 設定成500KB為了配合Android bundle傳送大小
				int maxBufferSize = 500 * 1024;
				userSession.setMaxTextMessageBufferSize(maxBufferSize);
				userSession.setMaxBinaryMessageBufferSize(maxBufferSize);
				/* save the new user in the map */
				sessionsMap.put(userName, userSession);
				System.out.println(userName);
	}
	
	@OnMessage
	public void onMessage(Session userSession,String message) {
		String memberNo = null;
		try {
			JSONObject jsin = new JSONObject(message);
			memberNo = jsin.getString("memberNo");
		} catch (Exception e) {
			e.getStackTrace();
		}
		Session session = sessionsMap.get(memberNo);
		session.getAsyncRemote().sendText("success");
	}
	
	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}
	
	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		Set<String> userNames = sessionsMap.keySet();
		for (String userName : userNames) {
			if (sessionsMap.get(userName).equals(userSession)) {
				sessionsMap.remove(userName);
				break;
			}
		}

		String text = String.format("session ID = %s, disconnected; close code = %d%nusers: %s", userSession.getId(),
				reason.getCloseCode().getCode(), userNames);
		System.out.println(text);
	}
}
