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
import com.teamontb.pboard.dto.ProductBoardVO;

public class SearchProductAct implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		ProductBoardDAO pbdao = ProductBoardDAO.getInstance();
		List<ProductBoardVO> pbList = pbdao.autoComplete();
		Gson gson = new Gson();
		String json = gson.toJson(pbList);
		pw.println(json);
	}

}
