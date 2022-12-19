package board;

import java.io.Serializable;


public class BoardFileDTO implements Serializable {
	private static final long serialVersionUID = -6675626554487766989L;

	private int f_id;
	private int number;
	private String org_name;
	private String real_name; 
	private String content_type;
	private long   length;
	
	
	
	public BoardFileDTO(int f_id, int number, String org_name, String real_name, String content_type, long length) {
		super();
		this.f_id = f_id;
		this.number = number;
		this.org_name = org_name;
		this.real_name = real_name;
		this.content_type = content_type;
		this.length = length;
	}
	
	public int getF_id() {
		return f_id;
	}
	public void setF_id(int f_id) {
		this.f_id = f_id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getOrg_name() {
		return org_name;
	}
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public String getContent_type() {
		return content_type;
	}
	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}
	public long getLength() {
		return length;
	}
	public void setLength(long length) {
		this.length = length;
	}
   
	
	
}
   

