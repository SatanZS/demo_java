package com.zsai.demo.httpclient.test;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendAndGetXml {

	public static void main(String[] args) throws Exception {
		String rqestXml = "";
		String urls = "";

		try {
			rqestXml = "<?xml version=\"1.0\" encoding=\"gb2312\"?>" 
					+ "<GNNT>" 
					+ "<REQ name=\"isPassword\">"
					+ "<USER_ID>0011058976</USER_ID>" 
					+ "<PASSWORD>a1111111</PASSWORD>"
					+ "<REGISTER_WORD>null</REGISTER_WORD>" 
					+ "<VERSIONINFO>2.0</VERSIONINFO>" 
					+ "<EC>null</EC></REQ>"
					+ "</GNNT>";
			// 访问远程接接口
			urls = "https://175.25.17.41:12915/tradeweb/httpXmlServlet";
			System.out.println(rqestXml);
			System.out.println(urls);

		} catch (Exception e) {
			throw new Exception();
		}
		// 使用HttpURLConnection发送http请求
		byte[] xmlbyte = rqestXml.getBytes("UTF-8");
		URL url = new URL(urls);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5 * 1000);
		conn.setDoOutput(true);// 允许输出
		conn.setUseCaches(false);// 不使用Cache
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
		conn.setRequestProperty("Charset", "UTF-8");
		conn.setRequestProperty("Content-Length", String.valueOf(xmlbyte.length));
		conn.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
		DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());
		outStream.write(xmlbyte);// 发送xml数据
		outStream.flush();
		outStream.close();

		// 解析返回来的xml消息体
		byte[] msgBody = null;
		if (conn.getResponseCode() != 200)
			throw new RuntimeException("请求url失败");
		InputStream is = conn.getInputStream();// 获取返回数据

		byte[] temp = new byte[1024];
		int n = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((n = is.read(temp)) != -1) {
			bos.write(temp, 0, n);
		}
		msgBody = bos.toByteArray();
		bos.close();
		is.close();
		String returnXml = new String(msgBody, "UTF-8").trim();

		conn.disconnect();

		// 以下下游解析XML
		System.out.println(returnXml);
	}
}