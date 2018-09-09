package com.teamontb.pboard.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.teamontb.action.Action;
import com.teamontb.cart.dao.CartDAO;
import com.teamontb.member.dao.MemberDAO;
import com.teamontb.member.dto.MemberVO;
import com.teamontb.pboard.dao.ProductBoardDAO;
import com.teamontb.product.dto.SalesStatusVO;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SalesInsert implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8"); 
		HttpSession session = request.getSession();		
		MemberVO mvo = (MemberVO)session.getAttribute("loginmember");
		String outuel = "consumer/login.jsp";
		MemberDAO mdao = MemberDAO.getInstance();
		CartDAO CDao = CartDAO.getInstance();
		if (mvo == null){
			
			RequestDispatcher rd = request.getRequestDispatcher(outuel);
			rd.forward(request, response);
		}else{
			String userid = mvo.getMemberEmail();
			ProductBoardDAO pdao = ProductBoardDAO.getInstance();
			String productno[] = request.getParameterValues("option");
			String ordercount[] = request.getParameterValues("count");
			String ordermoney[] = request.getParameterValues("order");
			String deliveryname = request.getParameter("deliveryname"); 
			String deliveryaddress = request.getParameter("roadFullAddr2");
			String deliverypostno = request.getParameter("zipNo2");
			String deliveryphoneno = request.getParameter("phone");
			String shippingfee2 = request.getParameter("shippingfee2");
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
			String orderNo = userid.split("@")[0] + sdf.format(date.getTime());
			System.out.println(orderNo);
		int deliveryprice;
		if(shippingfee2.equals("")){
			deliveryprice = 0;	
		}else{
			deliveryprice = Integer.parseInt(shippingfee2);
		}
		int usepoint = Integer.parseInt(request.getParameter("usepoint"));
		if(usepoint != 0){
			mdao.insertusePoint(userid, usepoint);
			for(int i = 0 ; i< productno.length; i++){
				if(i==0){
				pdao.insertorder(productno[i], userid, ordermoney[i], usepoint,"포인트사용 ",ordercount[i], "입금 완료",deliveryprice,deliveryname,deliveryphoneno, deliverypostno, deliveryaddress,orderNo);
				CDao.deleteBasket(userid,productno[i]);
				}else{
				pdao.insertorder(productno[i], userid, ordermoney[i], 0,"포인트사용 ",ordercount[i], "입금 완료",deliveryprice,deliveryname,deliveryphoneno, deliverypostno, deliveryaddress,orderNo);
				CDao.deleteBasket(userid,productno[i]);
				}
			}
		}else{
			for(int i = 0 ; i< productno.length; i++){
				pdao.insertorder(productno[i], userid, ordermoney[i], usepoint,"세일행사",ordercount[i], "입금 완료",deliveryprice,deliveryname,deliveryphoneno, deliverypostno, deliveryaddress,orderNo);
				CDao.deleteBasket(userid,productno[i]);
				}	
		}
		List<SalesStatusVO> list = pdao.selectPurchasedProduct(userid, orderNo);
//		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
//		Calendar cal = Calendar.getInstance();
//		String today = null;
//		today = formatter.format(cal.getTime());
//		Timestamp ts = Timestamp.valueOf(today);
//		System.out.println( " Timestamp : " + ts);
//		request.setAttribute("", salesStatus);
		outuel = "product/productfinal.jsp";
		request.setAttribute("orderList", list);
		request.setAttribute("orderNo", orderNo);
		RequestDispatcher rd = request.getRequestDispatcher(outuel);
		rd.forward(request, response);
		}
	}

}
