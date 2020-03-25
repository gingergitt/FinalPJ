<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@page import="com.db.LoginDB"%>

<%
LoginDB loginDB = LoginDB.getInstance();
	
   String id = request.getParameter("id");
   String pwd = request.getParameter("pwd");
   
	String result= loginDB.loginnDB(id,pwd);
 	System.out.println("---------------------");
 	System.out.println(id);
 	System.out.println(pwd);
 	System.out.println(result);
 

 	if(id.equals("id") && pwd.equals("pwd")) {
 		out.println("1") ;
 		
 	}else {
 		out.println("0");
 	}

 	 
   
   
   
   
   
   
   
   
   
   
   
%>