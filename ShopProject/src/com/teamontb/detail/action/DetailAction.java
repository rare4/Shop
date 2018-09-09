package com.teamontb.detail.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teamontb.action.Action;
import com.teamontb.pboard.dao.ProductBoardDAO;
import com.teamontb.pboard.dto.ProductBoardVO;
import com.teamontb.product.dao.ProductDAO;
import com.teamontb.product.dto.ReviewVO;


public class DetailAction implements Action {

   @Override
   public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      System.out.println("갔다옴");
      String outputUrl = "/product/productdetailpage.jsp?";
      
      ProductBoardDAO bDao = ProductBoardDAO.getInstance();
      String pNo = request.getParameter("pNo"); 
      ProductDAO dao = ProductDAO.getInstance();
      
      int rCount = dao.countReview(Integer.parseInt(pNo));
      List<ReviewVO> list1 = dao.selectReview(Integer.parseInt(pNo));
    
      List<ProductBoardVO> list = bDao.selectListByProductBoardNo(Integer.parseInt(pNo));
//      List<ProductBoardVO> list2 = bDao.;
      System.out.println(list.size());
      request.setAttribute("innerboard", list);
      request.setAttribute("review", list1);
      request.setAttribute("count", rCount);
        int pageNo = 1;
		int category = bDao.productselectcategory(Integer.parseInt(pNo));
		int totProduct = bDao.totProductList(category, null);
		List<ProductBoardVO> list2 = bDao.selectListByCategory(category, pageNo);
		switch(category) {
		case 1:
			request.setAttribute("categoryList", "코브 조명");
			break;
		case 2:
			request.setAttribute("categoryList", "스포트라이트");
			break;
		case 3:
			request.setAttribute("categoryList", "실링 라이트");
			break;
		case 4:
			request.setAttribute("categoryList", "샹들리에");
			break;
		case 5:
			request.setAttribute("categoryList", "다운 라이트");
			break;
		case 6:
			request.setAttribute("categoryList", "브래킷");
			break;
		case 7:
			request.setAttribute("categoryList", "플로어 스탠드");
			break;
		case 8:
			request.setAttribute("categoryList", "테이블 램프");
			break;
		case 9:
			request.setAttribute("categoryList", "플로어 램프");
			break;
		case 10:
			request.setAttribute("categoryList", "팬던트");
			break;
		case 11:
			request.setAttribute("categoryList", "풋 라이트");
			break;
		}
		request.setAttribute("totProduct", totProduct);
		request.setAttribute("productListByCategory", list);
      RequestDispatcher rd = request.getRequestDispatcher(outputUrl);
      rd.forward(request, response);
   }

}