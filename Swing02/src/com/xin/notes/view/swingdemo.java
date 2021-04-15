package com.xin.notes.view;

import javax.swing.JFrame;



public class swingdemo {
	private static void createGUI()
	{
		// JFrame指一个窗口，构造方法的参数为窗口标题
		MyFrame frame = new MyFrame("登录");
		
		// 当关闭窗口时，退出整个程序 (不懂的话没事，照抄即可，这一行不是重点)
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		
		// 设置窗口的其他参数，如窗口大小
		frame.setSize(250, 200);
		
		// 显示窗口
		frame.setVisible(true);
	}
	
	private static void createGUI01()
	{
		MyFrame01 frame = new MyFrame01("注册");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(250, 200);
		frame.setVisible(true);
	}
	
	private static void createGUI02()
	{
		MyFrame02 frame = new MyFrame02("我的笔记");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		frame.setVisible(true);		
		
	}
	
	private static void createGUI03()
	{
		MyFrame03 frame = new MyFrame03("新建笔记");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		frame.setVisible(true);		
		
	}
	
	public static void main(String[] args)
	{
		// 此段代码间接地调用了 createGUI()，具体原理在 Swing高级篇 里讲解
		// 初学者先照抄此代码框架即可
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				createGUI();
			}
		});

	}
	
	public static void MyFrame01() {
		{
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run()
				{
					createGUI01();
				}
			});

		}
	}
	
	public static void MyFrame02() {
		{
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run()
				{
					createGUI02();
				}
			});

		}
	}
	
	public static void MyFrame03() {
		{
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run()
				{
					createGUI03();
				}
			});

		}
	}
	
	

}
