package com.teamontb.cart.action;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.teamontb.action.Action;
import com.teamontb.cart.dao.CartDAO;
import com.teamontb.member.dto.MemberVO;

public class Cartdelete implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8"); 
		CartDAO CDao = CartDAO.getInstance();
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginmember");
		String userid = mvo.getMemberEmail();
		String productno = request.getParameter("productno");
		CDao.deleteBasket(userid, productno);
		
		PrintWriter pw= response.getWriter();
		pw.write("<script>alert('장바구니가 삭제되었습니다,');location.href='shop.do?product=cartlist';</script>");
	}

}
