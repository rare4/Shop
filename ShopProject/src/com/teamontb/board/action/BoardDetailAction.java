package com.teamontb.board.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.teamontb.action.Action;
import com.teamontb.board.dao.BoardDAO;
import com.teamontb.board.dto.BoardVO;
import com.teamontb.product.dao.ProductDAO;
import com.teamontb.product.dto.ProductVO;

public class BoardDetailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String outputUrl = "board/boardDetail.jsp";
		String no = request.getParameter("no");
		System.out.println(no);
		BoardDAO bDao = BoardDAO.getInstance();
		BoardVO list = bDao.selectOneBoard(no);
		
		request.setAttribute("list", list);
		
		RequestDispatcher rd = request.getRequestDispatcher(outputUrl);
		rd.forward(request, response);
		

	}

}
