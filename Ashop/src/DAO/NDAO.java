package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DTO.NDTO;
import DTO.NDTO;

public class NDAO extends link {

	private NDAO() {
	}

	private static NDAO instance = new NDAO();

	public static NDAO getInstance() {
		return instance;
	}

	public void insert(NDTO DTO) {
		String sql = null;

		sql = "insert into notice values(notice_seq.nextval, ?, ?, ?, ?, ?)";

		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, DTO.getId());
			pstmt.setString(2, DTO.getNtype());
			pstmt.setString(3, DTO.getNtitle());
			pstmt.setString(4, DTO.getNcontent());
			pstmt.setString(5, DTO.getNdate());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
	}

	public ArrayList<NDTO> List() {
		String sql = "select * from notice";

		ArrayList<NDTO> list = new ArrayList<NDTO>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				NDTO DTO = new NDTO();
				DTO.setNnum(rs.getInt("nnum"));
				DTO.setId(rs.getString("id"));
				DTO.setNtype(rs.getString("ntype"));
				DTO.setNtitle(rs.getString("ntitle"));
				DTO.setNdate(rs.getString("ndate"));
				list.add(DTO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return list;
	}
	
	public ArrayList<NDTO> DetailList(int num) {
		String sql = "select * from notice where nnum=?";

		ArrayList<NDTO> list = new ArrayList<NDTO>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				NDTO DTO = new NDTO();
				DTO.setNnum(rs.getInt("nnum"));
				DTO.setId(rs.getString("id"));
				DTO.setNtitle(rs.getString("ntitle"));
				DTO.setNcontent(rs.getString("ncontent"));
				DTO.setNdate(rs.getString("ndate"));
				list.add(DTO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return list;
	}

}
