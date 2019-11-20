package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.RDTO;

public class RDAO extends link {

	private RDAO() {
	}

	private static RDAO instance = new RDAO();

	public static RDAO getInstance() {
		return instance;
	}

	public ArrayList<RDTO> List(int pnum) {
		String sql = "select * from review where pnum =?";
		ArrayList<RDTO> list = new ArrayList<RDTO>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pnum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				RDTO DTO = new RDTO();
				DTO.setColor(rs.getString("color"));
				DTO.setContent(rs.getString("content"));
				DTO.setHeight(rs.getInt("height"));
				DTO.setId(rs.getString("id"));
				DTO.setPictureurl(rs.getString("pictureurl"));
				DTO.setPnum(rs.getInt("pnum"));
				DTO.setRdate(rs.getString("rdate"));
				DTO.setRnum(rs.getInt("rnum"));
				DTO.setSsize(rs.getString("ssize"));
				DTO.setTitle(rs.getString("title"));
				DTO.setWeight(rs.getInt("Weight"));
				list.add(DTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return list;
	}

	public void insert(RDTO DTO) {
		String sql = "insert into review values(rnum_seq.nextval, ?,?,?,?,?,?,?,?,?,?)";
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, DTO.getPnum());
			pstmt.setString(2, DTO.getId());
			pstmt.setString(3, DTO.getTitle());
			pstmt.setString(4, DTO.getRdate());
			pstmt.setString(5, DTO.getSsize());
			pstmt.setString(6, DTO.getColor());
			pstmt.setInt(7, DTO.getHeight());
			pstmt.setInt(8, DTO.getWeight());
			pstmt.setString(9, DTO.getContent());
			pstmt.setString(10, DTO.getPictureurl());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
	}

	public ArrayList<RDTO> SizeList(int height, int weight) {
		String sql = "select * from review where height =? and weight=?";
		ArrayList<RDTO> list = new ArrayList<RDTO>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, height);
			pstmt.setInt(2, weight);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				RDTO DTO = new RDTO();
				DTO.setColor(rs.getString("color"));
				DTO.setHeight(rs.getInt("height"));
				DTO.setSsize(rs.getString("ssize"));
				DTO.setWeight(rs.getInt("Weight"));
				list.add(DTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return list;
	}

//	public int loginchk(String id, String pwd) {
//		Connection conn = null;
//		String sql = "select * from review where id=?";
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
//					String sql1 = "select*from review where id =? and pwd =?";
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
//		String sql = "select * from review where id=?";
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
//		String sql = "update review set pwd=?, phone=?, email=?, address=? where id=?";
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
//		String sql = "delete review where id=?";
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
