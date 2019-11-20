package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class link {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			// jdbc/myoracle占쎌뵠占쏙옙 占쎌뵠�뵳袁⑹뱽 揶쏆빘猿쒐몴占� 筌≪뼚釉섓옙苑� DataSource揶쏉옙 獄쏆룆�뮉占쎈뼄.
			DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
			// ds揶쏉옙 占쎄문占쎄쉐占쎈┷占쎈�占쎌몵沃섓옙嚥∽옙 Connection占쎌뱽 �뤃�뗫�占쎈빍占쎈뼄.
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	// select占쎌뱽 占쎈땾占쎈뻬占쎈립 占쎌뜎 �뵳�딅꺖占쎈뮞 占쎈퉸占쎌젫�몴占� 占쎌맄占쎈립 筌롫뗄�꺖占쎈굡
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// insert, update, delete 占쎌삂占쎈씜占쎌뱽 占쎈땾占쎈뻬占쎈립 占쎌뜎 �뵳�딅꺖占쎈뮞 占쎈퉸占쎌젫�몴占� 占쎌맄占쎈립 筌롫뗄�꺖占쎈굡
	public static void close(Connection conn, Statement stmt) {
		try {
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}