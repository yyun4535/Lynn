package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.QDTO;

public class QDAO extends link {

	private QDAO() {
	}

	private static QDAO instance = new QDAO();

	public static QDAO getInstance() {
		return instance;
	}

	public int pwdchk(String pwd, int qnum) {
		Connection conn = null;
		String sql = "select * from qna where pwd=? and qnum=?";
		int pwdfail = -1;
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = null;
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pwd);
			pstmt.setInt(2, qnum);
			rs = pstmt.executeQuery();
			try {
				if (rs.next()) {
					return pwdfail = 1;
				} else {
					return pwdfail = 0;
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(conn, pstmt, rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pwdfail;
	}

	public ArrayList<QDTO> List(int pnum) {
		String sql = "select * from qna where pnum =?";
		ArrayList<QDTO> list = new ArrayList<QDTO>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pnum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				QDTO DTO = new QDTO();
				DTO.setId(rs.getString("id"));
				DTO.setContent(rs.getString("content"));
				DTO.setQdate(rs.getString("qdate"));
				DTO.setParent(rs.getInt("parent"));
				DTO.setTitle(rs.getString("title"));
				DTO.setQnum(rs.getInt("qnum"));
				DTO.setPwd(rs.getString("pwd"));
				DTO.setPum(pnum);
				list.add(DTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return list;
	}

	public ArrayList<QDTO> QList(int pnum, int qnum) {
		String sql = "select * from qna where pnum =? and qnum = ?";
		ArrayList<QDTO> list = new ArrayList<QDTO>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pnum);
			pstmt.setInt(2, qnum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				QDTO DTO = new QDTO();
				DTO.setId(rs.getString("id"));
				DTO.setContent(rs.getString("content"));
				DTO.setQdate(rs.getString("qdate"));
				DTO.setParent(rs.getInt("parent"));
				DTO.setTitle(rs.getString("title"));
				DTO.setQnum(qnum);
				DTO.setPwd(rs.getString("pwd"));
				DTO.setPum(pnum);
				list.add(DTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return list;
	}

	public void insert(QDTO DTO) {
		String sql = "insert into qna values(qna_seq.nextval, ?, ?, ?, ?,?,parent_seq.nextval,?)";
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, DTO.getPum());
			pstmt.setString(2, DTO.getId());
			pstmt.setString(3, DTO.getPwd());
			pstmt.setString(4, DTO.getQdate());
			pstmt.setString(5, DTO.getTitle());
			pstmt.setString(6, DTO.getContent());
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
	public void update(QDTO DTO, String id, int pnum, int qnum) {
		String sql = "update qna set title=?, content=? where id=? and pnum=? and qnum=?";
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, DTO.getTitle());
			pstmt.setString(2, DTO.getContent());
			pstmt.setString(3, id);
			pstmt.setInt(4, pnum);
			pstmt.setInt(5, qnum);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
	}

	public void delete(String id, int pnum, int qnum) {
		String sql = "delete qna where id=? and pnum = ?  and qnum =?";
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, pnum);
			pstmt.setInt(3, qnum);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
	}
}
