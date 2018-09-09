package com.teamontb.main.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teamontb.action.Action;
import com.teamontb.board.dao.BoardDAO;
import com.teamontb.board.dto.BoardVO;
import com.teamontb.pboard.dao.ProductBoardDAO;
import com.teamontb.pboard.dto.ProductBoardVO;
import com.teamontb.product.dao.ProductDAO;
import com.teamontb.product.dto.ProductVO;

public class MainAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("갔다옴");
		String outputUrl = "/index.jsp?";
		
		ProductDAO bDao = ProductDAO.getInstance();
		List<ProductVO> list = bDao.selectAll();
		
		request.setAttribute("main", list);
		System.out.println(request.getAttribute("main"));
		
		ProductBoardDAO bDao2 = ProductBoardDAO.getInstance();
		List<ProductBoardVO> selectAllList = bDao2.selectAll();
		request.setAttribute("productList", selectAllList);
		
		RequestDispatcher rd = request.getRequestDispatcher(outputUrl);
		rd.forward(request, response);
	}

}
