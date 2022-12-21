package chat;

import java.sql.SQLException;
import java.util.List;

public interface ChatRoomDAOInterface {
	
	public List<ChatRoomDTO> listRooms() ;
	
	public int createRoom(String chatName) ;
	
	
	public ChatRoomDTO getChatRoomDTO(String seq) throws SQLException ;
	
	
	public void updateClient(String seq,List<String> clients);
	
	public void close() throws Exception;
}
