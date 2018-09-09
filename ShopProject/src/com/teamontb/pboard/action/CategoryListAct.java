package com.teamontb.pboard.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teamontb.action.Action;
import com.teamontb.pboard.dao.ProductBoardDAO;
import com.teamontb.pboard.dto.ProductBoardVO;

public class CategoryListAct implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductBoardDAO pbdao = ProductBoardDAO.getInstance();
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		int category = Integer.parseInt(request.getParameter("category"));
		int totProduct = pbdao.totProductList(category, null);
		List<ProductBoardVO> list = pbdao.selectListByCategory(category, pageNo);
		switch(category) {
		case 1:
			request.setAttribute("categoryList", "코브 조명");
			break;
		case 2:
			request.setAttribute("categoryList", "풋 라이트");
			break;
		case 3:
			request.setAttribute("categoryList", "스포트 라이트");
			break;
		case 4:
			request.setAttribute("categoryList", "실링 라이트");
			break;
		case 5:
			request.setAttribute("categoryList", "샹들리에");
			break;
		case 6:
			request.setAttribute("categoryList", "다운 라이트");
			break;
		case 7:
			request.setAttribute("categoryList", "팬던트");
			break;
		case 8:
			request.setAttribute("categoryList", "브래킷");
			break;
		case 9:
			request.setAttribute("categoryList", "플로어 스탠드");
			break;
		case 10:
			request.setAttribute("categoryList", "테이블 램프");
			break;
		case 11:
			request.setAttribute("categoryList", "플로어 램프");
			break;
		}
		request.setAttribute("totProduct", totProduct);
		request.setAttribute("productListByCategory", list);
		RequestDispatcher rd = request.getRequestDispatcher("product/productList.jsp");
		rd.forward(request, response);
	}

}
