package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.DTO_Game;

public class DAO_Game {

	private static DAO_Game md = null;

	private DAO_Game() {
	}

	public static DAO_Game getInstance() { // �̱���
		if (md == null) {
			md = new DAO_Game();
		}
		return md;
	}

	public Connection getConnection() {
		Connection conn = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@10.0.0.10:1521:orcl", "system", "1111");

		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("Connection Faile");
		}

		return conn;
	}

	public void list(String a, DTO_Game dtoG) {
		System.out.println("GameList 1");

		// ����̹��ε� + connection ����� �ܰ�
		Connection conn = getConnection();
		PreparedStatement ppst = null;
		ResultSet rs = null;
		int dbcnt = 0;
		try {
			// ���� �ۼ��ܰ�
			ppst = conn.prepareStatement("select * from game where english = ?");
			ppst.setString(1, a);
			// ���� ����ܰ�
			rs = ppst.executeQuery();
			// ������ ����� �ִ°�?

			if (rs.next()) {
				System.out.println("����� ����");
				dbcnt = rs.getInt("cnt");
				updata(dtoG, dbcnt);
			} else {
				System.out.println("����� ����");
				insert(dtoG);
			}
		} catch (SQLException e) {
			System.out.println("SQL Error chkList");
			e.printStackTrace();
		} finally {
			try {
				if (ppst != null)
					ppst.close();
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				System.out.println("connection close error");
			}
		}
	}

	public void insert(DTO_Game gt) { // �ܾ��Է�
		Connection conn = getConnection();
		PreparedStatement ppst = null;

		try {
			// ���� �ۼ��ܰ�
			ppst = conn.prepareStatement("insert into Game values (?,?,?)");
			ppst.setString(1, gt.getEn());
			System.out.println("insert game en : " + gt.getEn());
			ppst.setString(2, gt.getKr());
			System.out.println("insert game kr : " + gt.getKr());
			ppst.setInt(3, 1);

			// ���� ����ܰ�r());
			ppst.executeUpdate();

		} catch (Exception e) {
			System.out.println("SQL Error Insert");
		} finally {
			try {
				if (ppst != null)
					ppst.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println("connection close error");
			}
		}
	}

	public ArrayList<DTO_Game> getList() {

		// ����̹��ε� + connection ����� �ܰ�
		Connection conn = getConnection();
		PreparedStatement ppst = null;
		ResultSet rs = null;
		ArrayList<DTO_Game> GDTOList = null;
		try {
			// ���� �ۼ��ܰ�
			ppst = conn.prepareStatement("select * from game order by cnt desc");

			// ���� ����ܰ�
			rs = ppst.executeQuery();

			// ������ ����� �ִ°�?
			if (rs.next()) {
				GDTOList = new ArrayList<DTO_Game>();
				do {
					DTO_Game dtg = new DTO_Game();
					dtg.setEn(rs.getString("English"));
					dtg.setKr(rs.getString("Korea"));
					dtg.setCnt(rs.getInt("Cnt"));
					GDTOList.add(dtg);

				} while (rs.next());
			}

		} catch (SQLException e) {
			System.out.println("SQL Error2");
			e.printStackTrace();
		} finally {
			try {
				if (ppst != null)
					ppst.close();
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				System.out.println("connection close error");
			}
		}
		return GDTOList;
	}

	public void updata(DTO_Game dg, int cnt) {
		Connection conn = getConnection();
		PreparedStatement ppst = null;

		try {
			System.out.println("update");
			ppst = conn.prepareStatement("update Game set Korea = ?, Cnt=? where English = ?");
			ppst.setString(1, dg.getKr());
			System.out.println("update kr : " + dg.getKr());
			ppst.setInt(2, cnt + 1);
			ppst.setString(3, dg.getEn());
			System.out.println("update en : " + dg.getEn());

			// ���� ����ܰ�
			ppst.executeUpdate();

		} catch (Exception e) {
			System.out.println("SQL Error3");
		} finally {
			try {
				if (ppst != null)
					ppst.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println("connection close error");
			}
		}

	}

}
