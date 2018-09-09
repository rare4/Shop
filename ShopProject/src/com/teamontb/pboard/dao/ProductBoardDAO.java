package com.teamontb.pboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.teamontb.pboard.dto.ProductBoardVO;
import com.teamontb.product.dao.ProductDAO;
import com.teamontb.product.dto.ProductVO;
import com.teamontb.product.dto.ReviewVO;
import com.teamontb.product.dto.SalesStatusVO;

import util.DBManagement;

public class ProductBoardDAO {
	private static ProductBoardDAO instance = new ProductBoardDAO();

	private ProductBoardDAO() {
	}

	public static ProductBoardDAO getInstance() {
		if (instance == null)
			instance = new ProductBoardDAO();
		return instance;
	}

	public List<ProductBoardVO> selectAll() {
		Connection con = DBManagement.getConnection();
		String selectQuery = "select pb.PRODUCT_BOARD_NO,pb.PRODUCT_BOARD_TITLE,pb.REGISTERED_DATE,bpb.CURRENT_PRICE,sum(bpb.PRODUCT_STOCK) AS PRODUCT_STOCK from product_board pb inner join bridge_p_pb bpb on pb.PRODUCT_BOARD_NO = bpb.PRODUCT_BOARD_NO group by PRODUCT_BOARD_NO;";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductBoardVO> pbvolist = null;
		List<ProductVO> pvoList = new ArrayList<ProductVO>();
		try {
			pstmt = con.prepareStatement(selectQuery);
			rs = pstmt.executeQuery();
			pbvolist = new ArrayList<ProductBoardVO>();
			while (rs.next()) {
				ProductDAO pdao = ProductDAO.getInstance();
				pvoList = pdao.selectPboard(rs.getInt("pb.PRODUCT_BOARD_NO"));
				pbvolist.add(new ProductBoardVO(rs.getInt("pb.PRODUCT_BOARD_NO"), rs.getString("pb.PRODUCT_BOARD_TITLE"),
						rs.getInt("bpb.CURRENT_PRICE"), rs.getInt("PRODUCT_STOCK"),
						rs.getDate("pb.REGISTERED_DATE"), pvoList));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManagement.close(rs, pstmt, con);

		}
		return pbvolist;
	}

	public ProductBoardVO selectOne(String product_no) {
		Connection con = DBManagement.getConnection();
		String selectQuery = "select * from product_board where product_board.PRODUCT_BOARD_NO=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductBoardVO pbvo = null;
		List<ProductVO> pvolist = null;
		try {
			pstmt = con.prepareStatement(selectQuery);
			pstmt.setInt(1, Integer.parseInt(product_no));
			rs = pstmt.executeQuery();
			pvolist = new ArrayList<ProductVO>();
			while (rs.next()) {
				ProductDAO pdao = ProductDAO.getInstance();
				pvolist = pdao.selectPboard(Integer.parseInt(product_no));
				pbvo.setCurrentPrice(rs.getInt("product_board.CURRENT_PRICE"));
				pbvo.setProductBoardNo(rs.getInt("product_board.PRODUCT_BOARD_NO"));
				pbvo.setProductBoardTitle(rs.getString("product_board.PRODUCT_BOARD_TITLE"));
				pbvo.setRegisteredDate(rs.getDate("product_board.REGISTERED_DATE"));
				pbvo.setRegisteredStock(rs.getInt("product_board.REGISTERED_STOCK"));
				pbvo.setProductList(pvolist);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManagement.close(rs, pstmt, con);
		}
		return pbvo;
	}

	public int totProductList(int category, String search) {
		Connection con = DBManagement.getConnection();
		String selectQuery = "";
		int result = 0;
		if (category == 0) {
			selectQuery = "select count(distinct product_board.product_board_no) as cnt from product,bridge_p_pb,product_board where product.PRODUCT_NO=bridge_p_pb.PRODUCT_NO and product_board.PRODUCT_BOARD_NO=bridge_p_pb.PRODUCT_BOARD_NO and product_board.product_board_title like '%"
					+ search + "%'";
		} else if (search == null) {
			selectQuery = "select count(distinct product_board.product_board_no) as cnt from product,bridge_p_pb,product_board where product.PRODUCT_NO=bridge_p_pb.PRODUCT_NO and product_board.PRODUCT_BOARD_NO=bridge_p_pb.PRODUCT_BOARD_NO and product.PRODUCT_CATEGORY= ?";
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(selectQuery);
			if (search == null) {
				pstmt.setInt(1, category);
			}
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManagement.close(rs, pstmt, con);

		}
		return result;
	}

	public List<ProductBoardVO> selectListByCategory(int category, int pageNo) {
		Connection con = DBManagement.getConnection();
		String selectQuery = "select * from product,bridge_p_pb,product_board where product.PRODUCT_NO=bridge_p_pb.PRODUCT_NO and product_board.PRODUCT_BOARD_NO=bridge_p_pb.PRODUCT_BOARD_NO and product.PRODUCT_CATEGORY=? group by product_board.PRODUCT_BOARD_NO limit ?, 10";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductBoardVO> pbvolist = null;
		List<ProductVO> pvoList = null;
		try {
			pstmt = con.prepareStatement(selectQuery);
			pstmt.setInt(1, category);
			pstmt.setInt(2, (pageNo / 10));
			rs = pstmt.executeQuery();
			pbvolist = new ArrayList<ProductBoardVO>();

			while (rs.next()) {
				ProductDAO pdao = ProductDAO.getInstance();
				pvoList = new ArrayList<ProductVO>();
				pvoList = pdao.selectPboard(rs.getInt("product_board.PRODUCT_BOARD_NO"));
				System.out.println(pvoList.get(0).toString());
				pbvolist.add(new ProductBoardVO(rs.getInt("product_board.PRODUCT_BOARD_NO"), rs.getString("product_board.PRODUCT_BOARD_TITLE"),
						rs.getInt("bridge_p_pb.CURRENT_PRICE"), rs.getInt("bridge_p_pb.PRODUCT_STOCK"),
						rs.getDate("product_board.REGISTERED_DATE"), pvoList));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManagement.close(rs, pstmt, con);

		}
		return pbvolist;
	}

	public List<ProductBoardVO> autoComplete() {
		List<ProductBoardVO> productBoardList = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectQuery = "select product_board_no, product_board_title from product_board";
		try {
			productBoardList = new ArrayList<ProductBoardVO>();
			conn = DBManagement.getConnection();
			pstmt = conn.prepareStatement(selectQuery);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductBoardVO pbvo = new ProductBoardVO();
				pbvo.setProductBoardNo(rs.getInt("product_board_no"));
				pbvo.setProductBoardTitle(rs.getString("product_board_title"));
				productBoardList.add(pbvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManagement.close(rs, pstmt, conn);
		}
		return productBoardList;
	}

	// 상세페이지 용

	public List<ProductBoardVO> selectListByProductBoardNo(int product_board_no) {
		List<ProductBoardVO> list = new ArrayList<ProductBoardVO>();
		Connection conn = DBManagement.getConnection();
		String selectQuery = "select p.PRODUCT_PRICE, p.product_main_images,p.PRODUCT_COLOR, p.PRODUCT_WEIGHT,p.PRODUCT_SIZE,p.PRODUCT_MANUFACTURER,p.PRODUCT_POWER_CONSUMPTION,p.PRODUCT_RATED_VOLTAGE,p.PRODUCT_INFORMATION,p.product_category";
		selectQuery += ",pb.product_stock,pdb.REGISTERED_DATE,pdb.PRODUCT_BOARD_TITLE,pb.CURRENT_PRICE,p.PRODUCT_NO,p.PRODUCT_SUBIMAGE,pdb.PRODUCT_BOARD_NO";
		selectQuery += " from product p join bridge_p_pb pb on p.PRODUCT_NO = pb.PRODUCT_NO";
		selectQuery += " join product_board pdb on pdb.PRODUCT_BOARD_NO = pb.PRODUCT_BOARD_NO ";
		selectQuery += "where pdb.PRODUCT_BOARD_NO = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(selectQuery);
			pstmt.setInt(1, product_board_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductBoardVO pbvo = new ProductBoardVO();
				ProductVO pvo = new ProductVO();
				pbvo.setRegisteredStock(rs.getInt("product_stock"));
				pbvo.setRegisteredDate(rs.getDate("REGISTERED_DATE"));
				pbvo.setProductBoardTitle(rs.getString("PRODUCT_BOARD_TITLE"));
				pbvo.setProductBoardNo(rs.getInt("PRODUCT_BOARD_NO"));
				pbvo.setCurrentPrice(rs.getInt("CURRENT_PRICE"));
				pvo.setProductSubImage(rs.getString("PRODUCT_SUBIMAGE"));
				pvo.setProductPrice(rs.getInt("PRODUCT_PRICE"));
				pvo.setProductMainImages(rs.getString("product_main_images"));
				pvo.setProductColor(rs.getString("PRODUCT_COLOR"));
				pvo.setProductWeight(rs.getInt("PRODUCT_WEIGHT"));
				pvo.setProductSize(rs.getString("PRODUCT_SIZE"));
				pvo.setProductManufacturer(rs.getString("PRODUCT_MANUFACTURER"));
				pvo.setProductPowerConsumption(rs.getInt("PRODUCT_POWER_CONSUMPTION"));
				pvo.setProductRatedVoltage(rs.getInt("PRODUCT_RATED_VOLTAGE"));
				pvo.setProductInformation(rs.getString("PRODUCT_INFORMATION"));
				pvo.setProductNo(rs.getInt("PRODUCT_NO"));
				pvo.setProductCategory(rs.getInt("product_category"));
				pbvo.setPvo(pvo);
				list.add(pbvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManagement.close(rs, pstmt, conn);
		}
		return list;
	}

	public List<ProductBoardVO> searchResultSelect(String search, int pageNo) {
		Connection con = DBManagement.getConnection();
		String selectQuery = "select * from product,bridge_p_pb,product_board where product.PRODUCT_NO=bridge_p_pb.PRODUCT_NO and product_board.PRODUCT_BOARD_NO=bridge_p_pb.PRODUCT_BOARD_NO and product_board.product_board_title like '%"
				+ search + "%' group by product_board.PRODUCT_BOARD_NO limit ?, 10";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductBoardVO> pbvolist = null;
		List<ProductVO> pvoList = null;
		try {
			pstmt = con.prepareStatement(selectQuery);
			pstmt.setInt(1, (pageNo / 10));
			rs = pstmt.executeQuery();
			pbvolist = new ArrayList<ProductBoardVO>();

			while (rs.next()) {
				ProductDAO pdao = ProductDAO.getInstance();
				pvoList = new ArrayList<ProductVO>();
				pvoList = pdao.selectPboard(rs.getInt("product_board.PRODUCT_BOARD_NO"));
				System.out.println(pvoList.get(0).toString());
				pbvolist.add(new ProductBoardVO(rs.getInt("product_board.PRODUCT_BOARD_NO"), rs.getString("product_board.PRODUCT_BOARD_TITLE"),
						rs.getInt("bridge_p_pb.CURRENT_PRICE"), rs.getInt("bridge_p_pb.product_stock"),
						rs.getDate("product_board.REGISTERED_DATE"), pvoList));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManagement.close(rs, pstmt, con);

		}
		return pbvolist;
	}
	
	public List<ReviewVO> reviewSelect(String productboardno){
		String selectQuery = "select *  from review where product_board_no= ? order by REVIEW_DATE ;";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ReviewVO> list = null;
		try {
			list = new ArrayList<ReviewVO>();
			conn = DBManagement.getConnection();
			pstmt = conn.prepareStatement(selectQuery);
			pstmt.setString(1, productboardno);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReviewVO rvo = new ReviewVO();
				rvo.setProductBoardNo(rs.getInt("product_board_no"));
				rvo.setMemberEmail(rs.getString("MEMBER_EMAIL"));
				rvo.setReview_content(rs.getString("review_content"));
				rvo.setReview_date(rs.getTimestamp("review_date"));
				rvo.setReview_images(rs.getString("review_images"));
				list.add(rvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManagement.close(rs, pstmt, conn);
		}
		return list;
	}
	public ProductBoardVO productselectone(String productno){
		String selectQuery = "select p.PRODUCT_NO,p.PRODUCT_MANUFACTURER, pb.PRODUCT_BOARD_TITLE ,bppb.CURRENT_PRICE,p.PRODUCT_SUBIMAGE,p.PRODUCT_SIZE,p.PRODUCT_COLOR from bridge_p_pb bppb inner join product p on bppb.PRODUCT_NO = p.PRODUCT_NO inner join product_board pb on pb.PRODUCT_BOARD_NO = bppb.PRODUCT_BOARD_NO where p.product_no = ?; ";
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductBoardVO pbvo = new ProductBoardVO();
		ProductVO pvo = new ProductVO();
		try {
			con = DBManagement.getConnection();
			pstmt = con.prepareStatement(selectQuery);
			pstmt.setInt(1, Integer.parseInt(productno));
			rs = pstmt.executeQuery();
			if(rs.next()){
				 pbvo.setCurrentPrice(rs.getInt("CURRENT_PRICE"));
				 pbvo.setProductBoardTitle(rs.getString("PRODUCT_BOARD_TITLE"));
				 pvo.setProductNo(rs.getInt("PRODUCT_NO"));
				 pvo.setProductManufacturer(rs.getString("PRODUCT_MANUFACTURER"));
				 pvo.setProductSubImage(rs.getString("PRODUCT_SUBIMAGE"));
				 pvo.setProductSize(rs.getString("PRODUCT_SIZE"));
				 pvo.setProductColor(rs.getString("PRODUCT_SIZE"));
				 pbvo.setPvo(pvo);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManagement.close(rs, pstmt, con);
		}
		return pbvo;
	}
	
	public int productselectcategory(int Pno){
		String selectQuery = "SELECT distinct p.PRODUCT_CATEGORY FROM product_board pb inner join bridge_p_pb bpb on pb.PRODUCT_BOARD_NO = bpb.PRODUCT_BOARD_NO inner join product p on bpb.PRODUCT_NO = p.PRODUCT_NO where pb.PRODUCT_BOARD_NO = ?;";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int Category = 0;
		try {
			conn = DBManagement.getConnection();
			pstmt = conn.prepareStatement(selectQuery);
			pstmt.setInt(1, Pno);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Category = rs.getInt("p.PRODUCT_CATEGORY");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManagement.close(rs, pstmt, conn);
		}
		return Category;
	}
	public void insertorder(String productno , String memberemail, String price,int usepoint ,String discountreason, String quantity, String status, int deliveryprice, String deliveryname,String deliveryphoneno,String deliverypostno, String deliveryaddress,String orderno) {
		String query = "insert into sales_status (product_no,member_email,price,discount,discount_reason,quantity,status,delivery_price,delivery_name,Delivery_phone_no,Delivery_post_no,Delivery_address,order_no) "
				+ "value (?,?,?,?,?,?,?,?,?,?,?,?,?); ";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBManagement.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(productno));			
			pstmt.setString(2, memberemail);			
			pstmt.setInt(3, Integer.parseInt(price));			
			pstmt.setInt(4, usepoint);			
			pstmt.setString(5, discountreason);			
			pstmt.setInt(6, Integer.parseInt(quantity));			
			pstmt.setString(7, status);
			pstmt.setInt(8, deliveryprice);
			pstmt.setString(9, deliveryname);
			pstmt.setString(10, deliveryphoneno);
			pstmt.setString(11, deliverypostno);
			pstmt.setString(12, deliveryaddress);
			pstmt.setString(13, orderno);
			System.out.println(pstmt);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManagement.close(pstmt, con);
		}
	}

	public List<SalesStatusVO> selectPurchasedProduct(String userid, String orderNo) {
		String selectQuery = "select * from sales_status where member_email = ? and order_no = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SalesStatusVO> list = null;
		try {
			conn = DBManagement.getConnection();
			pstmt = conn.prepareStatement(selectQuery);
			list = new ArrayList<SalesStatusVO>();
			pstmt.setString(1, userid);
			pstmt.setString(2, orderNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				SalesStatusVO ssVo = new SalesStatusVO();
				ssVo.setPrice(rs.getInt("price"));
				ssVo.setDiscount(rs.getInt("discount"));
				ssVo.setDiscountReason(rs.getString("discount_reason"));
				ssVo.setDeliveryPrice(rs.getInt("delivery_price"));
				ssVo.setQuantity(rs.getInt("quantity"));
				ssVo.setDeliveryName(rs.getString("delivery_name"));
				ssVo.setDeliveryAddress(rs.getString("delivery_address"));
				ssVo.setDeliveryPhoneNo(rs.getString("delivery_phone_no"));
				ssVo.setSellDate(rs.getDate("sell_date"));
				list.add(ssVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManagement.close(rs, pstmt, conn);
		}
		return list;
	}
	
	
}
