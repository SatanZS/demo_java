package com.zsai.demo.httpclient.test;

import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.OutputStreamWriter;  
import java.net.MalformedURLException;  
import java.net.URL;  
import java.net.URLConnection;  
  
public class HttpPostTest {  
    void testPost(String urlStr) {  
        try {  
            URL url = new URL(urlStr);  
            URLConnection con = url.openConnection();  
            con.setDoOutput(true);  
            con.setRequestProperty("Pragma:", "no-cache");  
            con.setRequestProperty("Cache-Control", "no-cache");  
            con.setRequestProperty("Content-Type", "text/xml");  
  
            OutputStreamWriter out = new OutputStreamWriter(con  
                    .getOutputStream());      
            String xmlInfo = getXmlInfo();  
            System.out.println("urlStr=" + urlStr);  
            System.out.println("xmlInfo=" + xmlInfo);  
            out.write(new String(xmlInfo.getBytes("ISO-8859-1")));  
            out.flush();  
            out.close();  
            BufferedReader br = new BufferedReader(new InputStreamReader(con  
                    .getInputStream()));  
            String line = "";  
            for (line = br.readLine(); line != null; line = br.readLine()) {  
                System.out.println(line);  
            }  
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
  
    private String getXmlInfo() {  
        StringBuilder sb = new StringBuilder();  
        sb.append("<?xml version=\"1.0\" encoding=\"gb2312\"?>");
        sb.append("<GNNT>");
        sb.append("<REQ name=\"isPassword\">");
        sb.append("<USER_ID>0011058976</USER_ID>");
        sb.append("<PASSWORD>a1111111</PASSWORD>");
        sb.append("<REGISTER_WORD>null</REGISTER_WORD>");
        sb.append("<VERSIONINFO>2.0</VERSIONINFO>");
        sb.append("<EC>null</EC>");
        sb.append("</REQ>");
        sb.append("</GNNT>");
        return sb.toString();  
    }  
  
    public static void main(String[] args) {  
        String url = "https://175.25.17.41:12915/tradeweb/httpXmlServlet";  
        new HttpPostTest().testPost(url);  
    }  
}  
