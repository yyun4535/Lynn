package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.DTO_Member;

public class DAO_Member {// �������� �����ͺ��̽�

	private static DAO_Member md = null;

	private DAO_Member() {

	}

	public static DAO_Member getInstance() {// �̱���
		if (md == null) {
			md = new DAO_Member();
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

	public void insert(DTO_Member inOne) {
		Connection conn = getConnection();
		PreparedStatement ppst = null;
		try {
			// ���� �ۼ��ܰ�
			ppst = conn.prepareStatement("insert into member values(?, ?)");

			ppst.setString(1, inOne.getId());
			ppst.setString(2, inOne.getPw());

			// ���� ����ܰ�
			ppst.executeUpdate();

		} catch (Exception e) {
			System.out.println("SQL Error_M1");
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

	public ArrayList<DTO_Member> getList() {

		// ����̹��ε� + connection ����� �ܰ�
		Connection conn = getConnection();
		PreparedStatement ppst = null;
		ResultSet rs = null;
		ArrayList<DTO_Member> memberDTOList = null;

		try {
			// ���� �ۼ��ܰ�
			ppst = conn.prepareStatement("select * from Member");
			// ���� ����ܰ�
			rs = ppst.executeQuery();

			// ������ ����� �ִ°�?
			if (rs.next()) {
				memberDTOList = new ArrayList<DTO_Member>();
				do {
					DTO_Member dto = new DTO_Member();

					dto.setId(rs.getString("id"));
					dto.setPw(rs.getString("pwd"));
					memberDTOList.add(dto);

				} while (rs.next());
			}

		} catch (Exception e) {
			System.out.println("SQL Error_M2");
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

		return memberDTOList;
	}
}