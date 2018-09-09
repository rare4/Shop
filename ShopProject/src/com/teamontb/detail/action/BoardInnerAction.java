package com.teamontb.detail.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teamontb.action.Action;
import com.teamontb.pboard.dao.ProductBoardDAO;
import com.teamontb.pboard.dto.ProductBoardVO;
import com.teamontb.product.dao.ProductDAO;
import com.teamontb.product.dto.ProductVO;

public class BoardInnerAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("갔다옴");
		String outputUrl = "/consumer/DetailPage.jsp?";
		
		ProductDAO bDao = ProductDAO.getInstance();
		List<ProductVO> list = bDao.selectAll();
		
		request.setAttribute("detail", list);
		System.out.println(request.getAttribute("detail"));
		
		ProductBoardDAO bDao2 = ProductBoardDAO.getInstance();
		List<ProductBoardVO> list2 = bDao2.selectAll();
		
		request.setAttribute("pboard", list2);
		
		RequestDispatcher rd = request.getRequestDispatcher(outputUrl);
		rd.forward(request, response);

	}

}
