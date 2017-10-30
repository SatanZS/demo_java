package com.zsai.demo.locale;

/**
     * File name: i18nDemo.java
     * Author: Jack, at http://bbs.whnet.edu.cn, Java discuss board.
     * Email: greatjava@sina.com
     * Description: Shows a Dialog with a "OK" button and "File"/"Exit" menu.
     *              This program made some modifications to fit the requestion of i18n
     */
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class i18nDemo {
	public static void main(String args[]) throws Exception {
		ResourceBundle res;
		if (args.length < 1) {
			// use current locale at machine to get the resource bundle.
			res = ResourceBundle.getBundle("Res");
		} else {
			res = ResourceBundle.getBundle("Res", new Locale(args[0], args[1]));
		}
		JDialog dialog = new JDialog();
		JButton btOK = new JButton();
		JMenuBar menuBar = new JMenuBar();
		JMenu menuFile = new JMenu();
		JMenuItem menuExit = new JMenuItem();
		Font font = new Font(res.getString("FontName"), Font.PLAIN, 12);
		btOK.setText(res.getString("OKText"));
		menuFile.setText(res.getString("FileMenuText"));
		menuExit.setText(res.getString("FileExitMenuText"));
		dialog.setTitle(res.getString("DialogTitle"));
		btOK.setFont(font);
		menuFile.setFont(font);
		menuExit.setFont(font);
		menuBar.add(menuFile);
		menuFile.add(menuExit);
		dialog.getContentPane().setLayout(new FlowLayout());
		dialog.getContentPane().add(btOK);
		dialog.getContentPane().add(menuBar);
		dialog.setSize(200, 100);
		dialog.setModal(true);
		dialog.show();
		System.exit(0);
	}
}