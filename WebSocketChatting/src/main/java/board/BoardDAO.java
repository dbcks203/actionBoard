package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO implements BoardDAOInterface {

	Connection conn;
	PreparedStatement pstmt;
	DataSource dataFactory;
	private static BoardDAO instance = null;

	public BoardDAO() {
		try {
			Context context = new InitialContext();
			Context envContext = (Context) context.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/WebSocketChatting");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized static BoardDAO getInstance() {
		if (instance == null) {
			instance = new BoardDAO();
		}
		return instance;
	}

	// AddOk 서블릿이 DTO를 줄테니, insert를 해주세요.
	public int articleInsert(BoardDTO dto) {
		try {
			conn = dataFactory.getConnection();
			String query = "insert into tblBoards (seq, parentNo, userid, subject, content, regdate, readcount, tag)"
					+ " values (NEXTVAL(seqBoards),lastval(seqBoards), ?, ?, ?, default, default, ?)";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, dto.getUserid());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getTag());
			pstmt.executeUpdate(); // 성공시 1 실패시 0

			query = "select lastval(seqBoards)";
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			double number = 0;
			if (rs.next()) {
				number = rs.getDouble(1);
			}
			rs.close();
			pstmt.close();
			return (int) number;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}
	
	
	public int articleInsert(String parentNo, String subject, String content, String userid) throws SQLException {
			conn = dataFactory.getConnection();
			String query = "insert into tblboards (seq, parentNo, subject, content, userid) VALUES(NEXTVAL(seqBoards),?,?,?,?)";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, parentNo);
			pstmt.setString(2, subject);
			pstmt.setString(3, content);
			pstmt.setString(4, userid);
			pstmt.executeUpdate();
			
			pstmt.close();
			
			query = "select lastval(seqBoards)";
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			double number = 0;
			if (rs.next() ) {
				number = rs.getDouble(1);
			}
			rs.close();
			pstmt.close();
			
			return (int) number;
	}
	
	public int totalPageNo(String text) {
		int totalPageSize = 0; 
		final int rowSize = 10; 
		if(text==null)
			text="";
		try {
			conn = dataFactory.getConnection();
			String query = "select ceil(COUNT(*) / ? ) from tblboards where subject like concat('%', ?, '%') or content like concat('%', ?, '%')or userid like concat('%', ?, '%') ";
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
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalPageSize; 

	}
	
	public List<BoardDTO> listArticles(String text, int pageNo) {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		final int rowSize= 10;
		try {
			conn = dataFactory.getConnection();
			text = text == null ? "" : text;
			String query = "select * from tblboards where subject like concat('%', ?, '%') or content like concat('%', ?, '%')or userid like concat('%', ?, '%') ORDER BY parentNo, `seq` limit ?, ?";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, text);
			pstmt.setString(2, text);
			pstmt.setString(3, text);
			pstmt.setInt(4, (pageNo-1) * rowSize);
			pstmt.setInt(5, rowSize);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BoardDTO board = new BoardDTO(
						rs.getString("seq"),	
						rs.getString("parentNo"),	
						rs.getString("userid"),	
						rs.getString("subject"),
						rs.getString("content"),
						rs.getString("regdate"),
						rs.getString("tag"),
						rs.getInt("readcount"),
						rs.getInt("like_count"),
						rs.getInt("dis_like_count")
						);
				
				list.add(board);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	// View 서블릿이 글번호를 줄테니 레코드 내용 전부를 DTO에 담아서 돌려주세요!
	public BoardDTO getBoardDTO(String seq) {
		BoardDTO dto = new BoardDTO();
		try {
			conn = dataFactory.getConnection();
			String query = "select * from tblBoards where seq= ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(seq));
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.setSeq(rs.getString("seq"));
				dto.setParentNo(rs.getString("parentNo"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setUserid(rs.getString("userid"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setTag(rs.getString("tag"));
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	// View 서블릿이 글번호를 줄테니 조회수를 +1 해주세요!
	public void updateReadCount(String seq) {
		try {
			conn = dataFactory.getConnection();
			String query = "update tblBoards set readcount = readcount + 1 where seq=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, seq);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// EditOk 서블릿이 수정할 DTO를 줄테니 update 해주세요!
	public int articleEdit(BoardDTO boardDTO) {
		try {
			conn = dataFactory.getConnection();
			String query = "update tblBoards set subject=?, content=? where seq=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, boardDTO.getSubject());
			pstmt.setString(2, boardDTO.getContent());
			pstmt.setString(3, boardDTO.getSeq());
			System.out.println(boardDTO.getSubject() + boardDTO.getContent() + boardDTO.getSeq());
			return pstmt.executeUpdate(); // 성공시 1 실패시 0
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// DelOk 서블릿이 글번호를 줄테니 글을 삭제해주세요!
	public int articleDelete(String seq) {
		try {
			conn = dataFactory.getConnection();
			String query = "delete from tblBoards where seq=?";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, seq);
			
			return pstmt.executeUpdate(); // 성공시 1 실패시 0

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	public void close() throws Exception {
		if (conn != null) {
			conn.close();
		}
	}
}
