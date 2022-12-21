package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import member.entity.Member;
import mvc.util.WebUtil;

public class MemberDAO {
	private Connection conn;
	private PreparedStatement pstmt;

	public MemberDAO() {
		try {
			conn = WebUtil.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Member> listMembers() {
		List<Member> list = new ArrayList<>();
		try {
			// connDB();
			String query = "select * from member";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Member member = new Member(rs.getString("uid"), rs.getString("pwd"), rs.getString("name"),
						rs.getString("sex"), rs.getString("address"), rs.getString("phone"), rs.getString("email"),
						rs.getString("admin"), rs.getDate("createDate"));
				System.out.println(member);
				list.add(member);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void addMember(Member member) {
		try {
			String query = "insert into member";
			query += " (uid, pwd, name, sex, address, phone, email)";
			query += " values(?,?,?,?,?,?,?)";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getUid());
			pstmt.setString(2, member.getPwd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getSex());
			pstmt.setString(5, member.getAddress());
			pstmt.setString(6, member.getPhone());
			pstmt.setString(7, member.getEmail());
			pstmt.executeQuery();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Member viewMember(String uid) {
		try {
			// connDB();
			String query = "select * from member where uid = ?";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, uid);
			ResultSet rs = pstmt.executeQuery();
			Member member = null;

			if (rs.next()) {
				member = new Member(rs.getString("uid"), rs.getString("pwd"), rs.getString("name"), rs.getString("sex"),
						rs.getString("address"), rs.getString("phone"), rs.getString("email"), rs.getString("admin"),
						rs.getDate("createDate"));
			}
			rs.close();

			return member;
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

	public int deleteMember(Member member) {
		int count = 0;
		try {
			String query = "delete from member where uid = ?";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getUid());

			count = pstmt.executeUpdate();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return count;
	}

	public int updateMember(Member member) {
		int count = 0;
		try {
			String query = "update member set pwd = ?, name = ?, sex = ?, address = ?, phone =?, email = ? where uid=?";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getPwd());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getSex());
			pstmt.setString(4, member.getAddress());
			pstmt.setString(5, member.getPhone());
			pstmt.setString(6, member.getEmail());
			pstmt.setString(7, member.getUid());
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public Member findUidMemberByPhone(String name, String phone) {
		Member member = null;
		try {
			String query = "select * from member where name = ? and phone = ?";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				member = Member.builder().uid(rs.getString("uid")).pwd(rs.getString("pwd")).name(rs.getString("name"))
						.sex(rs.getString("sex")).address(rs.getString("address")).phone(rs.getString("phone"))
						.email(rs.getString("email")).build();
			}

			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}

	public Member findPwdMemberByPhone(String uid, String name, String phone) {
		Member member = null;
		try {
			String query = "select * from member where uid =? and name = ? and phone = ?";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, uid);
			pstmt.setString(2, name);
			pstmt.setString(3, phone);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				member = Member.builder().uid(rs.getString("uid")).pwd(rs.getString("pwd")).name(rs.getString("name"))
						.sex(rs.getString("sex")).address(rs.getString("address")).phone(rs.getString("phone"))
						.email(rs.getString("email")).build();
			}

			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}

	public void close() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
