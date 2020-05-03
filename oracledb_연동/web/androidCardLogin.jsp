<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@page import="com.db.CardLoginDB"%>

<%
CardLoginDB cardloginDB = CardLoginDB.getInstance();
	
   String id = request.getParameter("id");
   
   System.out.println(id);
	
   
	String result= cardloginDB.cardloginnDB(id);
 	System.out.println("---------------------");
 	System.out.println(id);
 	System.out.println(result);
 

// 	if(id.equals("id") && pwd.equals("pwd")) {
 	if(result.equals("YY")) {
 		System.out.println("Registered Card found");

 		out.println("1") ;
 		
 	}else {
 		System.out.println("No Card Registered");
 		
 		out.println("0");
 	}

 	 
   
   
   
   
   
   
   
   
   
   
   
%>