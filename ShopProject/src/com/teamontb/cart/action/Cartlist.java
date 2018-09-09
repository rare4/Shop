package com.teamontb.cart.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.teamontb.cart.dao.CartDAO;
import com.teamontb.cart.dto.CartVO;
import com.teamontb.member.dto.MemberVO;
import com.teamontb.action.Action;


public class Cartlist implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8"); 
		HttpSession session = request.getSession();		
		MemberVO mvo = (MemberVO)session.getAttribute("loginmember");

		String outputUrl = "cart/cart.jsp";
		
		if(mvo !=null){
			String userid = mvo.getMemberEmail();
		CartDAO CDao = CartDAO.getInstance();
		List<CartVO>list = CDao.selectBasket(userid);
		request.setAttribute("cartList",list);
		RequestDispatcher rd = request.getRequestDispatcher(outputUrl);
		rd.forward(request, response);
		}else if(mvo == null){
			RequestDispatcher rd = request.getRequestDispatcher(outputUrl);
			rd.forward(request, response);
		}
	}

}
