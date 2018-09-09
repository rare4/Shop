package com.teamontb.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.teamontb.action.Action;
import com.teamontb.member.dao.MemberDAO;
import com.teamontb.member.dto.MemberVO;

public class SalesStatusUpdateAct implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		String status = request.getParameter("status");
		
		String statusReason = request.getParameter("statusReason");
		
		String etc = request.getParameter("etc");
		String orderno =request.getParameter("orderno");
		int pbNo = Integer.parseInt(request.getParameter("pbNo"));
		
		HttpSession session = request.getSession();
		MemberVO loginmember = (MemberVO)session.getAttribute("loginmember");
		MemberDAO mdao = MemberDAO.getInstance();
		
		mdao.salesStatusUpdate(loginmember.getMemberEmail(), status, statusReason, etc, pbNo,orderno);
		pw.println("<script>alert('" + status + "이 완료 되었습니다.');location.href='shop.do?member=memberPage'</script>");
	}

}
