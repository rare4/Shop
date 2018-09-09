package com.teamontb.action;


import com.teamontb.board.action.*;
import com.teamontb.cart.action.*;
import com.teamontb.detail.action.DetailAction;
import com.teamontb.detail.action.ReviewWriteAction;
import com.teamontb.main.action.MainAction;
import com.teamontb.member.action.CheckPh;
import com.teamontb.member.action.Checkpwd;
import com.teamontb.member.action.MemberJoinAct;
import com.teamontb.member.action.MemberLogOutAct;
import com.teamontb.member.action.MemberLoginAct;
import com.teamontb.member.action.MemberModifyInfo;
import com.teamontb.member.action.MemberPageAct;
import com.teamontb.member.action.MemberPwdConfirmAct;
import com.teamontb.member.action.SalesStatusUpdateAct;
import com.teamontb.pboard.action.CategoryListAct;
import com.teamontb.pboard.action.Orderinsert;
import com.teamontb.pboard.action.ReviewListAct;
import com.teamontb.pboard.action.SalesInsert;
import com.teamontb.pboard.action.SearchProductAct;
import com.teamontb.pboard.action.SearchResultAct;

public class ActionFactory {
	private static ActionFactory instance = new ActionFactory();
	
	private ActionFactory() {}
	
	public static ActionFactory getInstance() {
		if (instance == null) instance = new ActionFactory();
		return instance;
	}
	
	public Action getProductAction(String product) {
		Action action = null;
		if (product.equals("cartlist")) {
			action = new Cartlist();
		}else if(product.equals("cartmodify")){
			action = new Carttmodify();
		}else if(product.equals("cartdelete")){
			action = new Cartdelete();
		}else if(product.equals("cartdeleteall")){
			action = new Cartalldelete();
		} else if(product.equals("listByCategory")) {
			action = new CategoryListAct();
		} else if(product.equals("salesStatusUpdate")) {
			action = new SalesStatusUpdateAct();
		} else if(product.equals("productDetailPage")) {
			action = new DetailAction();
		} else if(product.equals("searchProduct")) {
			action = new SearchProductAct();
		} else if(product.equals("searchResult")) {
			action = new SearchResultAct();
		} else if (product.equals("reviewshow")){
			action = new ReviewListAct();
		}else if (product.equals("cartinsert")){
			action = new Cartinsert();
		} else if (product.equals("order")){
			action = new Orderinsert();
		} else if(product.equals("Choosedelete")){
			action = new CartChoosedelete();
		} else if(product.equals("salesinsert")){
			action = new SalesInsert();
		} else if(product.equals("review_Write")) {
			action = new ReviewWriteAction();
		}
		
		return action;
	}
	
	public Action getMemberAction(String member) {
		Action action = null;
		if(member.equals("memLogin")){
			action=(MemberLoginAct) new MemberLoginAct();
		}else if(member.equals("memJoin")){
			action=(MemberJoinAct) new MemberJoinAct();
		} else if(member.equals("memLogOut")) {
			action=(MemberLogOutAct) new MemberLogOutAct();
		} else if(member.equals("memberPage")) {
			action=(MemberPageAct) new MemberPageAct();
		} else if(member.equals("confirmPwd")) {
			action=(MemberPwdConfirmAct) new MemberPwdConfirmAct();
		} else if(member.equals("modifyInfo")) {
			action=(MemberModifyInfo) new MemberModifyInfo();
		}else if(member.equals("checkPh")){
			action=(CheckPh) new CheckPh();
		}else if(member.equals("checkpwd")){
			action=(Checkpwd) new Checkpwd();
		}
		return action;
	}
	
	public Action getBoardAction(String board) {
		Action action = null;
		System.out.println("action factory :" + board);
		if(board.equals("board_list")) {
			action = new BoardListAction();
			System.out.println(action);
		}
		if(board.equals("board_detail")) {
			action = new BoardDetailAction();
		}
		if(board.equals("board_WriteForm")) {
			action = new BoardWriteFormAction();
		}
		if(board.equals("board_Write")) {
			action = new BoardWriteAction();
		}
		if(board.equals("board_UpdateForm")) {
			action = new BoardUpdateFormAction();
		}
		if(board.equals("board_Update")) {
			action = new BoardUpdateAction();
		}
		if(board.equals("board_Delete")) {
			action = new BoardDeleteAction();
		}
		
		System.out.println(action);
		return action;
	
	}
	
	public Action getMainAction(String main){
		Action action = null;
		System.out.println("action factory :" + main );
		if(main.equals("main")){
			action = new MainAction();
		}
		return action;
	}
}
