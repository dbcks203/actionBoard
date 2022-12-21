package chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/WebSocket/{seq}")
public class ChatWebsocket {

	static Map<String, ArrayList<Session>> roomRecord = new HashMap<String, ArrayList<Session>>();
	static Map<Session, String> clientSeq = Collections.synchronizedMap(new HashMap<>());

	// private ChatServiceAbstract chatService = ChatService.getInstance();
	// private MessageServiceInterface messageService =
	// MessageService.getInstance();

	@OnOpen
	public void onOpen(@PathParam("seq") String seq, Session session) {
		System.out.println(seq);
		System.out.println("client is now connected...");
		if (!roomRecord.containsKey(seq)) {
			roomRecord.put(seq, new ArrayList<Session>());
		}
		roomRecord.get(seq).add(session);
		clientSeq.put(session, seq);
		/*
		 * if (chatService.register(this)) { this.session = session; this.username =
		 * username; String receiver = "all"; //MessageDTO msgResponse = new
		 * MessageDTO(this.username, "[P]open", "text", receiver, null);
		 * //chatService.sendMessageToAllUsers(msgResponse); }
		 */
	}

	@OnError
	public void onError(Throwable throwable) {
		throwable.printStackTrace();

	}

	@OnMessage
	public void onMessage(String message, Session session) {
		// messageService.saveMessage(message);

		System.out.println(roomRecord.get(clientSeq.get(session)));

		JSONObject jsonObject = new JSONObject(message);

		String sendMessage = jsonObject.getString("userName") + " : " + jsonObject.getString("message");

		// session관리 리스트에서 Session을 취득한다.
		Collection<Session> values = roomRecord.get(clientSeq.get(session));

		for (Session client : values) {

			if (session != client) {
				try {
					// 리스트에 있는 모든 세션(메시지 보낸 유저 제외)에 메시지를 보낸다. (형식: 유저명 => 메시지)
					client.getBasicRemote().sendText(sendMessage);
				} catch (IOException e) {
					// 에러가 발생하면 콘솔에 표시한다.
					e.printStackTrace();
				}
			}

		}
	}

	@OnClose
	public void onClose(Session session) {
		String seq = clientSeq.get(session);
		roomRecord.get(seq).remove(session);
		clientSeq.remove(session);
		/*
		if(roomRecord.get(seq)!=null) {
			roomRecord.remove(roomRecord.get(seq));
		}
		*/
		System.out.println("client is now disconnected...");
	}

}
