package com.xin.notes.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.commons.io.FileUtils;

import com.xin.notes.bean.User;

public class MyFrame03 extends JFrame implements ActionListener{
	String a="01";
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	static JTextArea TextArea = new JTextArea();
	static JTextField TextField = new JTextField(16);
	JLabel label = new JLabel("标题");
	static JCheckBox shuxing = new JCheckBox("公开");
	
	public MyFrame03(String title)
	{
		super(title);
		this.setLocationRelativeTo(null);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		panel1.setLayout(new FlowLayout());

		
		//添加菜单
		JMenuBar menubar = new JMenuBar();
		this.setJMenuBar(menubar);
		//菜单  文件
		JMenu fileMenu = new JMenu("文件");
		menubar.add(fileMenu);
		JMenuItem Renew = new JMenuItem("新建");
		JMenuItem Open = new JMenuItem("打开");
		JMenuItem Delete = new JMenuItem("删除");
		JMenuItem Save = new JMenuItem("保存");	
		
		fileMenu.add(Renew);
		fileMenu.add(Open);
		fileMenu.add(Save);
		fileMenu.add(Delete);
		
		//给文件菜单注册监听
		Renew.addActionListener(this);
		Open.addActionListener(this);
		Save.addActionListener(this);
		Delete.addActionListener(this);
		Renew.setActionCommand("Renew");
		Open.setActionCommand("Open");
		Save.setActionCommand("Save");
		Delete.setActionCommand("Delete");
		
		
		//添加输入框，标题栏
		panel1.add(label);
		
		panel1.add(TextField);
		
		panel1.add(shuxing);
		
//		TextArea.setPreferredSize(new Dimension(this.getWidth()-50,this.getHeight()-100));

		TextArea.setLineWrap(true);
		System.out.println(contentPane.getWidth());
		panel2.setLayout(new GridLayout(1,1));
		panel2.add(TextArea);
		
		//给复选框添加事件
		shuxing.addActionListener(this);
		
		contentPane.add(panel1,BorderLayout.PAGE_START);
		contentPane.add(panel2,BorderLayout.CENTER);
		contentPane.setVisible(true);
		
		
		
			
	}
	static String username;
	//这个方法用于获取登录界面的信息
	 public static void get(User user) {
		 username = user.getUserName();
		 System.out.println(username);
	 }
	
	 String path = null;
	 //设置菜单中监听器的事件
	 public void actionPerformed(ActionEvent e) {
		 String select;
		 if(shuxing.isSelected())
		 {
			 select = "public";
		 }else {
			 select= "private";
		 }
		 
		 if(e.getActionCommand().equals("Renew")) {
			 swingdemo.MyFrame03();
		 }else if(e.getActionCommand().equals("Open")) {
			 //设置文件选择组件
			 JFileChooser openwhere = new JFileChooser();
			 //设置名字
			 openwhere.setDialogTitle("请选择文件...");
			 //设置默认方式
			 openwhere.showOpenDialog(null);
			 //显示
			 openwhere.setVisible(true);
			 
			 //获得用户选择的文件绝对路径
			 File file = openwhere.getSelectedFile();
			 path = file.getAbsolutePath();
			 System.out.println(path);
			 if(path.contains("private")) {
				 JOptionPane.showMessageDialog(this, "你没有权限打开别人未公开的笔记！");
			 }else {
				 //获取标题
				 TextField.setText(file.getName().substring(0, file.getName().lastIndexOf(".")));
				 //获取文件中的文本
				 byte[] data = new byte[1000];
				 try {
					FileInputStream inputStream = new FileInputStream(file);
					int n = inputStream.read(data);// 返回值n表示实际读取的字节数
					inputStream.close();
					String text = new String(data, 0, n, "UTF-8");
					TextArea.setText(text);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 }
		 }else if(e.getActionCommand().equals("Save")) {
			 //获取日志标题
			 String title = TextField.getText();
			 
			 // 根据用户选择的匿名或公开和用户名信息选择文件路径，用笔记的标题作为文件的命名
			 File dir = new File("c:/Note/"+select+"/"+username);
			 dir.mkdirs();

			 File file = new File(dir, title+".txt");
			 path = file.getAbsolutePath();
			 String text = TextArea.getText();
			 
			 try {
				byte[] data = text.getBytes("UTF-8");
				FileOutputStream outputStream = new FileOutputStream(file);
				outputStream.write(data);
				outputStream.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		 }else if(e.getActionCommand().equals("Delete")) {
			 //获取文件对象，判断是否存在，之后进行删除
			 System.out.println(path);
			 File f = new File(path);
			 if(f.exists()) {
				 FileUtils.deleteQuietly(f);
				 TextArea.setText(null);
				 TextField.setText(null);
			 }else {
				 JOptionPane.showMessageDialog(this, "删除失败，该文件不存在");
			 }
		 }
		 
	 }
	 public static void openpath(String path,String select) {
		 if(select.equals("public")) {
			 shuxing.setSelected(true);
		 }else{
			 shuxing.setSelected(false);
		 }
		 
		 File file = new File(path);
		 
		//获取标题
		 TextField.setText(file.getName().substring(0, file.getName().lastIndexOf(".")));
		 //获取文件中的文本
		 byte[] data = new byte[1000];
		 try {
			FileInputStream inputStream = new FileInputStream(file);
			int n = inputStream.read(data);// 返回值n表示实际读取的字节数
			inputStream.close();
			String text = new String(data, 0, n, "UTF-8");
			TextArea.setText(text);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	 
}
