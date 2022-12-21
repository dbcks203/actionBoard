package board.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import board.entity.Board;
import mvc.dao.BaseDAO;

public class BoardDAO extends BaseDAO {
	public int totalPageNo(String text) {
		int totalPageSize = 0;
		final int rowSize = 10;
		try {
			String query = "select ceil(COUNT(*) / ? ) from board where title like concat('%', ?, '%') or content like concat('%', ?, '%')or writeId like concat('%', ?, '%') ";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, rowSize);
			pstmt.setString(2, text);
			pstmt.setString(3, text);
			pstmt.setString(4, text);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				totalPageSize = rs.getInt(1);
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

		return totalPageSize;

	}

	// 게시물 목록
	public List<Board> listBoards(String text, int pageNo) {
		List<Board> list = new ArrayList<>();
		final int rowSize = 10;
		try {
			text = text == null ? "" : text;

			// connDB();
			String query = "select * from board where title like concat('%', ?, '%') or content like concat('%', ?, '%')or writeId like concat('%', ?, '%') ORDER BY parentNo, `NUMBER` limit ?, ?";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, text);
			pstmt.setString(2, text);
			pstmt.setString(3, text);
			pstmt.setInt(4, (pageNo - 1) * rowSize);
			pstmt.setInt(5, rowSize);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Board board = new Board(rs.getString("number"), rs.getString("parentNo"), rs.getString("title"),
						rs.getString("content"), rs.getString("writeId"), rs.getDate("writeDate"),
						rs.getInt("viewCount"), rs.getInt("like_count"), rs.getInt("dis_like_count"));
				System.out.println(board);
				list.add(board);
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

	// 게시물 등록
	public int addBoard(String title, String content, String writeId) throws SQLException {
		int number = 0;
		try {
			String query = "insert into board (title, content, writeId) VALUES(?,?,?)";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, writeId);
			pstmt.executeUpdate();
	
			pstmt.close();
	
			query = "SELECT LAST_INSERT_ID()";
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				number = rs.getInt(1);
			}
			rs.close();
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

		return number;
	}

	public int addBoard(Board board) throws SQLException {
		String query = "insert into board (parentNo, title, content, writeId) VALUES(?,?,?,?)";
		System.out.println("prepareStatememt: " + query);
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, board.getParentNo());
		pstmt.setString(2, board.getTitle());
		pstmt.setString(3, board.getContent());
		pstmt.setString(4, board.getWriteId());
		pstmt.executeUpdate();

		pstmt.close();

		query = "SELECT LAST_INSERT_ID()";
		pstmt = conn.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		int number = 0;
		if (rs.next()) {
			number = rs.getInt(1);
		}
		rs.close();
		pstmt.close();
		conn.commit();

		return number;
	}

	// 게시물 보기 카운드 증가
	public void incViewCount(String number) {
		try {
			String query = "";
			query = "update board set viewCount = viewCount + 1 where number = ?";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, number);
			pstmt.executeUpdate();
			pstmt.close();

			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
			}
		}
	}

	// 게시물 조회
	public Board viewBoard(String number) {
		try {

			String query = "select * from board where number = ?";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, number);
			ResultSet rs = pstmt.executeQuery();
			Board board = null;

			if (rs.next()) {
				board = new Board(rs.getString("number"), rs.getString("parentNo"), rs.getString("title"),
						rs.getString("content"), rs.getString("writeId"), rs.getDate("writeDate"),
						rs.getInt("viewCount"), rs.getInt("like_count"), rs.getInt("dis_like_count"));
			}
			rs.close();

			return board;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
			}
		}
		return null;
	}

	// 게시물 삭제
	public int deleteBoard(Board board) {
		int count = 0;
		try {
			String query = "delete from board where number = ?";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, board.getNumber());

			count = pstmt.executeUpdate();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return count;
	}

	// 게시물 수정
	public int updateBoard(Board board) {
		int count = 0;
		try {
			String query = "update board set title = ?, content = ? where number=?";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getNumber());
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

}
