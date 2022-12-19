package chat;

import lombok.Data;

@Data
public class ChatRoomDTO {

	private String seq;
	private String title; 
	private int clientCount;
	private String clientList;
	private String createDate;
	
	
	public ChatRoomDTO(String seq, String title, int clientCount, String clientList, String createDate) {
		this.seq = seq;
		this.title = title;
		this.clientCount = clientCount;
		this.clientList = clientList;
		this.createDate = createDate;
	}


	public ChatRoomDTO() {
		// TODO Auto-generated constructor stub
	}


	public void setSeq(String seq) {
		// TODO Auto-generated method stub
		this.seq = seq;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getClientCount() {
		return clientCount;
	}


	public void setClientCount(int clientCount) {
		this.clientCount = clientCount;
	}


	public String getClientList() {
		return clientList;
	}


	public void setClientList(String clientList) {
		this.clientList = clientList;
	}


	public String getCreateDate() {
		return createDate;
	}


	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}


	public String getSeq() {
		return seq;
	}
}