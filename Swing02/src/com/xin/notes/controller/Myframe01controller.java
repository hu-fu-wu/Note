package com.xin.notes.controller;



import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.xin.notes.bean.User;
import com.xin.notes.dao.SQL_dao;

public class Myframe01controller {
	public static boolean onrButtonok(JTextField yhm,JTextField mm) {
		String str1 = yhm.getText();
		String str2 = mm.getText();
		boolean yes = SQL_dao.register(str1,str2);
		return yes;
	}
	
	public static boolean register(String yhm,String mm) {
		User user1 = new User();		
		user1.setUserName(yhm);
		user1.setPassword(mm);
		
		
		//把用户的账号密码传入数据库中
		boolean register = SQL_dao.register(user1.getUserName(),user1.getPassword());
		return register;
	}
	
	//关闭窗口方法
			public static void close(JFrame frame) {
				frame.dispose();
				
			}
}
