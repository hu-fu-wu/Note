package com.xin.notes.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.xin.notes.bean.User;
import com.xin.notes.controller.Myframe02controller;
import com.xin.notes.util.IOutil;
import com.xin.notes.util.Stringutil;


public class MyFrame02 extends JFrame implements ActionListener,MouseListener{
	String wenjianming;
	
	static String username;
	public static void get(User user) {
		 username = user.getUserName();
	 }
	

	//获取个人笔记并以表格形式显示
	JTable maintable = new JTable(20,1);
	
	
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JLabel label1 = new JLabel("用户名");
	JTextField textf1 = new JTextField(16);
	JButton button1 = new JButton("查询");
	JTable table1 = new JTable(10,1);
	JButton back1  = new JButton("返回主页面");
	
	JPanel panel3 = new JPanel();
	JLabel label2 = new JLabel("标题");
	JTextField textf2 = new JTextField(16);
	JButton button2 = new JButton("查询");
	JTable table2 = new JTable(10,1);
	JButton back2 = new JButton("返回主页面");
	public MyFrame02(String title) {
		super(title);
		this.setLocationRelativeTo(null);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		Container contentPane = getContentPane();
		contentPane.setLayout(new CardLayout());
		this.setSize(500,400);
		
		//添加菜单
		JMenuBar menubar = new JMenuBar();
		this.setJMenuBar(menubar);
		//菜单中
		JMenu userMenu = new JMenu("个人信息");
		JMenu newMenu = new JMenu("新建");
		JMenu searchMenu = new JMenu("搜索");
		menubar.add(userMenu);
		menubar.add(newMenu);
		menubar.add(searchMenu);
		//添加内层
			//个人信息
		JMenuItem usernamemenu = new JMenuItem("用户账号："+username);
		userMenu.add(usernamemenu);
			//新建
		JMenuItem renew = new JMenuItem("新建笔记");
		newMenu.add(renew);
			//搜索
		JMenuItem usersearch = new JMenuItem("按用户名搜索");
		JMenuItem titlesearch = new JMenuItem("按标题搜索");
		searchMenu.add(usersearch);
		searchMenu.add(titlesearch);
		
		//给各个菜单子项添加监听器
		renew.addActionListener(this);
		usersearch.addActionListener(this);
		titlesearch.addActionListener(this);
		renew.setActionCommand("renew");
		usersearch.setActionCommand("usersearch");
		titlesearch.setActionCommand("titlesearch");
		
	//Panel1层
		//给表格添加数据
		if(username.equals("123456")){
			Myframe02controller.filltable(maintable,IOutil.guanliyuanscan());
		}else {
			Myframe02controller.filltable(maintable,IOutil.mainuserscan(username));
		}

		
		//给表格添加鼠标监听
		maintable.addMouseListener(this);
		
		//添加个人笔记表格
		panel1.setLayout(new BorderLayout());
		panel1.add(maintable);
		panel1.setVisible(true);
	
	//Panel2层
		panel2.setLayout(new FlowLayout());
		panel2.add(back1);
		panel2.add(label1);
		panel2.add(textf1);
		panel2.add(button1);
		panel2.add(table1);
		table1.addMouseListener(this);
		button1.addActionListener(this);
		button1.setActionCommand("button1");
		back1.addActionListener(this);
		back1.setActionCommand("back1");
		panel2.setVisible(false);
		
	//Panel3层
		panel3.setLayout(new FlowLayout());
		panel3.add(back2);
		panel3.add(label2);
		panel3.add(textf2);
		panel3.add(button2);
		panel3.add(table2);
		table2.addMouseListener(this);
		button2.addActionListener(this);
		button2.setActionCommand("button2");
		back2.addActionListener(this);
		back2.setActionCommand("back2");
		panel3.setVisible(false);
		
		
		contentPane.add(panel1);
		contentPane.add(panel2);
		contentPane.add(panel3);
		
	}
	
	//对监听器添加事件
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("renew")) {
			swingdemo.MyFrame03();
		}else if(e.getActionCommand().equals("usersearch")){
			panel1.setVisible(false);
			panel2.setVisible(true);
			panel3.setVisible(false);
			
		}else if(e.getActionCommand().equals("titlesearch")) {
			panel1.setVisible(false);
			panel2.setVisible(false);
			panel3.setVisible(true);
		}else if(e.getActionCommand().equals("button1")) {
			String usersearch = textf1.getText();
			Vector<String> searchsetout1 = IOutil.search("c:/Note/public/"+usersearch);
			 Myframe02controller.filltable(table1, searchsetout1);
		}else if(e.getActionCommand().equals("button2")) {
			String titlesearch = textf2.getText();
			Vector<String> searchsetout2 = IOutil.search("c:/Note/public");
			Vector<String> searchsetout3 = new Vector<>(); 
			searchsetout3 = IOutil.filtersearch(titlesearch+".txt", searchsetout2);
			Myframe02controller.filltable(table2,searchsetout3);
		}else if(e.getActionCommand().equals("back1")) {
			panel1.setVisible(true);
			panel2.setVisible(false);
			panel3.setVisible(false);
		}else if(e.getActionCommand().equals("back2")) {
			panel1.setVisible(true);
			panel2.setVisible(false);
			panel3.setVisible(false);
		}
			
	}
	
	
	

	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(panel1.isVisible()) {
			int r=maintable.getSelectedRow();
			int c=maintable.getSelectedColumn();
			if(e.getClickCount()==1) {
				Object value= maintable.getValueAt(r, c);
				wenjianming = value.toString();
				if(username.equals("123456")) {
					swingdemo.MyFrame03();
					if(wenjianming.contains("public")) {
						MyFrame03.openpath(wenjianming,"public");
					}else if(wenjianming.contains("private")) {
						MyFrame03.openpath(wenjianming,"private");
					}
				}	else {
					System.out.println(wenjianming);
					String wenjianming1 = Stringutil.ClearBracket(wenjianming);
					System.out.println(wenjianming1);
					swingdemo.MyFrame03();
					if(wenjianming.contains("public")) {
						MyFrame03.openpath("c:/Note/public/"+username+"/"+wenjianming1,"public");
					}else if(wenjianming.contains("private")) {
						MyFrame03.openpath("c:/Note/private/"+username+"/"+wenjianming1,"private");
					}
				}
			}
		}else if(panel2.isVisible()){
			int r1=table1.getSelectedRow();
			int c1=table1.getSelectedColumn();
			if(e.getClickCount()==1) {
				Object value1= table1.getValueAt(r1, c1);
				wenjianming = value1.toString();
				System.out.println(wenjianming);
				swingdemo.MyFrame03();
				MyFrame03.openpath("c:/Note/public/"+username+"/"+wenjianming,"public");
			}
		}else if(panel3.isVisible()) {
			int r2=table2.getSelectedRow();
			int c2=table2.getSelectedColumn();
			if(e.getClickCount()==1) {
				Object value2= table2.getValueAt(r2, c2);
				wenjianming = value2.toString();
				System.out.println(wenjianming);
				swingdemo.MyFrame03();
				MyFrame03.openpath("c:/Note/public/"+username+"/"+wenjianming,"public");
			}
		}
	}
		
			
		
		
	




	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	
}
