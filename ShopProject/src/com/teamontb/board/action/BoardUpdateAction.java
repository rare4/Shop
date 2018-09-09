package com.teamontb.board.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teamontb.action.Action;
import com.teamontb.board.dao.BoardDAO;
import com.teamontb.board.dto.BoardVO;

public class BoardUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String outputUrl = "BoardList.jsp";
		String title = request.getParameter("title");
		String email = request.getParameter("memberemail");
		String content = request.getParameter("content");
		int boardnum = Integer.parseInt(request.getParameter("no"));
		
		System.out.println("title:"+title+", email:" +email+",content:" +content + ", boardnum:" + boardnum);
		BoardDAO bDao = BoardDAO.getInstance();
		BoardVO vo = new BoardVO(boardnum,title,content);
		System.out.println(vo.toString());
		
		
		
		boolean result = bDao.updateBoard(vo);
		System.out.println(result);
		//줄바꿈
		content.replace("\r\n", "<br>");
		vo.setBoard_content(request.getParameter("content").replace("\r\n,","<br>").replace("&", "&amp;"));
		
		if(result == false) {
			System.out.println("글쓰기에 실패했습니다.");
		}
		else if(result == true) {
			new BoardListAction().execute(request, response);
		}
		else {
			System.out.println("문제가 있습니다.");
		}
	}

}
