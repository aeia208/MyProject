package com.sist.model;
import com.sist.dao.*;
import javax.servlet.http.HttpServletRequest;

public class UpdateModel implements Model {
	@Override
	public String handlerRequest(HttpServletRequest request) throws Throwable {
		// 데이터 받기 (게시물 번호)
		String no = request.getParameter("no");
		BoardVO vo = BoardDAO.boardUpdateData(Integer.parseInt(no));
		request.setAttribute("vo", vo);
		// update.jsp로 request에 추가된 값을 전송함
		return "board/update.jsp";
	}
}
