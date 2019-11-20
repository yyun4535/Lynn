package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.MDTO;

public class MDAO extends link {

	private MDAO() {
	}

	private static MDAO instance = new MDAO();

	public static MDAO getInstance() {
		return instance;
	}

	// c Read u d
	public ArrayList<MDTO> List() {
		String sql = "select * from smember";
		ArrayList<MDTO> list = new ArrayList<MDTO>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MDTO DTO = new MDTO();
				DTO.setAdmin(rs.getInt("admin"));
				DTO.setId(rs.getString("id"));
				DTO.setPwd(rs.getString("pwd"));
				DTO.setPhone(rs.getString("phone"));
				DTO.setEmail(rs.getString("email"));
				DTO.setAddress(rs.getString("address"));
				list.add(DTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return list;
	}

	public void insert(MDTO DTO) {
		String sql = "insert into smember values(?, ?, ?, ?, ?, ?)";
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, DTO.getAdmin());
			pstmt.setString(2, DTO.getId());
			pstmt.setString(3, DTO.getPwd());
			pstmt.setString(4, DTO.getEmail());
			pstmt.setString(5, DTO.getPhone());
			pstmt.setString(6, DTO.getAddress());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("�떕�떎援ъ슂");
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
	}

	public int loginchk(String id, String pwd) {
		Connection conn = null;
		String sql = "select * from smember where id=?";
		int idfail = -1;
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = null;
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			try {
				if (rs.next()) {
					String sql1 = "select*from smember where id =? and pwd =?";
					pstmt = conn.prepareStatement(sql1);
					pstmt.setString(1, id);
					pstmt.setString(2, pwd);
					rs = pstmt.executeQuery();

					if (rs.next()) {
						return idfail = 2;
					} else {
						return idfail = 1;
					}
				} else {
					return idfail = 0;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(conn, pstmt, rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idfail;
	}

	public int idchk(String id) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		String sql = "select * from smember where id=?";
		int idfail = -1;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return idfail = 1;

			} else {
				return idfail = 0;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return idfail;
	}

	public void update(MDTO DTO) {
		String sql = "update smember set pwd=?, phone=?, email=?, address=? where id=?";
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, DTO.getPwd());
			pstmt.setString(2, DTO.getPhone());
			pstmt.setString(3, DTO.getEmail());
			pstmt.setString(4, DTO.getAddress());
			pstmt.setString(5, DTO.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
	}

	public void delete(String id) {
		String sql = "delete smember where id=?";
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
	}
}
