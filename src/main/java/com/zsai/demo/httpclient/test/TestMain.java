package com.zsai.demo.httpclient.test;

//�Խӿڽ��в���
public class TestMain
{
    // private String url = "https://175.25.17.40:15915/";
    private String url = "https://175.25.51.7:16915/";
    private String charset = "gbk";
    private HttpClientUtil httpClientUtil = null;

    public TestMain()
    {
        httpClientUtil = new HttpClientUtil();
    }

    public void test()
    {
        String rqestXml = "<?xml version=\"1.0\" encoding=\"gb2312\"?>" + "<GNNT>" + "<REQ name=\"isPassword\">"
                + "<USER_ID>0011058976</USER_ID>" + "<PASSWORD>a1111111</PASSWORD>"
                + "<REGISTER_WORD>null</REGISTER_WORD>" + "<VERSIONINFO>2.0</VERSIONINFO>" + "<EC>null</EC></REQ>"
                + "</GNNT>";
        String urls = "https://175.25.17.41:12915/tradeweb/httpXmlServlet";

        String httpOrgCreateTest = url + "tradeweb/httpXmlServlet";
        String str = "<?xml version=\"1.0\" encoding=\"gb2312\"?><GNNT><REQ name=\"logon\"><USER_ID>10015</USER_ID><PASSWORD>a111111</PASSWORD><REGISTER_WORD>201604250815217631001571988</REGISTER_WORD><VERSIONINFO></VERSIONINFO><EC>4820267d782c8ddea72554f2cdf658c6</EC></REQ></GNNT>";
        System.out.println(getValueByTagName(str, "REGISTER_WORD"));
        String httpOrgCreateTestRtn = httpClientUtil.doPost(urls, rqestXml, charset);
        System.out.println("result:" + httpOrgCreateTestRtn);
    }

    public static void main(String[] args)
    {
        TestMain main = new TestMain();
        main.test();
    }

    private String getValueByTagName(String xml, String tagName)
    {
        String tagValue = "";
        String replaceXml = xml.replaceAll("</", "<");
        String[] result = replaceXml.split("<" + tagName + ">");
        if (result.length > 1)
        {
            tagValue = result[1];
        }
        if (("COMMODITY_ID".equals(tagName)) && (tagValue != null) && (tagValue.length() > 2))
        {
            tagValue = tagValue.substring(2);
        }
        return tagValue;
    }
}