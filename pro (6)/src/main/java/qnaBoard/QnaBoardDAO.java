package qnaBoard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class QnaBoardDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;

public QnaBoardDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/proDB");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//Qna 목록 
	public List<QnaBoard> listQnaBoards(String text) {
		List<QnaBoard> list = new ArrayList<>();
		try {
			text = text == null ? "" : text;						
			conn = dataFactory.getConnection();
			String query = "select * from qnaboard where title like concat('%', ?, '%') or content like concat('%', ?, '%')or writeId like concat('%', ?, '%')";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, text);
			pstmt.setString(2, text);
			pstmt.setString(3, text);
			ResultSet rs = pstmt.executeQuery();			
			while (rs.next()) {
				QnaBoard QnaBoard = new QnaBoard(
						rs.getInt("number"),	
						rs.getString("title"),	
						rs.getString("content"),
						rs.getString("writeId"),
						rs.getDate("writeDate"),
						rs.getInt("viewCount"));
				System.out.println(QnaBoard);
				list.add(QnaBoard);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
//Qna 등록
	public void addQnaBoard(String title, String content, String writeId) throws SQLException {
			Connection conn = dataFactory.getConnection();
			String query = "insert into qnaboard (title, content, writeId) VALUES(?,?,?)";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, writeId);
			pstmt.executeUpdate();			
			conn.close();
			pstmt.close();
	}

//Qna 조회	
	public QnaBoard viewQnaBoard(String number, boolean updated) {
		try {						
			conn = dataFactory.getConnection();			
			String query = "";
			if (updated) {
				query = "update qnaboard set viewCount = viewCount + 1 where number = ?";
				System.out.println("prepareStatememt: " + query);
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, number);
				pstmt.executeUpdate();
				pstmt.close();
			}						
			query = "select * from qnaboard where number = ?";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, number);
			ResultSet rs = pstmt.executeQuery();
			QnaBoard QnaBoard = null;			
			if (rs.next()) {
				QnaBoard = new QnaBoard(
						rs.getInt("number"),	
						rs.getString("title"),	
						rs.getString("content"),
						rs.getString("writeId"),
						rs.getDate("writeDate"),
						rs.getInt("viewCount"));
			}
			rs.close();			
			return QnaBoard;
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

	//Qna 삭제
	public int deleteQnaBoard(QnaBoard QnaBoard) {
		int count = 0;
		try {
			Connection conn = dataFactory.getConnection();
			String query = "delete from qnaboard where number = ?";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, QnaBoard.getNumber());								
			count = pstmt.executeUpdate();
			pstmt.close();				
		}catch (Exception e) {
			e.printStackTrace();			
		}
		return count;
	}	
			
	//Qna 수정
	public int updateQnaBoard(QnaBoard QnaBoard) {
		int count = 0;
		try {
			Connection conn = dataFactory.getConnection();
			String query = "update qnaboard set title = ?, content = ? where number=?";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, QnaBoard.getTitle());			
			pstmt.setString(2, QnaBoard.getContent());													
			pstmt.setInt(3, QnaBoard.getNumber());								
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
}
	

			
			
