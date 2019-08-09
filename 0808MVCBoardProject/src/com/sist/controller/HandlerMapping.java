package com.sist.controller;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.*;

public class HandlerMapping extends DefaultHandler {
	Map map = new HashMap();
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// qName(태그명), attributes(속성) : 어떤 태그에서, 어떤 속성을 가져올 것인지를 재정의
		try {
			if(qName.equals("bean")) {
				String id = attributes.getValue("id");
				String cls = attributes.getValue("class");
				
				Class clsName = Class.forName(cls);
				Object obj = clsName.newInstance(); // newInstance()의 반환형이 Object형
				
				map.put(id, obj);
			}
		} catch (Exception ex) {}
	}
	
}
