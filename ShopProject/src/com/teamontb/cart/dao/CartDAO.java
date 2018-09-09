package com.teamontb.cart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.teamontb.cart.dto.CartVO;

import util.DBManagement;

public class CartDAO {
	private static CartDAO instance = new CartDAO();

	private CartDAO() {
	}

	public static CartDAO getInstance() {
		if (instance == null) {
			return new CartDAO();
		}
		return instance;
	}

	public List<CartVO> selectBasket(String userid) {
		String query = "select pb.PRODUCT_BOARD_TITLE,c.QUANTITY,bppb.CURRENT_PRICE, p.PRODUCT_SIZE,p.PRODUCT_COLOR, p.PRODUCT_WEIGHT,c.PRODUCT_NO,p.PRODUCT_SUBIMAGE,pb.PRODUCT_BOARD_NO,bppb.PRODUCT_STOCK"
				+ " from product p inner join bridge_p_pb bppb on p.PRODUCT_NO = bppb.PRODUCT_NO inner join cart c on c.product_no = p.product_no inner join product_board pb on pb.PRODUCT_BOARD_NO = bppb.PRODUCT_BOARD_NO"
				+ " where c.MEMBER_EMAIL = ? ;";
		List<CartVO> list = new ArrayList<CartVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DBManagement.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new CartVO(rs.getString("PRODUCT_BOARD_TITLE"), rs.getInt("QUANTITY"),
						rs.getInt("CURRENT_PRICE"), rs.getString("PRODUCT_SIZE"), rs.getDouble("PRODUCT_WEIGHT"),
						rs.getString("PRODUCT_COLOR"), rs.getInt("product_no"),rs.getString("PRODUCT_SUBIMAGE"),rs.getInt("PRODUCT_BOARD_NO"),rs.getInt("PRODUCT_STOCK")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManagement.close(rs, pstmt, con);
		}
		return list;
	}

	public void updateBasket(String memberemail, String productboardno, String count) {
		String query = "update cart set quantity =  ? where member_email = ? and product_no = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBManagement.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, count);
			pstmt.setString(2, memberemail);
			pstmt.setString(3, productboardno);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManagement.close(pstmt, con);
		}
	}

	public void deleteBasket(String memberemail, String productno) {
		String query = "delete from cart where member_email = ? and product_no = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBManagement.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberemail);
			pstmt.setString(2, productno);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManagement.close(pstmt, con);
		}
	}

	public void alldeleteBasket(String memberemail) {
		String query = "delete from cart where member_email = ? ";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBManagement.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberemail);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManagement.close(pstmt, con);
		}
	}
	public void insertcart(String userid , String productboardno, String quantity) {
		String query = "insert into cart values(?,?,?) ";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBManagement.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userid);
			pstmt.setString(2, productboardno);
			pstmt.setString(3, quantity);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManagement.close(pstmt, con);
		}
	}
	public boolean selectcart(String userid, String productboardno){
		boolean result = false;
		String query="select * from cart where MEMBER_EMAIL = ? and PRODUCT_NO = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBManagement.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userid);
			pstmt.setString(2,productboardno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = true;
				return result; //제품이 있으면 false
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManagement.close(rs, pstmt, con);
		}
		return result; //제품이 없으면 true
		
	}
}
