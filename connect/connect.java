package gameCenter.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connect {

	static Connection con = null;
	
	public static Connection getConnection() {
		String jdbcutf8 = "?useUnicode=true&characterEncoding=UTF-8";
		try {
			String url = "jdbc:mysql://localhost:3306/gamed";

			Class.forName("com.mysql.jdbc.Driver");
			try {
				con =  DriverManager.getConnection(url+jdbcutf8, "root",
						"123456");
				System.out.println("Connect Complete");
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//System.out.println(e);
		}
		return con;
	}
	
	
	

}
