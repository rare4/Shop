 package com.teamontb.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teamontb.action.Action;
import com.teamontb.action.ActionFactory;

@WebServlet({ "/ShopServlet", "/shop.do" })
public class ShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ShopServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String product = request.getParameter("product");
		String member = request.getParameter("member");
		String board = request.getParameter("board");
		String main = request.getParameter("main");
		ActionFactory actionFactory = ActionFactory.getInstance();
		if (product != null) {
			Action action = actionFactory.getProductAction(product);
			action.execute(request, response);
		} else if (member != null) {
			Action action = actionFactory.getMemberAction(member);
				action.execute(request, response);
		} else if (board != null) {
			Action action = actionFactory.getBoardAction(board);
			action.execute(request, response);
		} else if (main != null){
			Action action = actionFactory.getMainAction(main);
			action.execute(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
