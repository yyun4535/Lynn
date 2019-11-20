package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.DTO_Word;

public class DAO_Word {

	ArrayList<DTO_Word> WordDTOList = null;

	private static DAO_Word md = null;

	private DAO_Word() {
	}

	public static DAO_Word getInstance() { // �̱���
		if (md == null) {
			md = new DAO_Word();
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

	public void insert(DTO_Word inOne) { // �ܾ��Է�
		Connection conn = getConnection();
		PreparedStatement ppst = null;

		try {
			// ���� �ۼ��ܰ�
			ppst = conn.prepareStatement("insert into Word values(?, ?, ?)");
			ppst.setString(1, inOne.getId());
			System.out.println("DAO ���̵�" + inOne.getId());
			ppst.setString(2, inOne.getEn());
			System.out.println("DAO ���� " + inOne.getEn());
			ppst.setString(3, inOne.getKr());
			System.out.println("DAO �ѱ�" + inOne.getKr());
			// ���� ����ܰ�
			ppst.executeUpdate();

		} catch (Exception e) {
			System.out.println("SQL Error1");
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

	public ArrayList<DTO_Word> getList(String id) {

		// ����̹��ε� + connection ����� �ܰ�
		Connection conn = getConnection();
		PreparedStatement ppst = null;
		ResultSet rs = null;
		// ArrayList<DTO_Word> WordDTOList = null;
		try {
			// ���� �ۼ��ܰ�

			ppst = conn.prepareStatement("select * from word where id = ?");
			ppst.setString(1, id);
			System.out.println("DAO id : " + id);

			// ���� ����ܰ�
			rs = ppst.executeQuery();

			// ������ ����� �ִ°�?
			if (rs.next()) {

				WordDTOList = new ArrayList<DTO_Word>();

				do {
					DTO_Word dtoW = new DTO_Word();
					dtoW.setId(rs.getString("Id"));
					dtoW.setEn(rs.getString("English"));
					dtoW.setKr(rs.getString("Korea"));
					WordDTOList.add(dtoW);

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

		return WordDTOList;
	}

	public void updata(DTO_Word inOne) {

		Connection conn = getConnection();
		PreparedStatement ppst = null;

		try {
			ppst = conn.prepareStatement("update Word set English=?, Korea=? where id=? and English=?");

			ppst.setString(1, inOne.getEn());
			System.out.println("DB : " + inOne.getEn());
			ppst.setString(2, inOne.getKr());
			System.out.println("DB : " + inOne.getKr());
			ppst.setString(3, inOne.getId());
			System.out.println("DB : " + inOne.getId());
			ppst.setString(4, inOne.getEn());
			System.out.println("DB : " + inOne.getEn());
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

	public void del(String word) {
		Connection conn = getConnection();
		PreparedStatement ppst = null;
		try {
			ppst = conn.prepareStatement("delete from Word where english=?");
			ppst.setString(1, word);
			ppst.executeUpdate();
		} catch (Exception e) {
			System.out.println("SQL Error4");

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
