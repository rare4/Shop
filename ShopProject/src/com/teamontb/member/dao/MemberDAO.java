package com.teamontb.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.teamontb.member.dto.MemberVO;
import com.teamontb.member.dto.PointVO;

import util.DBManagement;

public class MemberDAO {
	private static MemberDAO instance = new MemberDAO();

	private MemberDAO() {
	}

	public static MemberDAO getInstance() {
		if (instance == null)
			instance = new MemberDAO();
		return instance;
	}
	public String findEmail(String phoneNo){
		String query="select member_email from member where member_phone_no="+phoneNo;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result="";
		try {
			conn = DBManagement.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result=rs.getString(0);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManagement.close(rs, pstmt, conn);
		}
		return result;
	}
	public String findpwd(String email,String phoneNo){
		String query="select member_password from member where member_phone_no="+phoneNo+" and member_email="+email;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result="";
		try {
			conn = DBManagement.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result=rs.getString(0);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManagement.close(rs, pstmt, conn);
		}
		return result;
	}
	public MemberVO checkMember(String email, String pwd) {
		String query = "select * from member where member_email='" + email + "' and member_password='" + pwd + "' and withdrawal =0";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO mvo = null;
		try {
			conn = DBManagement.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				mvo = new MemberVO(rs.getString("member_email"), rs.getString("member_password"), rs.getString("MEMBER_ADDRESS"),
						rs.getString("MEMBER_POST_NO"), rs.getString("MEMBER_PHONE_NO"), rs.getString("MEMBER_GENDER"), rs.getDate("MEMBER_BIRTHDAY"), rs.getDate("JOIN_DATE"),
						rs.getInt("MEMBER_ADMIN"), rs.getInt("LOGIN_STATUS"), rs.getInt("WITHDRAWAL"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManagement.close(rs, pstmt, conn);
		}
		return mvo;
	}
	public void updateLogin(String email, String pwd,int count){
		String query="update member set login_status="+count+" where member_email='" + email + "' and member_password='" + pwd + "'";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		try {
			conn = DBManagement.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManagement.close(pstmt, conn);
		}
	} 
	public boolean insertMember(String email, String pwd, String fullAddr, String zipno, String phoneNo, String gender, Date birthday) {
		boolean result = false;
		String query = "insert into member (MEMBER_EMAIL,MEMBER_PASSWORD,MEMBER_ADDRESS,MEMBER_POST_NO,MEMBER_PHONE_NO,MEMBER_GENDER,MEMBER_BIRTHDAY) values (?,?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		java.sql.Date sqlDate= new java.sql.Date(birthday.getTime());
		try {
			conn = DBManagement.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, email);
			pstmt.setString(2, pwd);
			pstmt.setString(3, fullAddr);
			pstmt.setString(4, zipno);
			pstmt.setString(5, phoneNo);
			pstmt.setString(6, gender);
			pstmt.setDate(7,sqlDate);
			int input = pstmt.executeUpdate();
			if (input == 1) {
				System.out.println("회원가입 성공");
				result = true;
			} else {
				System.out.println("회원가입 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManagement.close(pstmt, conn);
		}
		return result;
	}

	public List<PointVO> pointList(String memberEmail) {
		List<PointVO> list = new ArrayList<PointVO>();
		String selectQuery = "select * from point_history ph, point_category pc where pc.POINT_CATEGORY = ph.POINT_CATEGORY and ph.MEMBER_EMAIL = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManagement.getConnection();
			pstmt = conn.prepareStatement(selectQuery);
			pstmt.setString(1, memberEmail);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new PointVO(rs.getInt("point_category"), rs.getDate("point_date"), rs.getInt("point_history"), rs.getString("point_reason"), rs.getInt("point")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManagement.close(rs, pstmt, conn);
		}
		return list;
	}

	public boolean checkPwd(String memberEmail, String chkPwd) {
		boolean result = false;
		String selectQuery = "select * from member where MEMBER_EMAIL = ? and MEMBER_PASSWORD = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManagement.getConnection();
			pstmt = conn.prepareStatement(selectQuery);
			pstmt.setString(1, memberEmail);
			pstmt.setString(2, chkPwd);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManagement.close(rs, pstmt, conn);
		}
		return result;
	}

	public void withdrawalMember(String memberEmail) {
		String updateQuery = "update member set withdrawal = 1 where member_email = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManagement.getConnection();
			pstmt = conn.prepareStatement(updateQuery);
			pstmt.setString(1, memberEmail);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManagement.close(pstmt, conn);
		}
	}
	public void insertusePoint(String memberEmail,int usepoint) {
		String updateQuery = "insert into point_history (point_category,member_email,point_history) values(4,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManagement.getConnection();
			pstmt = conn.prepareStatement(updateQuery);
			pstmt.setString(1, memberEmail);
			pstmt.setInt(2, usepoint * -1);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManagement.close(pstmt, conn);
		}
	}

	public void salesStatusUpdate(String memberEmail, String status, String statusReason, String etc, int pbNo, String orderno) {
		String updateQuery = "update sales_status set status = ?, status_reason = ? where member_email = ? and product_no = ? and order_no = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManagement.getConnection();
			pstmt = conn.prepareStatement(updateQuery);
			pstmt.setString(1, status);
			if(statusReason != "기타") {
			pstmt.setString(2, statusReason);
			} else {
				pstmt.setString(2, statusReason + " : " + etc);
			}
			pstmt.setString(3, memberEmail);
			pstmt.setInt(4, pbNo);
			pstmt.setString(5, orderno);
			System.out.println(pstmt);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManagement.close(pstmt, conn);
		}
	}

	public boolean modifyMemberInfo(String memberEmail, String curPwd, String changePwd, String changePhoneNo, String changeRoadAddr,
			String changePostNo) {
		boolean result = false;
		String updateQuery = "update member set member_password = ?, member_Phone_No = ?, member_address = ?, member_post_no = ? where member_password = ? and member_email = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManagement.getConnection();
			pstmt = conn.prepareStatement(updateQuery);
			pstmt.setString(1, changePwd);
			pstmt.setString(2, changePhoneNo);
			pstmt.setString(3, changeRoadAddr);
			pstmt.setString(4, changePostNo);
			pstmt.setString(5, curPwd);
			pstmt.setString(6, memberEmail);
			int rs = pstmt.executeUpdate();
			
			if(rs == 1)
				result = true;
		} catch (SQLException e) {
			result = false;
		} finally {
			DBManagement.close(pstmt, conn);
		}
		return result;
	}
}
