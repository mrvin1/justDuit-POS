package connect;

import java.sql.*;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class Connect {
	private static Connection conn;
	
	public static Connection connect() {
		if (conn == null) {
			MysqlDataSource source = new MysqlDataSource();
			source.setServerName("localhost");
			source.setUser("root");
			source.setPassword("");
			source.setDatabaseName("justduit");
			
			try {
				conn = source.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("failed to connect");
			}
		}
		return conn;
	}	
}