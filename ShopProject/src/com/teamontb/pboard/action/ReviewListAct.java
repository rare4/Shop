package com.teamontb.pboard.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.teamontb.action.Action;

import com.teamontb.pboard.dao.ProductBoardDAO;
import com.teamontb.product.dto.ReviewVO;

public class ReviewListAct implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductBoardDAO pDao = ProductBoardDAO.getInstance();
		String productBoardNo = request.getParameter("productBoardNo");
		List<ReviewVO> list = pDao.reviewSelect(productBoardNo);
		response.setContentType("application/json; charset=UTF-8");	
		PrintWriter w = response.getWriter();
		Gson gson = new Gson();
		w.print(gson.toJson(list));
		w.flush();
		w.close();
	}

}
