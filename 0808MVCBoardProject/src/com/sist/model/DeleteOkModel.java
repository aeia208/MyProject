package com.sist.model;

import javax.servlet.http.HttpServletRequest;

import com.sist.dao.*;

public class DeleteOkModel implements Model {
	@Override
	public String handlerRequest(HttpServletRequest request) throws Throwable {
		// 데이터 받기
		String pwd = request.getParameter("pwd");
		String no = request.getParameter("no");
		
		boolean bCheck = BoardDAO.boardDelete(Integer.parseInt(no), pwd);
		request.setAttribute("bCheck", bCheck);
		
		return "board/delete_ok.jsp";
	}
}
