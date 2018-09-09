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

public class MemberPwdConfirmAct implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		MemberDAO mdao = MemberDAO.getInstance();
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginmember");
		boolean result = mdao.checkPwd(mvo.getMemberEmail(), request.getParameter("memberPwd"));
		if(result == false) {
			pw.println("<script>alert('비밀번호가 정확하지 않습니다.');history.go(-1);</script>");
		} else {
			mdao.withdrawalMember(mvo.getMemberEmail());
			session.removeAttribute("loginmember");
			pw.println("<script>alert('성공적으로 탈퇴가 처리되었습니다.');location.href='index.jsp';</script>");
		}
	}

}
