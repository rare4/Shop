package com.teamontb.pboard.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.teamontb.action.Action;
import com.teamontb.member.dao.MemberDAO;
import com.teamontb.member.dto.MemberVO;
import com.teamontb.member.dto.PointVO;
import com.teamontb.pboard.dao.ProductBoardDAO;
import com.teamontb.pboard.dto.ProductBoardVO;



public class Orderinsert implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//장바구니와 상품디테일 페이지에서 구매확정페이지로 넘기는 부분
		String[] option = request.getParameterValues("option");
	    String[] count = request.getParameterValues("count");
		ProductBoardDAO pbdao = ProductBoardDAO.getInstance();
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginmember");
		System.out.println(mvo);
		if(mvo == null){
			RequestDispatcher rd = request.getRequestDispatcher("consumer/login.jsp");
			rd.forward(request, response);
		}else{
		MemberDAO mDAO = MemberDAO.getInstance();
		List<PointVO> pointList = mDAO.pointList(mvo.getMemberEmail());
		List<ProductBoardVO> list = new ArrayList<ProductBoardVO>();
		for (int i = 0; i<option.length; i++){
			list.add(pbdao.productselectone(option[i]));
		}
		request.setAttribute("pointList",pointList);
	    request.setAttribute("ordercount", count);
		request.setAttribute("product",list);
		RequestDispatcher rd = request.getRequestDispatcher("product/productConfirm.jsp");
		rd.forward(request, response);
		}
	}

}
