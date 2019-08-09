package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public class DeleteModel implements Model {
	@Override
	public String handlerRequest(HttpServletRequest request) throws Throwable {
		// 데이터 받기 (게시물 번호)
		String no = request.getParameter("no");
		request.setAttribute("no", no);
		return "board/delete.jsp";
	}
}
