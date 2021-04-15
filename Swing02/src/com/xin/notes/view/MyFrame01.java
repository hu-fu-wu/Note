//注册界面
package com.xin.notes.view;

import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


import com.xin.notes.bean.User;
import com.xin.notes.controller.Myframe01controller;
import com.xin.notes.dao.SQL_dao;

public class MyFrame01 extends JFrame{
	JPanel panel = new JPanel();
	JLabel Label1 = new JLabel("用户名");
	JLabel Label2 = new JLabel("密码 ");
	JButton rbutton = new JButton("注册");
	JTextField yhm = new JTextField(16);
	JTextField mm = new JTextField(16);	
	
	public MyFrame01(String title)
	{
		super(title);
		this.setLocationRelativeTo(null);
		Container contentPane = getContentPane();
		panel.setLayout(new FlowLayout());
		
		panel.add(Label1);
		panel.add(yhm);
		panel.add(Label2);
		panel.add(mm);
		panel.add(rbutton);
		
		contentPane.add(panel);
		contentPane.setVisible(true);
	
		rbutton.addActionListener((e)->{
			boolean yes = Myframe01controller.onrButtonok(yhm, mm);
			if(yes) {
				JOptionPane.showMessageDialog(this, "注册成功");
				Myframe01controller.close(this);
				swingdemo.main(null);
			}
		});
		
	}	
	

	

	
	

	
}
