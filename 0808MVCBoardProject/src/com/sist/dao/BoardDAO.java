package com.sist.dao;
import java.io.*;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class BoardDAO {
	private static SqlSessionFactory ssf;
	
	static {
		// 초기화 블럭 => XML을 파싱
		try {
			// XML읽기
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	// 목록 출력
	public static List<BoardVO> boardListData(Map map){
		List<BoardVO> list = new ArrayList<BoardVO>();
		// DBCP => 반환(==닫기)
		SqlSession session = ssf.openSession(); // getConnection()
		list = session.selectList("boardListData", map);
		session.close(); // 반환 (disConnection())
		return list;
	}
	
	// 데이터 입력
	public static void boardInsert(BoardVO vo) {
		SqlSession session = ssf.openSession(true);
		session.insert("boardInsert", vo);
		session.close();
	}
	
	// 상세보기
	static public BoardVO boardDetailData(int no) {
		BoardVO vo = new BoardVO();
		SqlSession session = ssf.openSession();
		session.update("hitIncrement", no);
		session.commit();
		vo = session.selectOne("boardDetailData", no);
		session.close();
		return vo;
	}
	
	// 총페이지
	public static int boardTotalPage() {
		int total = 0;
		// 오라클 연결
		SqlSession session = ssf.openSession();
		// 데이터값 가져오기
		total = session.selectOne("boardTotalPage");
		// 반환
		session.close();
		return total;
	}
	
	// 수정할 데이터 읽기
	public static BoardVO boardUpdateData(int no) {
		BoardVO vo = new BoardVO();
		// 오라클 연결
		SqlSession session = ssf.openSession();
		// 데이터값 가져오기
		vo = session.selectOne("boardDetailData", no); // mapper id 재사용 가능
		// 반환
		session.close();
		return vo;
	}
	
	// 수정하기
	public static boolean boardUpdate(BoardVO vo) {
		boolean bCheck = false;
		// 오라클 연결
		SqlSession session = ssf.openSession();
		// DB에 있는 비밀번호 읽기
		String db_pwd = session.selectOne("boardGetPwd", vo.getNo());
		// 데이터 수정
		if (db_pwd.equals(vo.getPwd())) {
			bCheck = true;
			session.update("boardUpdate", vo);
			session.commit();
		}
		// 반환
		session.close();
		return bCheck;
	}
	
	// 삭제하기
	public static boolean boardDelete(int no, String pwd) {
		boolean bCheck = false;
		// 오라클 연결
		SqlSession session = ssf.openSession();
		// DB에 있는 비밀번호 읽기
		String db_pwd = session.selectOne("boardGetPwd", no);
		
		// 데이터 수정
		if (db_pwd.equals(pwd)) {
			bCheck = true;
			session.update("boardDelete", no);
			session.commit();
		}
		// 반환
		session.close();
		return bCheck;
	}
	
	// 찾기
	public static List<BoardVO> boardFineData(Map map) {
		List<BoardVO> list = new ArrayList<BoardVO>();
		// 오라클 연결
		SqlSession session = ssf.openSession();
		// 데이터 읽어오기
		list = session.selectList("boardFindData", map);
		// 반환
		session.close();
		return list;
	}
}
