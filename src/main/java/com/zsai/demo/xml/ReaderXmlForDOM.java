package com.zsai.demo.xml;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class ReaderXmlForDOM {

	public static void main(String args[]) {
		String xmlstr = "<?xml version=\"1.0\" encoding=\"GBK\"?>"
				+ "<GNNT>"
				+ "<REP name=\"ispassword\">"
				+ "<RESULT>"
				+ "<RETCODE>1</RETCODE>"
				+ "<MESSAGE>您本次登录地点和上次不一致,如果这不是您自己的主动行为,建议您及时修改密码!</MESSAGE>"
				+ "<MODULE_ID>7</MODULE_ID>"
				+ "<TYPE></TYPE>"
				+ "</RESULT>"
				+ "</REP>"
				+ "</GNNT>";
		long begin = System.currentTimeMillis();
		parse(xmlstr);
		long after = System.currentTimeMillis();
		System.out.println("DOM用时" + (after - begin) + "毫秒");
	}

	public static void parse(String protocolXML) {

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(protocolXML)));

			Element root = doc.getDocumentElement();
			NodeList books = root.getChildNodes();
			if (books != null) {
				for (int i = 0; i < books.getLength(); i++) {
					Node book = books.item(i);
					System.out.println("节点=" + book.getNodeName() + "\ttext=" + book.getFirstChild().getNodeValue());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
