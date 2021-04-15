package com.xin.notes.util;

import java.io.File;
import java.io.FileFilter;
import java.util.Vector;

import com.xin.notes.bean.User;

public class IOutil {
	static String username;
	public static void get(User user) {
		 username = user.getUserName();
		 System.out.println(username);
		 System.out.println(username);
	 }
	
	public static Vector<String> search(String path){
		Vector<String> filename = new Vector<>();
        System.out.println(path);
		File mulu = new File(path);
		File[] subFiles = mulu.listFiles();
		for(File f : subFiles)
		{
			filename.add(f.getName());
		
		}
		
		return filename;
		
	}

	public static Vector<String> filtersearch(String textfield,Vector<String> path) {
        Vector<String> filename = new Vector<>();
        for(String s : path) {
			File mulu = new File("c:/Note/public/"+s+"/");
			mulu.mkdirs();
			
			//加个过滤器，以达到搜索的目的
			FileFilter filter = new FileFilter() {
				
				@Override
				public boolean accept(File pathname) {
					String filePath = pathname.getAbsolutePath();
					if(textfield.equals("null")) {
						return true;
					}else {	
						if(filePath.contains(textfield)) {
							return true;
						}
						return false;
				
					}
				}
			};
			
			
			File[] subFiles = mulu.listFiles(filter);
			for(File f : subFiles)
			{
				filename.addElement(f.getName());
				System.out.println(f.getName());
				System.out.println(filename);
			
			}
        }
		return filename;
	}
	
	//主界面我的笔记查询
		public static Vector<String> mainuserscan(String username) {
			Vector<String> filename = new Vector<>();
			File mulu1 = new File("c:/Note/public/"+username);
			mulu1.mkdirs();
			File[] subFiles1 = mulu1.listFiles();
			for(File f : subFiles1)
			{
				filename.addElement(f.getName()+"(from public)");
			}
			
			File mulu2 = new File("c:/Note/private/"+username);
			mulu2.mkdirs();
			File[] subFiles2 = mulu2.listFiles();
			for(File f : subFiles2)
			{
				filename.addElement(f.getName()+"(from private)");
			}
			return filename;
			
		}
		
		//管理员界面查询
		public static Vector<String> guanliyuanscan(){
			Vector<String> mulu1name = IOutil.search("c:/Note/public");
			Vector<String> filename = new Vector<>(); 
			for(String s : mulu1name) {
				File mulu2 = new File("c:/Note/public/"+s);
				mulu2.mkdirs();
				File[] subFiles2 = mulu2.listFiles();
				for(File f : subFiles2)
				{
					filename.addElement(f.getAbsolutePath());
				}
			}
			System.out.println(filename);
			Vector<String> mulu2name = IOutil.search("c:/Note/private"); 
			for(String s : mulu2name) {
				File mulu2 = new File("c:/Note/private/"+s);
				mulu2.mkdirs();
				File[] subFiles2 = mulu2.listFiles();
				for(File f : subFiles2)
				{
					filename.addElement(f.getAbsolutePath());
				}
			}
			System.out.println(filename);
			return filename;
		}

}
