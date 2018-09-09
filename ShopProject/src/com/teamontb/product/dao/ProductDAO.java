package com.teamontb.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.teamontb.product.dto.SalesStatusVO;
import com.teamontb.product.dto.ProductVO;
import com.teamontb.product.dto.ReviewVO;

import util.DBManagement;

public class ProductDAO {
	private static ProductDAO instance = new ProductDAO();

	private ProductDAO() {
	}

	public static ProductDAO getInstance() {
		if (instance == null)
			instance = new ProductDAO();
		return instance;
	}


	public List<ProductVO> selectAll() {
		Connection con = DBManagement.getConnection();
		String selectQuery = "SELECT * FROM project.product";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductVO> list = null;
		try {
			pstmt = con.prepareStatement(selectQuery);
			rs = pstmt.executeQuery();
			list = new ArrayList<ProductVO>();
			while (rs.next()) {
				list.add(new ProductVO(rs.getInt("product_no"), rs.getInt("product_category"),
						rs.getString("product_name"), rs.getInt("product_cost"), rs.getInt("product_price"),
						rs.getString("product_information"), rs.getString("product_main_images"),rs.getString("product_subimage"),
						rs.getString("product_color"), rs.getInt("product_weight"), rs.getString("product_size"),
						rs.getString("product_manufacturer"), rs.getInt("product_power_consumption"),
						rs.getInt("product_rated_voltage")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManagement.close(rs, pstmt, con);
		}
		return list;
	}

	public ProductVO selectOne(String product_no) {
		Connection con = DBManagement.getConnection();
		String selectQuery = "SELECT * FROM project.product where product_no = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductVO vo = null;
		try {
			pstmt = con.prepareStatement(selectQuery);
			pstmt.setInt(1, Integer.parseInt(product_no));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new ProductVO(rs.getInt("product_no"), rs.getInt("product_category"), rs.getString("product_name"),
						rs.getInt("product_cost"), rs.getInt("product_price"), rs.getString("product_information"),
						rs.getString("product_main_images"),rs.getString("product_subimage"), rs.getString("product_color"), rs.getInt("product_weight"),
						rs.getString("product_size"), rs.getString("product_manufacturer"),
						rs.getInt("product_power_consumption"), rs.getInt("product_rated_voltage"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManagement.close(rs, pstmt, con);
		}
		return vo;
	}

	public List<SalesStatusVO> getSalesStatusOrderList(String memberEmail) {
		List<SalesStatusVO> list = new ArrayList<SalesStatusVO>();
		Connection conn = DBManagement.getConnection();
		String selectQuery = "select ss.*, p.PRODUCT_name from sales_status ss, product p where ss.PRODUCT_NO = p.PRODUCT_NO and status not in ('교환 요청', '환불 요청', '환불 완료', '교환 완료') and ss.member_email = ? order by ss.sell_date desc";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(selectQuery);
			pstmt.setString(1, memberEmail);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				SalesStatusVO ssVO = new SalesStatusVO();
				ssVO.setPrice(rs.getInt("price"));
				ssVO.setDiscount(rs.getInt("discount"));
				ssVO.setDiscountReason(rs.getString("discount_reason"));
				ssVO.setQuantity(rs.getInt("quantity"));
				ssVO.setSellDate(rs.getDate("sell_date"));
				ssVO.setStatus(rs.getString("status"));
				ssVO.setStatusReason(rs.getString("status_reason"));
				ssVO.setProductNo(rs.getInt("product_no"));
				ssVO.setProductName(rs.getString("product_name"));
				list.add(ssVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManagement.close(rs, pstmt, conn);
		}
		return list;
	}

	public List<SalesStatusVO> getProductStatusList(String memberEmail) {
		List<SalesStatusVO> list = new ArrayList<SalesStatusVO>();
		Connection conn = DBManagement.getConnection();
		String selectQuery = "select ss.*, p.PRODUCT_name from sales_status ss, product p where ss.PRODUCT_NO = p.PRODUCT_NO and ss.member_email = ? and date_add(sell_date, interval + 7 day) >= now() order by ss.sell_date desc";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(selectQuery);
			pstmt.setString(1, memberEmail);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				SalesStatusVO ssVO = new SalesStatusVO();
				ssVO.setPrice(rs.getInt("price"));
				ssVO.setDiscount(rs.getInt("discount"));
				ssVO.setDiscountReason(rs.getString("discount_reason"));
				ssVO.setQuantity(rs.getInt("quantity"));
				ssVO.setSellDate(rs.getDate("sell_date"));
				ssVO.setStatus(rs.getString("status"));
				ssVO.setStatusReason(rs.getString("status_reason"));
				ssVO.setProductNo(rs.getInt("product_no"));
				ssVO.setProductName(rs.getString("product_name"));
				ssVO.setOrderno(rs.getString("order_no"));
				list.add(ssVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManagement.close(rs, pstmt, conn);
		}
		return list;
	}
	
	public List<SalesStatusVO> getSalesRepriceList(String memberEmail) {
		List<SalesStatusVO> list = new ArrayList<SalesStatusVO>();
		Connection conn = DBManagement.getConnection();
		String selectQuery = "select ss.*, pb.PRODUCT_name from sales_status ss, product pb where ss.PRODUCT_NO = pb.PRODUCT_NO and status in('환불 완료') and ss.member_email = ? order by ss.sell_date desc";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(selectQuery);
			pstmt.setString(1, memberEmail);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				SalesStatusVO ssVO = new SalesStatusVO();
				ssVO.setPrice(rs.getInt("price"));
				ssVO.setDiscount(rs.getInt("discount"));
				ssVO.setDiscountReason(rs.getString("discount_reason"));
				ssVO.setQuantity(rs.getInt("quantity"));
				ssVO.setSellDate(rs.getDate("sell_date"));
				ssVO.setStatus(rs.getString("status"));
				ssVO.setStatusReason(rs.getString("status_reason"));
				ssVO.setProductNo(rs.getInt("product_no"));
				ssVO.setProductName(rs.getString("product_name"));
				list.add(ssVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManagement.close(rs, pstmt, conn);
		}
		return list;
	}
	public List<ProductVO> selectPboard(int boardNo) {
		Connection con = DBManagement.getConnection();
		String selectQuery = "select * from product p, bridge_p_pb bpb where p.product_no = bpb.PRODUCT_NO and bpb.product_board_no = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductVO> list = null;
		try {
			pstmt = con.prepareStatement(selectQuery);
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			list = new ArrayList<ProductVO>();
			while (rs.next()) {
				list.add(new ProductVO(rs.getInt("product_no"), rs.getInt("product_category"),
						rs.getString("product_name"), rs.getInt("product_cost"), rs.getInt("product_price"),
						rs.getString("product_information"), rs.getString("product_main_images"),rs.getString("product_subimage"),
						rs.getString("product_color"), rs.getInt("product_weight"), rs.getString("product_size"),
						rs.getString("product_manufacturer"), rs.getInt("product_power_consumption"),
						rs.getInt("product_rated_voltage")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManagement.close(rs, pstmt, con);
		}
		return list;
	}

	public void AddReview(ReviewVO vo) {
		Connection con = DBManagement.getConnection();
		String selectQuery = "insert into review(product_board_no,member_email,review_content,review_images,review_star) values(?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(selectQuery);
			pstmt.setInt(1, vo.getProductBoardNo());
			pstmt.setString(2, vo.getMemberEmail());
			pstmt.setString(3, vo.getReview_content());
			pstmt.setString(4, vo.getReview_images());
			pstmt.setInt(5, vo.getReview_star());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManagement.close(pstmt, con);
		}
	
	}
	
	public List<ReviewVO> selectReview(int pNo){
		Connection con = DBManagement.getConnection();
		String selectQuery = "select * from review where product_board_no = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ReviewVO> list = null;
		try {
			pstmt = con.prepareStatement(selectQuery);
			pstmt.setInt(1, pNo);
			rs = pstmt.executeQuery();
			list = new ArrayList<ReviewVO>();
			while (rs.next()) {
				list.add(new ReviewVO(rs.getInt("product_board_no"), rs.getString("member_email"),rs.getString("review_content")
						,rs.getTimestamp("review_date"), rs.getInt("review_star")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManagement.close(rs, pstmt, con);
		}
		return list;
	}
	
	public int countReview(int pNo) {
		int result = 0;
		Connection con = DBManagement.getConnection();
		String selectQuery = "select count(*) from review where product_board_no = ?;";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement(selectQuery);
			pstmt.setInt(1, pNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManagement.close(rs, pstmt, con);
		}
		
		return result;
	}
}
