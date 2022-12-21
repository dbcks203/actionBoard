package mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import board.entity.Board;
import mvc.util.WebUtil;

//import join1.MemberBean;



public class BaseDAO {
	protected Connection conn;
	protected PreparedStatement pstmt;

	public BaseDAO() {
		try {
			conn = WebUtil.getConnection();
			conn.setAutoCommit(false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
