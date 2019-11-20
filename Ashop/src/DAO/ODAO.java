package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DTO.CDTO;
import DTO.ODTO;

public class ODAO extends link {

	private ODAO() {
	}

	private static ODAO instance = new ODAO();

	public static ODAO getInstance() {
		return instance;
	}

	public ArrayList<ODTO> OneList(String id, int pnum) {// REVIEW 사용 LIST
		String sql = "select * from sorder where id=? and pnum = ?";
		ArrayList<ODTO> list = new ArrayList<ODTO>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, pnum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ODTO DTO = new ODTO();
				DTO.setId(rs.getString("id"));
				DTO.setPnum(rs.getInt("pnum"));
				DTO.setTitle(rs.getString("title"));
				DTO.setPictureurl(rs.getString("pictureurl"));
				DTO.setPcnt(rs.getInt("pcnt"));
				DTO.setPrice(rs.getInt("price"));
				DTO.setSsize(rs.getString("ssize"));
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

	public int selectordernum() { // 주문내역 넘버 update를 위해서 기존 주문내역의 최종넘버를 받기
		String sql = "select buynum from (select buynum from sorder order by buynum desc) where rownum=1";
		ArrayList<ODTO> list = new ArrayList<ODTO>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int ordernum = -1;

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ordernum = rs.getInt("buynum");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return ordernum;
	}

	public ArrayList<ODTO> AdminOrderList() {// admin orderList(최신순 주문내역 받아오기)

		String sql = "select * from sorder order by buynum desc ";

		ArrayList<ODTO> list = new ArrayList<ODTO>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ODTO DTO = new ODTO();
				DTO.setBuynum(rs.getInt("buynum"));
				DTO.setName(rs.getString("name"));
				DTO.setId(rs.getString("id"));
				DTO.setPhone(rs.getString("phone"));
				DTO.setEmail(rs.getString("email"));
				DTO.setAddress(rs.getString("address"));
				DTO.setOrdernote(rs.getString("ordernote"));
				list.add(DTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return list;
	}

	public ArrayList<ODTO> List(String id) {// 사용자의 order확인 List
		// 모든 주문내역이 아닌 가장 최신의 주문내역 확인 list> 구매 후 확인 차 보여주는
		String sql = "select * from sorder where buynum in (select max(buynum) from sorder where id =?)";
		ArrayList<ODTO> list = new ArrayList<ODTO>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ODTO DTO = new ODTO();
				DTO.setBuynum(rs.getInt("buynum"));
				DTO.setName(rs.getString("name"));
				DTO.setId(rs.getString("id"));
				DTO.setPhone(rs.getString("phone"));
				DTO.setEmail(rs.getString("email"));
				DTO.setAddress(rs.getString("address"));
				DTO.setOrdernote(rs.getString("ordernote"));
				DTO.setTitle(rs.getString("title"));
				DTO.setSsize(rs.getString("ssize"));
				DTO.setColor(rs.getString("color"));
				DTO.setPrice(rs.getInt("price"));
				DTO.setPcnt(rs.getInt("pcnt"));
				list.add(DTO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return list;
	}

	public ArrayList<ODTO> AdminProductList(int pnum) {// Admin product 분석리스트 2(color, size)
		String sql1 = "select ssize type, sum(pcnt) sum from sorder where pnum=? group by ssize union select color type, sum(pcnt) sum from sorder where pnum=? group by color";
		
		ArrayList<ODTO> list = new ArrayList<ODTO>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ODTO DTO = null;

		try {
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, pnum);
			pstmt.setInt(2, pnum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				DTO = new ODTO();
				DTO.setPnum(pnum);
				DTO.setSsize(rs.getString("type"));
				DTO.setOrdernote(rs.getString("sum"));
				list.add(DTO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return list;
	}

	public int insert(ODTO DTO) {
		String sql = "insert into sorder values(?,?,?,?,?,?,?,?,?, ?, ?, ?, ?, ?)";

		Connection conn = getConnection();
		PreparedStatement pstmt = null;

		int re = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, DTO.getBuynum());
			pstmt.setInt(2, DTO.getPnum());
			pstmt.setString(3, DTO.getTitle());
			pstmt.setInt(4, DTO.getPrice());
			pstmt.setString(5, DTO.getPictureurl());
			pstmt.setString(6, DTO.getSsize());
			pstmt.setString(7, DTO.getColor());
			pstmt.setInt(8, DTO.getPcnt());
			pstmt.setString(9, DTO.getId());
			pstmt.setString(10, DTO.getName());
			pstmt.setString(11, DTO.getEmail());
			pstmt.setString(12, DTO.getPhone());
			pstmt.setString(13, DTO.getAddress());
			pstmt.setString(14, DTO.getOrdernote());

			pstmt.executeUpdate();

			re = 1;

		} catch (Exception e) {
			re = 0;
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		return re;
	}

	public ArrayList<ODTO> OneSelect(int ordernum) {
		// 구매수량 update를 위한 구문 1단계

		String sql = "select * from sorder where buynum=?";
		ArrayList<ODTO> onelist = new ArrayList<ODTO>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ordernum);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ODTO DTO = new ODTO();
				DTO.setPnum(rs.getInt("pnum"));
				DTO.setPcnt(rs.getInt("pcnt"));
				onelist.add(DTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return onelist;
	}

	public ArrayList<ODTO> AdminOrderDetailList(int ordernum) { // admin orderlist> detail
		// 주문번호를 눌렀을 때 자세한 내역이 보이게

		String sql = "select * from sorder where buynum=? ";
		ArrayList<ODTO> list = new ArrayList<ODTO>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ordernum);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ODTO DTO = new ODTO();
				DTO.setBuynum(rs.getInt("buynum"));
				DTO.setTitle(rs.getString("title"));
				DTO.setSsize(rs.getString("ssize"));
				DTO.setColor(rs.getString("color"));
				DTO.setPrice(rs.getInt("price"));
				DTO.setPcnt(rs.getInt("pcnt"));

				list.add(DTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return list;
	}

	public ArrayList<ODTO> MemberList(String id) { // MemberList> 리뷰를 위한 주문자확인

		String sql = "select * from sorder where id=? ";
		ArrayList<ODTO> list = new ArrayList<ODTO>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ODTO DTO = new ODTO();
				DTO.setBuynum(rs.getInt("buynum"));
				DTO.setPnum(rs.getInt("pnum"));
				DTO.setTitle(rs.getString("title"));
				DTO.setSsize(rs.getString("ssize"));
				DTO.setColor(rs.getString("color"));
				DTO.setPrice(rs.getInt("price"));
				DTO.setPcnt(rs.getInt("pcnt"));

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
