package com.zsai.demo.xml;

import java.io.StringReader;
import java.util.Stack;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SAX解析XML 查看事件调用
 *
 */
public class SaxTest1 {

	public static void main(String[] args) throws Exception {
		// step 1: 获得SAX解析器工厂实例
		SAXParserFactory factory = SAXParserFactory.newInstance();

		// step 2: 获得SAX解析器实例
		SAXParser parser = factory.newSAXParser();

		// step 3: 开始进行解析
		// 传入待解析的文档的处理器
		String xmlstr = "<?xml version=\"1.0\" encoding=\"GBK\"?><GNNT><REP name=\"ispassword\"><RESULT><RETCODE>-9</RETCODE><MESSAGE>其他异常！</MESSAGE><MODULE_ID>7</MODULE_ID><TYPE></TYPE></RESULT></REP></GNNT>";
		StringReader srReader = new StringReader(xmlstr);
		InputSource is = new InputSource(srReader);
		MyHandler1 dh = new MyHandler1();
		parser.parse(is, dh);
	}
}

class MyHandler1 extends DefaultHandler {
	// 使用栈这个数据结构来保存
	private Stack<String> stack = new Stack<String>();

	@Override
	public void startDocument() throws SAXException {
		System.out.println("start document -> parse begin");
	}

	@Override
	public void endDocument() throws SAXException {

		System.out.println("end document -> parse finished");
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		System.out.println("start element-----------");
		System.out.println("    localName: " + localName);
		System.out.println("    qName: " + qName);

	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		System.out.println("characters-----------");
		// System.out.println(" ch: " + Arrays.toString(ch) );
		System.out.println("    ch: " + ch);
		System.out.println("    start: " + start);
		System.out.println("    length: " + length);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println("end element-----------");

		System.out.println("    localName: " + localName);
		System.out.println("    qName: " + qName);

	}
}