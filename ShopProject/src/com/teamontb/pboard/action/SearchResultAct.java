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

public class SearchResultAct implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("searchBar");
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		ProductBoardDAO pbdao = ProductBoardDAO.getInstance();
		List<ProductBoardVO> list = pbdao.searchResultSelect(search, pageNo);
		int productCount = pbdao.totProductList(0, search);
		request.setAttribute("totProduct", productCount);
		request.setAttribute("productListByCategory", list);
		request.setAttribute("searchResult", "검색 결과");
		RequestDispatcher rd = request.getRequestDispatcher("product/productList.jsp");
		rd.forward(request, response);
	}

}
