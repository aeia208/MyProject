package com.sist.model;

import javax.servlet.http.HttpServletRequest;

import com.sist.dao.*;

public class UpdateOkModel implements Model {
	@Override
	public String handlerRequest(HttpServletRequest request) throws Throwable {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception ex) {}
		
		// 데이터 받기
		String name = request.getParameter("name");
		String no = request.getParameter("no");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		
		BoardVO vo = new BoardVO();
		vo.setName(name);
		vo.setNo(Integer.parseInt(no));
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		boolean bCheck = BoardDAO.boardUpdate(vo);
		
		request.setAttribute("bCkeck", bCheck);
		
		if (bCheck) {
			request.setAttribute("no", no);
		}
		
		return "board/update_ok.jsp";
	}
}
