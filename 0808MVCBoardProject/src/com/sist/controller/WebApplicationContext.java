package com.sist.controller;
import java.util.*;
import java.io.*;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class WebApplicationContext {
	private Map map = new HashMap();
	
	public WebApplicationContext(String path) {
		try {
			SAXParserFactory spf = SAXParserFactory.newInstance(); // spf: �ļ��⸦ ����
			SAXParser sp = spf.newSAXParser(); // sp: �ļ���
			HandlerMapping hm = new HandlerMapping();
			sp.parse(new File(path), hm);
			
			map = hm.map;			
		} catch (Exception ex) {}
	}
	
	public Object getBean(String id) { // Modelã��
		return map.get(id);
	}
}
