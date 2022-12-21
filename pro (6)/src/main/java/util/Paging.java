package util;

import lombok.Data;

@Data
public class Paging {
	private int currentPageNo;
	private int pageSize = 10;
	private int totalPageNo;
	private int startPageNo;
	private int endPageNo;
	
	public Paging(int currentPageNo, int totalPageNo) {
		super();
		this.currentPageNo = currentPageNo;
		int startPageNo = ((currentPageNo - 1) / pageSize) * pageSize + 1;
		int endPageNo = startPageNo + pageSize - 1;
		
		if (endPageNo > totalPageNo) endPageNo = totalPageNo;
	}
	
}
