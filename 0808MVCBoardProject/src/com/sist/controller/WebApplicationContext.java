package com.sist.controller;
import java.util.*;
import java.io.*;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class WebApplicationContext {
	private Map map = new HashMap();
	
	public WebApplicationContext(String path) {
		try {
			SAXParserFactory spf = SAXParserFactory.newInstance(); // spf: 파서기를 만듬
			SAXParser sp = spf.newSAXParser(); // sp: 파서기
			HandlerMapping hm = new HandlerMapping();
			sp.parse(new File(path), hm);
			
			map = hm.map;			
		} catch (Exception ex) {}
	}
	
	public Object getBean(String id) { // Model찾기
		return map.get(id);
	}
}
