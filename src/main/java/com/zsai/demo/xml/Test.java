package com.zsai.demo.xml;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Test {

	public static void main(String[] args) {

		// step 1: 获得SAX解析器工厂实例
		SAXParserFactory factory = SAXParserFactory.newInstance();

		try {
			// step 2: 获得SAX解析器实例
			SAXParser parser = factory.newSAXParser();

			String xmlstr = "<?xml version=\"1.0\" encoding=\"GBK\"?><GNNT><REP name=\"ispassword\"><RESULT><RETCODE>-9</RETCODE><MESSAGE>其他异常！</MESSAGE><MODULE_ID>7</MODULE_ID><TYPE></TYPE></RESULT></REP></GNNT>";
			StringReader srReader = new StringReader(xmlstr);
			InputSource is = new InputSource(srReader);
			MyHandler dh = new MyHandler();
			parser.parse(is, dh);

			System.out.println(dh.getXmlmap());

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

/**
 * @author Administrator
 *
 */
class MyHandler extends DefaultHandler {

	private HashMap<String, String> xmlmap;
	private StringBuffer buf;
	private String str;

	public MyHandler() {
		super();
	}

	@Override
	public void setDocumentLocator(Locator locator) {
		super.setDocumentLocator(locator);
	}

	@Override
	public void startDocument() throws SAXException {
		buf = new StringBuffer();
		System.out.println("开始");
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("结束");
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		str = buf.toString();
		System.out.println(qName);
		System.out.println(buf);
		if (qName.equals("RETCODE")) {
			xmlmap.put(qName, buf.toString());
		}
		if (qName.equals("MESSAGE")) {
			xmlmap.put(qName, buf.toString());
		}
		buf.delete(0, buf.length());
	}

	@Override
	public void characters(char[] chars, int start, int length) throws SAXException {
		// 将元素内容累加到StringBuffer中
		buf.append(chars, start, length);
	}

	public HashMap<String, String> getXmlmap() {
		return xmlmap;
	}

	public void setXmlmap(HashMap<String, String> xmlmap) {
		this.xmlmap = xmlmap;
	}

}