package com.teamontb.cart.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.teamontb.action.Action;
import com.teamontb.cart.dao.CartDAO;
import com.teamontb.cart.dto.CartVO;
import com.teamontb.member.dto.MemberVO;

public class Cartinsert implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8"); 
		CartDAO CDao = CartDAO.getInstance();
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginmember");
		String userid = mvo.getMemberEmail();
		String[] options = request.getParameterValues("option");//스트링 배열을 받고..
		String[] counts = request.getParameterValues("count");//스트링 배열을 받고..
		int cartselect = 0;
		PrintWriter pw= response.getWriter();
		
		for(int i =0; i<counts.length;i++){
			if(CDao.selectcart(userid,  options[i])) // 있으면 true 없으면 false 
			{
				cartselect++;
			}
			else if(CDao.selectcart(userid,  options[i]) == false){
				CDao.insertcart(userid, options[i], counts[i]);	
			}
		}
		if(cartselect>0){
			pw.print("var gocart = confirm('이미 장바구니에 있는 아이템을 제외하고 추가하였습니다..                확인하러가시겠습니까?'); ");
			pw.flush();
			pw.close();
		}else{
			pw.print("var gocart = confirm('장바구니에 추가했습니다. 확인하러가시겠습니까?');");
			pw.flush();
			pw.close();
		}
	}

}
