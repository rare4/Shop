package com.teamontb.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.teamontb.action.Action;
import com.teamontb.member.dao.MemberDAO;
import com.teamontb.member.dto.MemberVO;

public class MemberLoginAct implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 
		String email=request.getParameter("email");
		String pwd=request.getParameter("pwd");
		MemberDAO mdao=MemberDAO.getInstance();
		MemberVO mvo=mdao.checkMember(email,pwd);
		RequestDispatcher rd=null;
		if(mvo!=null){
			int count=mvo.getLoginStatus()+1;
			mdao.updateLogin(email, pwd,count);
			HttpSession session=request.getSession();
			session.setAttribute("loginmember", mvo);
			rd=request.getRequestDispatcher("index.jsp");
		}else{
			request.setAttribute("statusMessage", "<script>alert('로그인 실패');</script>");
			rd=request.getRequestDispatcher("consumer/login.jsp");
		}
		rd.forward(request, response);
		
	}
}
