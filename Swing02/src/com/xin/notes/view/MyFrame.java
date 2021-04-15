//登录界面
package com.xin.notes.view;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.xin.notes.bean.User;
import com.xin.notes.controller.Myframecontroller;
import com.xin.notes.dao.SQL_dao;

public class MyFrame extends JFrame{
	JPanel panel = new JPanel();
	JLabel Label1 = new JLabel("用户名");
	JLabel Label2 = new JLabel("密码 ");
	JButton lbutton = new JButton("登录 ");
	JButton rbutton = new JButton("注册");
	static JTextField yhm = new JTextField(16);
	static JPasswordField mm = new JPasswordField(16);	
	
	public MyFrame(String title) {
		super(title);
		this.setLocationRelativeTo(null);
		//内容面板
		Container contentPane = getContentPane();
		panel.setLayout(new FlowLayout());
		
		//添加控件
		panel.add(Label1);
		panel.add(yhm);
		panel.add(Label2);
		panel.add(mm);
		panel.add(lbutton);
		panel.add(rbutton);
		
		contentPane.add(panel);
		contentPane.setVisible(true);
		//添加登录按钮事件
		lbutton.addActionListener( (e)->{
			boolean yes = Myframecontroller.onlButtonok(yhm,mm);
			if(yes) {
				JOptionPane.showMessageDialog(this, "登录成功");
				Myframecontroller.close(this);
				swingdemo.MyFrame02();
				
			}else {
				JOptionPane.showMessageDialog(this, "登录失败，请检查用户名和密码");
			}
				}
		);
		//添加注册按钮事件
		rbutton.addActionListener( (e)->{
			Myframecontroller.onrButtonok(this);
		
		}
		);
		
	}
	
	
	
	
	
	
	
	
}
	


	

