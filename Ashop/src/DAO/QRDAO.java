package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.QRDTO;

public class QRDAO extends link {

	private QRDAO() {
	}

	private static QRDAO instance = new QRDAO();

	public static QRDAO getInstance() {
		return instance;
	}

	public ArrayList<QRDTO> List(int pnum, int qnum) {
		String sql = "select * from qnar where qnum = ? and pnum=?";
		ArrayList<QRDTO> list = new ArrayList<QRDTO>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qnum);
			pstmt.setInt(2, pnum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				QRDTO DTO = new QRDTO();
				DTO.setPnum(rs.getInt("pnum"));
				DTO.setQnum(rs.getInt("qnum"));
				DTO.setQrnum(rs.getInt("qrnum"));
				DTO.setContent(rs.getString("content"));
				DTO.setQrdate(rs.getString("qrdate"));
				list.add(DTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return list;
	}

	public void insert(QRDTO DTO) {
		String sql = "insert into qnar values(qnar_seq.nextval, ?, ?, ?,?)";
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, DTO.getQnum());
			pstmt.setInt(2, DTO.getPnum());
			pstmt.setString(3, DTO.getQrdate());
			pstmt.setString(4, DTO.getContent());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
	}

//	public int loginchk(String id, String pwd) {
//		Connection conn = null;
//		String sql = "select * from qna where id=?";
//		int idfail = -1;
//		ResultSet rs = null;
//		try {
//			PreparedStatement pstmt = null;
//			conn = getConnection();
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, id);
//			rs = pstmt.executeQuery();
//			try {
//				if (rs.next()) {
//					String sql1 = "select*from qna where id =? and pwd =?";
//					pstmt = conn.prepareStatement(sql1);
//					pstmt.setString(1, id);
//					pstmt.setString(2, pwd);
//					rs = pstmt.executeQuery();
//
//					if (rs.next()) {
//						return idfail = 2;
//					} else {
//						return idfail = 1;
//					}
//				} else {
//					return idfail = 0;
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				close(conn, pstmt, rs);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return idfail;
//	}
//
//	public int idchk(String id) {
//		PreparedStatement pstmt = null;
//		Connection conn = null;
//		String sql = "select * from qna where id=?";
//		int idfail = -1;
//		ResultSet rs = null;
//		try {
//			conn = getConnection();
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, id);
//			rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//				return idfail = 1;
//
//			} else {
//				return idfail = 0;
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			close(conn, pstmt, rs);
//		}
//		return idfail;
//	}
//
//	public void update(QDTO DTO) {
//		String sql = "update qna set pwd=?, phone=?, email=?, address=? where id=?";
//		Connection conn = getConnection();
//		PreparedStatement pstmt = null;
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, DTO.getPwd());
//			pstmt.setString(2, DTO.getPhone());
//			pstmt.setString(3, DTO.getEmail());
//			pstmt.setString(4, DTO.getAddress());
//			pstmt.setString(5, DTO.getId());
//			pstmt.executeUpdate();// 占쎈쐩占쎈닑占쎈뉴占쎈닱�뜝占� �뜝�럥堉꾢뜝�럥六у뜝�럥由썲뜝�럥堉�.
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			close(conn, pstmt);
//		}
//	}
//
//	public void delete(String id) {
//		String sql = "delete qna where id=?";
//		Connection conn = getConnection();
//		PreparedStatement pstmt = null;
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, id);
//			pstmt.executeUpdate();// 占쎈쐩占쎈닑占쎈뉴占쎈닱�뜝占� �뜝�럥堉꾢뜝�럥六�
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			close(conn, pstmt);
//		}
//	}
}
