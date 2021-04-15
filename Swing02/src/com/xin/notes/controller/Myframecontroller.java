package com.xin.notes.controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.xin.notes.bean.User;
import com.xin.notes.dao.SQL_dao;
import com.xin.notes.view.MyFrame02;
import com.xin.notes.view.MyFrame03;
import com.xin.notes.view.swingdemo;

public class Myframecontroller{
	//点登录，调用登录方法，若正确，显示登录成功并打开记事本，若错误，显示登录失败
	public static boolean onlButtonok(JTextField yhm,JPasswordField mm) {
		String str1 = yhm.getText();
		String str2 = mm.getText();
		boolean yes = login(str1,str2);
		return yes;
	}
	
	
	//登录方法，传入用户名和密码
		public static boolean login(String yhm,String mm) {
			
			//让用户输入登录账号，密码
			User user2 = new User();
			user2.setUserName(yhm);
			user2.setPassword(mm);	
			MyFrame02.get(user2);
			MyFrame03.get(user2);
			//判断账号密码是否正确
			boolean login = SQL_dao.login(user2.getUserName(), user2.getPassword());
			return login;
		}
		
		//点击注册，关闭登录窗口，打开注册窗口
		public static void onrButtonok(JFrame frame) {
			close(frame);
			swingdemo.MyFrame01();
		}
		
		//关闭窗口方法
		public static void close(JFrame frame) {
			frame.dispose();
			
		}
	
}
