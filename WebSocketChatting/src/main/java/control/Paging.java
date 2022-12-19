package control;


public class Paging {
	
	private int pageNo;
	private int pageSize;
	private int totalPageNo;
	private int startPageNo;
	private int endPageNo;
	
	
	public Paging(int pageNo, int totalPageNo) {
		super();
		this.pageNo = pageNo;
		this.totalPageNo = totalPageNo;
		this.startPageNo = ((pageNo - 1) / pageSize) * pageSize + 1;
		this.endPageNo = startPageNo + pageSize - 1;
	}


	public int getPageNo() {
		return pageNo;
	}


	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public int getTotalPageNo() {
		return totalPageNo;
	}


	public void setTotalPageNo(int totalPageNo) {
		this.totalPageNo = totalPageNo;
	}


	public int getStartPageNo() {
		return startPageNo;
	}


	public void setStartPageNo(int startPageNo) {
		this.startPageNo = startPageNo;
	}


	public int getEndPageNo() {
		return endPageNo;
	}


	public void setEndPageNo(int endPageNo) {
		this.endPageNo = endPageNo;
	}
	
	
		
}
