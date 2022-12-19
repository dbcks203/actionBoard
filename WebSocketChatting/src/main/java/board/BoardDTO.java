package board;



public class BoardDTO {
	private String seq;
	private String userid;
	private String parentNo;
	private String subject;
	private String content;
	private String regdate;
	private String tag;
	private int readcount;
	private int dis_like_count;
	private int like_count;
	
	public BoardDTO(String seq, String parentNo, String userid, String subject, String content, String regdate,
			 String tag, int readcount, int dis_like_count, int like_count) {
		super();
		this.seq = seq;
		this.parentNo = parentNo;
		this.userid = userid;
		this.subject = subject;
		this.content = content;
		this.regdate = regdate;
		this.readcount = readcount;
		this.tag = tag;
		this.dis_like_count = dis_like_count;
		this.like_count = like_count;
	}
	
	public BoardDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getParentNo() {
		return parentNo;
	}
	public void setParentNo(String parentNo) {
		this.parentNo = parentNo;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public int getDis_like_count() {
		return dis_like_count;
	}
	public void setDis_like_count(int dis_like_count) {
		this.dis_like_count = dis_like_count;
	}
	public int getLike_count() {
		return like_count;
	}
	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}
	
	
	
}
