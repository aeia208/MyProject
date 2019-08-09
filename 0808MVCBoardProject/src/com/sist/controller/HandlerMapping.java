package com.sist.controller;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.*;

public class HandlerMapping extends DefaultHandler {
	Map map = new HashMap();
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// qName(�±׸�), attributes(�Ӽ�) : � �±׿���, � �Ӽ��� ������ �������� ������
		try {
			if(qName.equals("bean")) {
				String id = attributes.getValue("id");
				String cls = attributes.getValue("class");
				
				Class clsName = Class.forName(cls);
				Object obj = clsName.newInstance(); // newInstance()�� ��ȯ���� Object��
				
				map.put(id, obj);
			}
		} catch (Exception ex) {}
	}
	
}
