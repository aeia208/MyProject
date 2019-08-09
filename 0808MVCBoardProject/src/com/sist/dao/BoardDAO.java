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
		// �ʱ�ȭ �� => XML�� �Ľ�
		try {
			// XML�б�
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	// ��� ���
	public static List<BoardVO> boardListData(Map map){
		List<BoardVO> list = new ArrayList<BoardVO>();
		// DBCP => ��ȯ(==�ݱ�)
		SqlSession session = ssf.openSession(); // getConnection()
		list = session.selectList("boardListData", map);
		session.close(); // ��ȯ (disConnection())
		return list;
	}
	
	// ������ �Է�
	public static void boardInsert(BoardVO vo) {
		SqlSession session = ssf.openSession(true);
		session.insert("boardInsert", vo);
		session.close();
	}
	
	// �󼼺���
	static public BoardVO boardDetailData(int no) {
		BoardVO vo = new BoardVO();
		SqlSession session = ssf.openSession();
		session.update("hitIncrement", no);
		session.commit();
		vo = session.selectOne("boardDetailData", no);
		session.close();
		return vo;
	}
	
	// ��������
	public static int boardTotalPage() {
		int total = 0;
		// ����Ŭ ����
		SqlSession session = ssf.openSession();
		// �����Ͱ� ��������
		total = session.selectOne("boardTotalPage");
		// ��ȯ
		session.close();
		return total;
	}
	
	// ������ ������ �б�
	public static BoardVO boardUpdateData(int no) {
		BoardVO vo = new BoardVO();
		// ����Ŭ ����
		SqlSession session = ssf.openSession();
		// �����Ͱ� ��������
		vo = session.selectOne("boardDetailData", no); // mapper id ���� ����
		// ��ȯ
		session.close();
		return vo;
	}
	
	// �����ϱ�
	public static boolean boardUpdate(BoardVO vo) {
		boolean bCheck = false;
		// ����Ŭ ����
		SqlSession session = ssf.openSession();
		// DB�� �ִ� ��й�ȣ �б�
		String db_pwd = session.selectOne("boardGetPwd", vo.getNo());
		// ������ ����
		if (db_pwd.equals(vo.getPwd())) {
			bCheck = true;
			session.update("boardUpdate", vo);
			session.commit();
		}
		// ��ȯ
		session.close();
		return bCheck;
	}
	
	// �����ϱ�
	public static boolean boardDelete(int no, String pwd) {
		boolean bCheck = false;
		// ����Ŭ ����
		SqlSession session = ssf.openSession();
		// DB�� �ִ� ��й�ȣ �б�
		String db_pwd = session.selectOne("boardGetPwd", no);
		
		// ������ ����
		if (db_pwd.equals(pwd)) {
			bCheck = true;
			session.update("boardDelete", no);
			session.commit();
		}
		// ��ȯ
		session.close();
		return bCheck;
	}
	
	// ã��
	public static List<BoardVO> boardFineData(Map map) {
		List<BoardVO> list = new ArrayList<BoardVO>();
		// ����Ŭ ����
		SqlSession session = ssf.openSession();
		// ������ �о����
		list = session.selectList("boardFindData", map);
		// ��ȯ
		session.close();
		return list;
	}
}
