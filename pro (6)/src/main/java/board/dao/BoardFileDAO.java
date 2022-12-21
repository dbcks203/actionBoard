package board.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import board.entity.BoardFile;
import mvc.dao.BaseDAO;

//import join1.MemberBean;

public class BoardFileDAO extends BaseDAO {

	// 게시물에 속한 첨부파일 목록
	public List<BoardFile> list(int number) {
		List<BoardFile> list = new ArrayList<>();
		try {
			// connDB();
			String query = "select * from board_file where `number` = ?";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, number);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardFile boardFile = new BoardFile(rs.getInt("f_id"), rs.getInt("number"), rs.getString("org_name"),
						rs.getString("real_name"), rs.getString("content_type"), rs.getLong("length"));
				System.out.println(boardFile);
				list.add(boardFile);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
					pstmt = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

//게시물 등록
	public void insertBoardFile(BoardFile boardFile) throws SQLException {
		try {
			String query = "insert into board_file (number, org_name, real_name, content_type, length) VALUES(?,?,?,?,?)";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardFile.getNumber());
			pstmt.setString(2, boardFile.getOrg_name());
			pstmt.setString(3, boardFile.getReal_name());
			pstmt.setString(4, boardFile.getContent_type());
			pstmt.setLong(5, boardFile.getLength());
			pstmt.executeUpdate();

			pstmt.close();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
					pstmt = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}