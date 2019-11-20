package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.PDTO;

public class PDAO extends link {

	private PDAO() {
	}

	private static PDAO instance = new PDAO();

	public static PDAO getInstance() {
		return instance;
	}

	public void insert(PDTO DTO) {
		String sql = "insert into sproduct values(p_seq.nextval,?, ?, ?, ?, ?, ?, ?, ?, ?, 0, 0)";
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, DTO.getTitle());
			pstmt.setString(2, DTO.getSort());
			pstmt.setInt(3, DTO.getPrice());
			pstmt.setString(4, DTO.getPictureurl());
			pstmt.setString(5, DTO.getDetail());
			pstmt.setString(6, DTO.getDetailpic());
			pstmt.setString(7, DTO.getSsize());
			pstmt.setString(8, DTO.getColor());
			pstmt.setInt(9, DTO.getPcnt());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
	}

	public ArrayList<PDTO> RandomList() {// MD픽! 랜덤리스트
		String sql = "select *from (select *from sproduct order by dbms_random.value) where rownum <= 4";

		ArrayList<PDTO> randomlist = new ArrayList<PDTO>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PDTO DTO = new PDTO();
				DTO.setPnum(rs.getInt("pnum"));
				DTO.setTitle(rs.getString("title"));
				DTO.setPictureurl(rs.getString("pictureurl"));
				DTO.setPrice(rs.getInt("price"));
				DTO.setPnum(rs.getInt("pnum"));
				randomlist.add(DTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return randomlist;
	}

	public ArrayList<PDTO> List(String sort) {// 카테고리별 상품리스트
		String sql = "select * from sproduct where sort = ?";
		ArrayList<PDTO> list = new ArrayList<PDTO>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sort);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PDTO DTO = new PDTO();
				DTO.setPnum(rs.getInt("pnum"));
				DTO.setPcnt(rs.getInt("pcnt"));
				DTO.setHistory(rs.getInt("history"));
				DTO.setTitle(rs.getString("title"));
				DTO.setSort(rs.getString("sort"));
				DTO.setPictureurl(rs.getString("pictureurl"));
				DTO.setPrice(rs.getInt("price"));
				DTO.setPnum(rs.getInt("pnum"));
				DTO.setColor(rs.getString("color"));
				list.add(DTO);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return list;
	}

	public ArrayList<PDTO> detail(String pnum) {// 상품 상세보기
		PreparedStatement pstmt = null;
		Connection conn = null;
		ArrayList<PDTO> list = new ArrayList<PDTO>();
		String sql = "select * from sproduct where pnum=?";
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(pnum));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PDTO DTO = new PDTO();
				DTO.setTitle(rs.getString("title"));
				DTO.setSort(rs.getString("sort"));
				DTO.setPictureurl(rs.getString("pictureurl"));
				DTO.setDetailpic(rs.getString("detailpic"));
				DTO.setDetail(rs.getString("detail"));
				DTO.setSsize(rs.getString("ssize"));
				DTO.setColor(rs.getString("color"));
				DTO.setPrice(rs.getInt("price"));
				DTO.setPnum(rs.getInt("pnum"));
				DTO.setPcnt(rs.getInt("pcnt"));
				DTO.setClick(rs.getInt("click"));
				DTO.setHistory(rs.getInt("history"));
				list.add(DTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return list;
	}

	public ArrayList<PDTO> NewestList() {// 최신 상품
		PreparedStatement pstmt = null;
		Connection conn = null;
		ArrayList<PDTO> newestlist = new ArrayList<PDTO>();

		String sql = "select *from (select *from sproduct order by pnum desc) where rownum<=8";
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				PDTO DTO = new PDTO();
				DTO.setTitle(rs.getString("title"));
				DTO.setSort(rs.getString("sort"));
				DTO.setPictureurl(rs.getString("pictureurl"));
				DTO.setPrice(rs.getInt("price"));
				DTO.setPnum(rs.getInt("pnum"));
				DTO.setClick(rs.getInt("click"));
				newestlist.add(DTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return newestlist;
	}

	public ArrayList<PDTO> BestList() {// Best 상품
		PreparedStatement pstmt = null;
		Connection conn = null;
		ArrayList<PDTO> BestList = new ArrayList<PDTO>();

		String sql = "select *from (select *from sproduct order by history desc) where rownum<=4";
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				PDTO DTO = new PDTO();
				DTO.setPictureurl(rs.getString("pictureurl"));
				DTO.setPnum(rs.getInt("pnum"));
				DTO.setHistory(rs.getInt("history"));
				BestList.add(DTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return BestList;
	}

	public void Update(int pnum, int renum) {// 조회수 update 2차관문 : 조회수 update하기
		// pnum: 상품 num, renum : update 할 조회수 num

		String sql = "update sproduct set click=? where pnum=?";
		Connection conn = getConnection();
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, renum);
			pstmt.setInt(2, pnum);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
	}

	public int SelectClick(int pnum) {// 조회수 update 1차관문 : 조회수받아오기
		int sendnum = -1;

		PreparedStatement pstmt = null;
		Connection conn = null;

		String sql = "select click from sproduct where pnum=?";
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pnum);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				sendnum = rs.getInt("click");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return sendnum;
	}

	public void HistoryUpdate(int pnum, int renum) {
		// 구매수 update 3차관문 : 구매수 update하기
		// pnum: 상품 num, renum : update 할 조회수 num

		String sql = "update sproduct set history=? where pnum=?";
		Connection conn = getConnection();
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, renum);
			pstmt.setInt(2, pnum);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
	}

	public int SelectHistory(int pnum) {
		// 구매수 update 2차관문 :상품번호로 기존의 저장된 구매수받아오기
		int sendnum = -1;

		PreparedStatement pstmt = null;
		Connection conn = null;

		String sql = "select history from sproduct where pnum=?";
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pnum);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				sendnum = rs.getInt("history");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return sendnum;
	}

	public ArrayList<PDTO> AdminProduct() {// Admin> 상품 수량 파악
		PreparedStatement pstmt = null;
		Connection conn = null;
		ArrayList<PDTO> list = new ArrayList<PDTO>();
		String sql = "select *from sproduct";
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PDTO DTO = new PDTO();
				DTO.setTitle(rs.getString("title"));
				DTO.setSort(rs.getString("sort"));
				DTO.setPictureurl(rs.getString("pictureurl"));
				DTO.setPnum(rs.getInt("pnum"));
				DTO.setPcnt(rs.getInt("pcnt"));
				DTO.setClick(rs.getInt("click"));
				DTO.setHistory(rs.getInt("history"));
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
