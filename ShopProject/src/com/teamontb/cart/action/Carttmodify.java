package com.teamontb.cart.action;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.teamontb.cart.dao.CartDAO;
import com.teamontb.member.dto.MemberVO;
import com.teamontb.action.Action;

public class Carttmodify implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CartDAO bDao = CartDAO.getInstance();
		response.setContentType("text/html; charset=UTF-8"); 
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginmember");
		String userid = mvo.getMemberEmail();
		String productno = request.getParameter("productno");
		String count = request.getParameter("count");
		bDao.updateBasket(userid, productno, count);
		PrintWriter pw= response.getWriter();
		pw.write("<script>alert('장바구니 수량이 변경 되었습니다,');location.href='shop.do?product=cartlist';</script>");
	}

}
