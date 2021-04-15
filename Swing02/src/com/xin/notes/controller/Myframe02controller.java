package com.xin.notes.controller;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Myframe02controller {
	//填充表格方法
		public static void filltable(JTable table,Vector<String> date) {
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
			dtm.setRowCount(0);
			for(String s:date) {
				Vector<String> a = new Vector<>();
				a.add(s);
				dtm.addRow(a);
			}
		}
		
}
