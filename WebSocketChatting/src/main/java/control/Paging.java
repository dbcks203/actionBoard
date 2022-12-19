package control;

public class Paging {

	private int currentPageNo; // 현재 페이지
	private int pageSize; // 한 페이지당 글 수
	private int totalPageNo; // 총 글 갯수
	private int startPageNo; // 현재페이지의 시작번호
	private int endPageNo; // 현재페이지의 끝번호

	public Paging(int currentPageNo, int pageSize, int totalPageNo) {
		super();
		this.currentPageNo = currentPageNo;
		this.totalPageNo = totalPageNo;
		//this.startPageNo = ((pageNo - 1) / pageSize) * pageSize + 1;
		this.endPageNo = startPageNo + pageSize - 1;

		if (endPageNo > totalPageNo)
			endPageNo = totalPageNo;
	}

}
