package com.teamontb.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.teamontb.board.dto.*;

import util.DBManagement;

public class BoardDAO {
	private static BoardDAO instance = new BoardDAO();
	
	private BoardDAO() {
		
	}
	
	public static BoardDAO getInstance() {
		if(instance == null) {
			instance = new BoardDAO();
			
		}
		return instance;
		}
		
	
	public List<BoardVO> selectAllBoard(int a, int b){
		String query = "select * from project.customer_board order by CUSTOMER_BOARD_NO desc limit ?, ?;";
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println(query);
		
		try {
			con = util.DBManagement.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, a);
			pstmt.setInt(2, b);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(new BoardVO(
						rs.getInt("customer_board_no"),
						rs.getString("member_email"),
						rs.getString("board_title"),
						rs.getString("board_content"),
						rs.getString("board_images"),
						rs.getString("board_category"),
						rs.getTimestamp("written_date"),
						rs.getTimestamp("modified_date")
					));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		
		DBManagement.close(rs, pstmt, con);
		}
		
		return list;
	}
	
	
	public BoardVO selectOneBoard(String no){
		String query = "select * from project.customer_board where customer_board_no=?";
	
		BoardVO list = new BoardVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
	
		try {
			con = util.DBManagement.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.setCustomer_board_no(rs.getInt("customer_board_no"));
				list.setMember_email(rs.getString("member_email"));
				list.setBoard_title(rs.getString("board_title"));
				list.setBoard_content(rs.getString("board_content"));
				list.setBoard_images(rs.getString("board_images"));
				list.setBoard_category(rs.getString("board_category"));
				list.setWritten_date(rs.getTimestamp("written_date"));
				list.setModified_date(rs.getTimestamp("modified_date"));
				
			}
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			DBManagement.close(rs, pstmt, con);
			}
		return list;
		
	}

	public boolean insertBoard(BoardVO vo) {
		boolean result = false;
		
	
		String query="insert into project.customer_board(member_email,board_title,board_content) values(?,?,?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {
			con = util.DBManagement.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, vo.getMember_email());
			pstmt.setString(2, vo.getBoard_title());
			pstmt.setString(3, vo.getBoard_content());
			
			if(pstmt.executeUpdate() == 1) {
				result = true;
			}else {
				result=false;
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			DBManagement.close(pstmt, con);
			}
		
		
		return result;
	}
	

	public boolean updateBoard(BoardVO vo) {
		boolean result = true;
		
		String query="update customer_board set board_title=?,board_content=? where customer_board_no= ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {
			con = util.DBManagement.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, vo.getBoard_title());
			pstmt.setString(2, vo.getBoard_content());
			pstmt.setInt(3, vo.getCustomer_board_no());
			pstmt.executeUpdate();
			
			
			if(pstmt.executeUpdate() != 0) {
				result = true;
			}else {
				result=false;
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			DBManagement.close(pstmt, con);
			
			}
		
		return result;
		
	}

	public void deleteBoard(int boardnum) {

		String query = "delete from customer_board where customer_board_no=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = util.DBManagement.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, boardnum);
			pstmt.executeUpdate();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			DBManagement.close(pstmt, con);
			
			}
		
	
		
	}
	
	public int countBoard() {
		String query = "select count(*) from customer_board;";
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = util.DBManagement.getConnection();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				result = rs.getInt(1);
			}
			System.out.println("카운트:" + result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			DBManagement.close(pstmt, con);
			
			}
		
	
		return result;
	}
}
