package com.teamontb.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teamontb.action.Action;
import com.teamontb.board.dao.BoardDAO;
import com.teamontb.board.dto.BoardVO;

public class BoardUpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String outputUrl = "board/boardUpdate.jsp";
		String board_num = request.getParameter("no");
		
		BoardDAO dd = BoardDAO.getInstance();
		BoardVO vv = new BoardVO();
		
		vv = dd.selectOneBoard(board_num);
		request.setAttribute("board", vv);
		RequestDispatcher rd = request.getRequestDispatcher(outputUrl);
		rd.forward(request, response);
	}

}
