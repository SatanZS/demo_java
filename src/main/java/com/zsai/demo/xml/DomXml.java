package com.zsai.demo.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class DomXml {
	public static void main(String[] args) {
		String xmlstr = "<?xml version=\"1.0\" encoding=\"GBK\"?><GNNT><REP name=\"ispassword\"><RESULT><RETCODE>-9</RETCODE><MESSAGE>其他异常！</MESSAGE><MODULE_ID>7</MODULE_ID><TYPE></TYPE></RESULT></REP></GNNT>";
		String result = "";
		Boolean status = false;
		try {
			Document doc = DocumentHelper.parseText(xmlstr);
			Element gnnt_el = doc.getRootElement();
			Element req_el = gnnt_el.element("REP");
			Element result_el = req_el.element("RESULT");
			Element retcode_el = result_el.element("RETCODE");
			switch (retcode_el.getTextTrim()) {
			case "1":
				status = true;
				result = "验证成功";
				break;
			case "-1":
				result = "交易代码不存在！";
				break;
			case "-2":
				result = "交易密码输入有误！";
				break;
			case "-3":
				result = "禁止登陆！";
				break;
			case "-4":
				result = "Key盘验证错误！";
				break;
			case "-5":
				result = "其它异常！";
				break;
			case "-6":
				result = "交易板块被禁止！";
				break;
			case "-7":
				result = "au没有此用户！";
				break;
			case "-8":
				result = "没有板块权限！";
				break;
			case "-201":
				result = "RMI连接失败";
				break;
			case "-202":
				result = "RMI调用出错";
				break;
			case "-203":
				result = "未知异常！";
				break;
			case "sessionID":
				result = "其他异常！";
				break;
			default:
				result = "其他异常！";
				break;
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		System.out.println(status);
		System.out.println(result);
	}
}
