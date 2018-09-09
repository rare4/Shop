package com.teamontb.member.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teamontb.action.Action;
import com.teamontb.member.dao.MemberDAO;

public class MemberJoinAct implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 
		String email=request.getParameter("email");
		String pwd=request.getParameter("pwd");
		String phoneNo=request.getParameter("phoneNo");
		String fullAddr=request.getParameter("roadFullAddr");
		String zipNo=request.getParameter("zipNo");
		String gender=request.getParameter("rb");
		String birthday=request.getParameter("birthday");
		System.out.println(birthday);
		MemberDAO mdao=MemberDAO.getInstance();
		SimpleDateFormat bthday=new SimpleDateFormat("yyyy-MM-dd");
		Date inputBirth = null;
		try {
			inputBirth = bthday.parse(birthday);
			Calendar cal=Calendar.getInstance();
			cal.setTime(inputBirth);
			cal.add(Calendar.DATE, 1);
			inputBirth=cal.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(gender);
		boolean result=mdao.insertMember(email, pwd, fullAddr, zipNo, phoneNo, gender,inputBirth);
		
		RequestDispatcher rd=null;
		
		if(result){
			rd=request.getRequestDispatcher("/index.jsp");
		}else{
			PrintWriter pw=response.getWriter();
			pw.print("alert('회원가입 실패');");
			rd=request.getRequestDispatcher("/consumer/joinin.jsp");
		}
		rd.forward(request, response);
	}

}
