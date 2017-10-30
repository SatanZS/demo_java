package com.zsai.demo.httpclient.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class mine {

	public static void post(String httpsUrl, String xmlStr) {
		HttpsURLConnection urlCon = null;
		try {
			urlCon = (HttpsURLConnection) (new URL(httpsUrl)).openConnection();
			urlCon.setDoInput(true);
			urlCon.setDoOutput(true);
			urlCon.setRequestMethod("POST");
			urlCon.setRequestProperty("Content-Length", String.valueOf(xmlStr.getBytes().length));
			urlCon.setUseCaches(false);
			urlCon.getOutputStream().write(xmlStr.getBytes("utf8"));
			urlCon.getOutputStream().flush();
			urlCon.getOutputStream().close();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String rqestXml = "<?xml version=\"1.0\" encoding=\"gb2312\"?>" + "<GNNT>" + "<REQ name=\"isPassword\">"
				+ "<USER_ID>0011058976</USER_ID>" + "<PASSWORD>a1111111</PASSWORD>"
				+ "<REGISTER_WORD>null</REGISTER_WORD>" + "<VERSIONINFO>2.0</VERSIONINFO>" + "<EC>null</EC></REQ>"
				+ "</GNNT>";
		String urls = "https://175.25.17.41:12915/tradeweb/httpXmlServlet";
		
		post(urls, rqestXml);
	}
}
