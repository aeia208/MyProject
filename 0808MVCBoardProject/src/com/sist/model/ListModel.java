package com.sist.model;

import java.util.*;
import com.sist.dao.*;
import javax.servlet.http.HttpServletRequest;

public class ListModel implements Model {
	@Override
	public String handlerRequest(HttpServletRequest request) throws Throwable {
		// 목록 받기
		// 페이지 받기
		String page = request.getParameter("page");
		if(page==null)
			page = "1";
		int curpage = Integer.parseInt(page);
		int rowSize = 10;
		int start = (curpage * rowSize) - (rowSize - 1);
		int end = curpage * rowSize;
		int total = BoardDAO.boardTotalPage();
		
		// map에 저장
		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List<BoardVO> list = BoardDAO.boardListData(map);
		
		// list.jsp로 전송
		request.setAttribute("list", list);
		request.setAttribute("totalpage", total);
		request.setAttribute("curpage", page);
		return "board/list.jsp";
	}
}
