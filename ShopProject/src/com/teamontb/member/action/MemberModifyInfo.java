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

public class MemberModifyInfo implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		HttpSession session = request.getSession();
		MemberDAO mdao = MemberDAO.getInstance();
		MemberVO mvo = (MemberVO) session.getAttribute("loginmember");
		String curPwd = request.getParameter("curPwd");
		String changePwd = request.getParameter("changePwd");
		String changePhoneNo = request.getParameter("changePhoneNo");
		String changeRoadAddr = request.getParameter("roadFullAddr");
	      String changePostNo = request.getParameter("zipNo");
		System.out.println(changeRoadAddr);
		System.out.println(changePostNo);
		boolean confirm = mdao.modifyMemberInfo(mvo.getMemberEmail(), curPwd, changePwd, changePhoneNo, changeRoadAddr, changePostNo);
		if(confirm == false) {
			pw.println("<script>alert('비밀번호가 일치하지 않습니다!');history.go(-1);</script>");
		}else{
			pw.println("<script>alert('회원정보가 바뀌었습니다.');history.go(-1);</script>");
		}
	}

}
