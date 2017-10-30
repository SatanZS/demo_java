package com.zsai.demo.xml;

import java.io.StringReader;
import java.util.HashMap;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ReaderXmlForSAX {

	public static HashMap<String, String> strr = null;

	public static void parse(String protocolXML) {
		try {
			SAXParserFactory saxfac = SAXParserFactory.newInstance();
			SAXParser saxparser = saxfac.newSAXParser();
			TestSAX tsax = new TestSAX();
			saxparser.parse(new InputSource(new StringReader(protocolXML)), tsax);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getXMLString(String reqName, String account, String password) {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"gb2312\"?>");
		sb.append("<GNNT>");
		sb.append("<REQ name=\"" + reqName + "\">");
		sb.append("<USER_ID>" + account + "</USER_ID>");
		sb.append("<PASSWORD>" + password + "</PASSWORD>");
		sb.append("<REGISTER_WORD>null</REGISTER_WORD>");
		sb.append("<VERSIONINFO>2.0</VERSIONINFO>");
		sb.append("<EC>null</EC>");
		sb.append("</REQ>");
		sb.append("</GNNT>");
		// 返回String格式
		return sb.toString();
	}

	public static void main(String args[]) {
		String reqName = "isPassword";
		String account = "0011058976";
		String password = "a1111111";

		long begin = System.currentTimeMillis();
		parse(getXMLString(reqName, account, password));
		long after = System.currentTimeMillis();
		System.out.println("DOM用时" + (after - begin) + "毫秒");
	}
}

class TestSAX extends DefaultHandler {

	private StringBuffer buf;
	private String str;

	public TestSAX() {
		super();
	}

	public void startDocument() throws SAXException {
		buf = new StringBuffer();
		System.out.println("*******开始解析XML*******");
	}

	public void endDocument() throws SAXException {
		System.out.println("*******XML解析结束*******");
	}

	public void endElement(String namespaceURI, String localName, String fullName) throws SAXException {
		str = buf.toString();
		System.out.println("节点=" + fullName + "\tvalue=" + buf + " 长度=" + buf.length());
		System.out.println();
		buf.delete(0, buf.length());
	}

	public void characters(char[] chars, int start, int length) throws SAXException {
		// 将元素内容累加到StringBuffer中
		buf.append(chars, start, length);
	}

}