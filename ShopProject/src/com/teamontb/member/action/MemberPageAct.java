package com.teamontb.member.action;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.teamontb.product.dao.ProductDAO;
import com.teamontb.product.dto.SalesStatusVO;

public class MemberPageAct implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginmember") == null) {
			PrintWriter pw = response.getWriter();
			pw.println("<script>alert('잘못된 접근입니다.');location.href='index.jsp';</script>");
			return;
		}
		MemberVO mvo = (MemberVO) session.getAttribute("loginmember");
		MemberDAO mDAO = MemberDAO.getInstance();
		ProductDAO pDAO = ProductDAO.getInstance();
		List<PointVO> pointList = mDAO.pointList(mvo.getMemberEmail());
		List<SalesStatusVO> productOrderList = pDAO.getSalesStatusOrderList(mvo.getMemberEmail());
		List<SalesStatusVO> productStatusList = pDAO.getProductStatusList(mvo.getMemberEmail());
		List<SalesStatusVO> productRepriceList = pDAO.getSalesRepriceList(mvo.getMemberEmail());
		request.setAttribute("pointList", pointList);
		request.setAttribute("productOrderList", productOrderList);
		request.setAttribute("productStatusList", productStatusList);
		request.setAttribute("productRepriceList", productRepriceList);
		RequestDispatcher rd = request.getRequestDispatcher("consumer/memberPage.jsp");
		rd.forward(request, response);
	}

}
