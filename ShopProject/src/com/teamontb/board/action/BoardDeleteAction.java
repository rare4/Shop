package com.teamontb.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teamontb.action.Action;
import com.teamontb.board.dao.BoardDAO;
import com.teamontb.board.dto.BoardVO;

public class BoardDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String outputUrl = "BoardList.jsp";
		
		String boardnum = request.getParameter("no");
		
		BoardDAO bDao = BoardDAO.getInstance();
		
		bDao.deleteBoard(Integer.parseInt(boardnum));
		
		//new BoardListAction().execute(request, response);

		RequestDispatcher rd = request.getRequestDispatcher(outputUrl);
		rd.forward(request, response);
		

	}

}
