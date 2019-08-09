package com.sist.model;
import com.sist.dao.*;
import javax.servlet.http.HttpServletRequest;

public class UpdateModel implements Model {
	@Override
	public String handlerRequest(HttpServletRequest request) throws Throwable {
		// ������ �ޱ� (�Խù� ��ȣ)
		String no = request.getParameter("no");
		BoardVO vo = BoardDAO.boardUpdateData(Integer.parseInt(no));
		request.setAttribute("vo", vo);
		// update.jsp�� request�� �߰��� ���� ������
		return "board/update.jsp";
	}
}
