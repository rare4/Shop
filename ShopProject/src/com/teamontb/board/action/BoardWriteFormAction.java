package com.teamontb.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teamontb.action.Action;

public class BoardWriteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String outputUrl = "board/boardWrite.jsp";
		
		RequestDispatcher rd = request.getRequestDispatcher(outputUrl);
		rd.forward(request, response);

	}

}
