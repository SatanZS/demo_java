package com.zsai.demo.locale;

/**
    * File name: Res_en_US.java
    * Author: Jack, at http://bbs.whnet.edu.cn, Java discuss board.
    * Email: greatjava@sina.com
    * Description: Resource file for i18nDemo.java
    */
import java.util.*;

public class Res_en_US extends java.util.ListResourceBundle {
	static final Object[][] contents = new String[][] { { "OKText", "OK" }, { "FontName", "Dialoginput" },
			{ "FileMenuText", "File" }, { "FileExitMenuText", "Exit" }, { "DialogTitle", "Demo Dialog" } };

	public Object[][] getContents() {
		return contents;
	}
}