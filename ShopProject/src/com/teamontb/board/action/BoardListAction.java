package com.teamontb.board.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teamontb.action.Action;
import com.teamontb.board.dao.BoardDAO;
import com.teamontb.board.dto.BoardVO;

public class BoardListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String outputUrl = "board/boardList.jsp";

		BoardDAO bDao = BoardDAO.getInstance();
		int a, b;
		int pageno = 1;
		
		if(request.getParameter("pageno") != null) {
			pageno = Integer.parseInt(request.getParameter("pageno"));
			
		}
		int count = bDao.countBoard();
		System.out.println("보드리스트액션카운트:" + count);
		if(pageno == 1) {
			a=0;
			b=10;
		}else {
			a=(pageno-1)*10;
			b= 10;
		}
		System.out.println(a);
		System.out.println(b);
		List<BoardVO> list = bDao.selectAllBoard(a,b);
		
		request.setAttribute("count", count);
		request.setAttribute("boardList", list);
		
	
		RequestDispatcher rd = request.getRequestDispatcher(outputUrl);
		rd.forward(request, response);
	}

}
