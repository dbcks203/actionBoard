package member;

import lombok.Data;


@Data
public class MemberBean {
	private String id;
	private String pwd;
	private String name;
	private String email;
	private String address;
	private String phone;
	private String sex;
	private String joinDate;
	
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}

	public MemberBean(String id, String pwd, String name,String sex, String email, String address,String phone) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.sex= sex;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.joinDate= null;
	}
	
	public MemberBean() {
		// TODO Auto-generated constructor stub
	}


	public String getJoinDate() {
		return joinDate;
	}


	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}


}
