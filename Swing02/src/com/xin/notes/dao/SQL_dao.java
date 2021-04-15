package com.xin.notes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.xin.notes.util.DButil;

public class SQL_dao {
	public static boolean login(String Name, String Password){
		 boolean success = false;
		 Connection conn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 try {
			//获取连接
			conn = DButil.getConnection();
			//获取预编译的数据库操作对象、
			String sql = "select * from `user` where user_name = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,Name );
			rs = ps.executeQuery();
			if(rs.next()) {
				if(Password.equals(rs.getString("password"))){
					success = true;
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//释放资源
			DButil.close(conn, ps);
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		 return success;
	}
	
	public static boolean register(String rn,String rp) {
		boolean success = false;
		Connection conn = null;
		PreparedStatement ps = null;
		int i;
		try {
			conn = DButil.getConnection();
			String sql = "insert into `user`(user_name,password) values (?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, rn);
			ps.setString(2, rp);
			i = ps.executeUpdate();
			if(i>0) {
				success = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DButil.close(conn, ps );
		}
		return success;
		
	}
}
