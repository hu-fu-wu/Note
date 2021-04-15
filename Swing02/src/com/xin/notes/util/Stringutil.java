package com.xin.notes.util;

public class Stringutil {
	//用于去除字符串中括号内的内容
		public static String ClearBracket(String context) {
	      int head = context.indexOf('('); // 标记第一个使用左括号的位置
	      int next = head + 1; // 从head+1起检查每个字符
		  if(head!=-1) {   
	      	  while(context.charAt(next) != ')') {
		    	  next++;
		      }
		      String temp = context.substring(head, next+1);
		      context = context.replace(temp, ""); // 用空内容替换，复制给context
		  }
	      return context; // 返回更新后的context
	  }
}
