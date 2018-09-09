package com.teamontb.detail.action;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teamontb.action.Action;
import com.teamontb.board.action.BoardListAction;
import com.teamontb.board.dao.BoardDAO;
import com.teamontb.board.dto.BoardVO;
import com.teamontb.product.dao.ProductDAO;
import com.teamontb.product.dto.ReviewVO;

public class ReviewWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String outputUrl = "product/productdetailpage.jsp";
		String title = request.getParameter("title");
		String email = request.getParameter("memberEmail");
		String content = request.getParameter("content");
		
		String contents = content.replace("\r\n","<br>");
		String reviewImage = request.getParameter("reviewImage");
		int pNo = Integer.parseInt(request.getParameter("pNo"));
		int starValue = Integer.parseInt(request.getParameter("starValue"));
		
		ProductDAO pdao = ProductDAO.getInstance();
		ReviewVO vo = new ReviewVO(pNo, email, contents,  reviewImage,  starValue);
	
		pdao.AddReview(vo);
		
		new DetailAction().execute(request, response);
	

}
}
