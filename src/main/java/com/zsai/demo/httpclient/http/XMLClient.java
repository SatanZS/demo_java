package com.zsai.demo.httpclient.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

public class XMLClient
{

    private HttpClient client;

    public static void main(String[] args) throws Exception
    {
        XMLClient client = new XMLClient();
        String reqName = "isPassword";
        // 0000158795
        // 0011058976
        String account = "0000158795";
        String password = "a1111111";
        String result = client.sendXMLDataByPost("https://175.25.17.41:12915/tradeweb/httpXmlServlet",
                client.getXMLString(reqName, account, password));
        System.out.println(result);
    }

    // 获取XML
    public String getXMLString(String reqName, String account, String password)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"gb2312\"?>");
        sb.append("<GNNT>");
        sb.append("  <REQ name=\"" + reqName + "\">");
        sb.append("    <USER_ID>" + account + "</USER_ID>");
        sb.append("    <PASSWORD>" + password + "</PASSWORD>");
        sb.append("    <REGISTER_WORD>null</REGISTER_WORD>");
        sb.append("    <VERSIONINFO>2.0</VERSIONINFO>");
        sb.append("    <EC>null</EC>");
        sb.append("  </REQ>");
        sb.append("</GNNT>");
        // 返回String格式
        return sb.toString();
    }

    // 使用POST方法发送XML数据
    public String sendXMLDataByPost(String url, String xmlData) throws Exception
    {
        if (client == null)
        {
            client = new SSLClient();
        }
        HttpPost post = new HttpPost(url);
        // List<BasicNameValuePair> parameters = new ArrayList<>();
        // parameters.add(new BasicNameValuePair("xml", xmlData));
        // post.setEntity(new UrlEncodedFormEntity(parameters, "GBK"));
        post.setEntity(new StringEntity(xmlData, "GBK"));
        HttpResponse response = client.execute(post);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, "GBK");
        return result;
    }

}