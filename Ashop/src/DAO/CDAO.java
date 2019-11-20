package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DTO.CDTO;

public class CDAO extends link {

	private CDAO() {
	}

	private static CDAO instance = new CDAO();

	public static CDAO getInstance() {
		return instance;
	}

	public void delete(String id, int num, int choice) {// 구매로 넘어간 후 장바구니를 삭제 메소드
		String sql = "delete from smoney where id=? and num=? and choice=?";
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, num);
			pstmt.setInt(3, choice);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
	}

	public void cartupdate(String id, int pcnt, int pnum, int num) {// 장바구니 수량 update
		// 변경 할 기준이 되는 장바구니 번호, 상품번호, id와 변경 할 수량 필요
		String sql = "update smoney set pcnt=? where id=? and pnum=? and num=?";
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pcnt);
			pstmt.setString(2, id);
			pstmt.setInt(3, pnum);
			pstmt.setInt(4, num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
	}

	public void choicereupdate(String id) {
		// 구매 실패제품 다시 1-> 0으로 choice 변경

		String sql = "update smoney set choice=? where id=?";
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 0);
			pstmt.setString(2, id);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
	}

	public void choiceupdate(String id, String[] choicelist) {// checkbox 구매 할 제품 선택 > choice 0>1로 update
		String sql = "update smoney set choice=? where id=? and num=?";
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		try {

			for (int i = 0; i < choicelist.length; i++) {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, 1);
				pstmt.setString(2, id);
				pstmt.setInt(3, Integer.parseInt(choicelist[i]));
				pstmt.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
	}

	public ArrayList<CDTO> BuyList(String id, int choice) {
		// CHECKOUT info에 보이는 주문 List > choice가 1인것(장바구니중에서 구매할 것만)

		String sql = "select * from smoney where id=? and choice=?";
		ArrayList<CDTO> list = new ArrayList<CDTO>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, choice);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CDTO DTO = new CDTO();
				DTO.setId(rs.getString("id"));
				DTO.setNum(rs.getInt("num"));
				DTO.setPnum(rs.getInt("pnum"));
				DTO.setTitle(rs.getString("title"));
				DTO.setPictureurl(rs.getString("pictureurl"));
				DTO.setPcnt(rs.getInt("pcnt"));
				DTO.setPrice(rs.getInt("price"));
				DTO.setSsize(rs.getString("ssize"));
				DTO.setColor(rs.getString("color"));
				DTO.setChoice(rs.getInt("choice"));
				list.add(DTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return list;
	}

	public ArrayList<CDTO> List(String id) {// 전체 장바구니 LIST
		// chk를 지웠으니 choice check여부 상관없이 모든 장바구니 List를 받아오기
		String sql = "select * from smoney where id=? order by num desc";
		ArrayList<CDTO> list = new ArrayList<CDTO>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CDTO DTO = new CDTO();
				DTO.setNum(rs.getInt("num"));
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

	public void insert(CDTO DTO) {

		String sql = "insert into smoney values(c_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, DTO.getPnum());
			pstmt.setString(2, DTO.getId());
			pstmt.setString(3, DTO.getTitle());
			pstmt.setInt(4, DTO.getPrice());
			pstmt.setString(5, DTO.getPictureurl());
			pstmt.setString(6, DTO.getSsize());
			pstmt.setString(7, DTO.getColor());
			pstmt.setInt(8, DTO.getPcnt());
			pstmt.setInt(9, DTO.getChoice());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
	}

}
