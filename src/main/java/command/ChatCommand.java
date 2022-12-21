package command;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import chat.ChatRoomDAO;
import chat.ChatRoomDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ChatCommand{
	ChatRoomDAO chatRoomDAO = new ChatRoomDAO();
	
	
	public String chatCreate(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException, SQLException {
		// TODO Auto-generated method stub
		String chatName =request.getParameter("chatName");
		chatRoomDAO.createRoom(chatName);
		try {
			chatRoomDAO.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "chatlist.zan";
	}
	
	public String chatEnter(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException, SQLException {
		
		HttpSession session = request.getSession();
		
		System.out.println(request.getParameter("seq"));
		System.out.println(request.getParameter("title"));
		boolean isRoom =true;
		session.setAttribute("isRoom", isRoom);
		
		request.setAttribute("roomNo", request.getParameter("seq"));
		request.setAttribute("title",request.getParameter("title"));
		return "/jsp/chat/groupChat.jsp";
	}
	
	public String chatInfo(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException, SQLException {
		// TODO Auto-generated method stub
		String target = request.getParameter("seq");
		ChatRoomDTO chatRoomDTO = chatRoomDAO.getChatRoomDTO(target);
		request.setAttribute("chatRoomDTO", chatRoomDTO);
		
		
		String clients = chatRoomDTO.getClientList();
		if(clients!=null)
		{
			StringTokenizer stk=new StringTokenizer(clients,",{}");		
			ArrayList<String> clientsArray = new ArrayList<String>();
			
			while (stk.hasMoreTokens()){
			clientsArray.add(stk.nextToken());
			}
			request.setAttribute("clients", clientsArray);
		}
		return "/jsp/chat/chatInfo.jsp";
	}
	
	public String chatList(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException, SQLException {
		// TODO Auto-generated method stub
		request.setAttribute("roomList", chatRoomDAO.listRooms());
		
		return "/jsp/chat/chatLobby.jsp";
	}
}
