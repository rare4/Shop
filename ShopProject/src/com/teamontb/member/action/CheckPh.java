package com.teamontb.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teamontb.action.Action;
import com.teamontb.member.dao.MemberDAO;

public class CheckPh implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 
		String phoneNo=request.getParameter("phone");
		MemberDAO mdao=MemberDAO.getInstance();
		String email=mdao.findEmail(phoneNo);
		PrintWriter pw=response.getWriter();
		RequestDispatcher rd=null;
		rd=request.getRequestDispatcher("/index.jsp");
		if(email!=""){
			pw.println("<script>alert("+email+");</script>");
		}else{
			pw.println("<script>alert('해당 회원이 존재하지 않습니다.');</script>");
		}
		pw.println("<script>history.back();</script>");
		rd.forward(request, response);
	}

}
