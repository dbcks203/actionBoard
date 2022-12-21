package mvc.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public class WebUtil {
	private static DataSource dataSource;
	
	public static void setDataSource(DataSource dataSource) {
		WebUtil.dataSource = dataSource;
	}
	
	public static Connection getConnection() throws SQLException {
		if (WebUtil.dataSource == null) return null;
		return WebUtil.dataSource.getConnection();
	}
}
