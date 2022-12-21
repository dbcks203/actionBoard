package noticeBoard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class NoticeBoardDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;

public NoticeBoardDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/proDB");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//공지글 목록 
	public List<NoticeBoard> listNoticeBoards(String text) {
		List<NoticeBoard> list = new ArrayList<>();
		try {
			text = text == null ? "" : text;						
			conn = dataFactory.getConnection();
			String query = "select * from noticeboard where title like concat('%', ?, '%') or content like concat('%', ?, '%')or writeId like concat('%', ?, '%')";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, text);
			pstmt.setString(2, text);
			pstmt.setString(3, text);
			ResultSet rs = pstmt.executeQuery();			
			while (rs.next()) {
				NoticeBoard NoticeBoard = new NoticeBoard(
						rs.getInt("number"),	
						rs.getString("title"),	
						rs.getString("content"),
						rs.getString("writeId"),
						rs.getDate("writeDate"),
						rs.getInt("viewCount"));
				System.out.println(NoticeBoard);
				list.add(NoticeBoard);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//이제 원하는 것을 말해봐 
	//아니 
	
	
//공지글 등록
	public void addNoticeBoard(String title, String content, String writeId) throws SQLException {
			Connection conn = dataFactory.getConnection();
			String query = "insert into noticeboard (title, content, writeId) VALUES(?,?,?)";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, writeId);
			pstmt.executeUpdate();			
			conn.close();
			pstmt.close();
	}

//공지글 조회	
	public NoticeBoard viewNoticeBoard(String number, boolean updated) {
		try {						
			conn = dataFactory.getConnection();			
			String query = "";
			if (updated) {
				query = "update noticeboard set viewCount = viewCount + 1 where number = ?";
				System.out.println("prepareStatememt: " + query);
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, number);
				pstmt.executeUpdate();
				pstmt.close();
			}						
			query = "select * from noticeboard where number = ?";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, number);
			ResultSet rs = pstmt.executeQuery();
			NoticeBoard NoticeBoard = null;			
			if (rs.next()) {
				NoticeBoard = new NoticeBoard(
						rs.getInt("number"),	
						rs.getString("title"),	
						rs.getString("content"),
						rs.getString("writeId"),
						rs.getDate("writeDate"),
						rs.getInt("viewCount"));
			}
			rs.close();			
			return NoticeBoard;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {}
		}
		return null;		
	}

	//공지글 삭제
	public int deleteNoticeBoard(NoticeBoard NoticeBoard) {
		int count = 0;
		try {
			Connection conn = dataFactory.getConnection();
			String query = "delete from noticeboard where number = ?";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, NoticeBoard.getNumber());								
			count = pstmt.executeUpdate();
			pstmt.close();				
		}catch (Exception e) {
			e.printStackTrace();			
		}
		return count;
	}	
			
	//공지글 수정
	public int updateNoticeBoard(NoticeBoard NoticeBoard) {
		int count = 0;
		try {
			Connection conn = dataFactory.getConnection();
			String query = "update noticeboard set title = ?, content = ? where number=?";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, NoticeBoard.getTitle());			
			pstmt.setString(2, NoticeBoard.getContent());													
			pstmt.setInt(3, NoticeBoard.getNumber());								
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
}
	

			
			
