<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@page import="com.db.CardPlusDB"%>

<%
CardPlusDB cardplusDB = CardPlusDB.getInstance();
	
   String id = request.getParameter("id");
   
   System.out.println(id);
	
   
	String result= cardplusDB.CardPlussDB(id);
 	System.out.println("---------------------");
 	System.out.println(id);
 	System.out.println(result);
 

// 	if(id.equals("id") && pwd.equals("pwd")) {
 	if(result.equals("YY")) {
 		System.out.println("새로운 카드정보를 추가합니다.");

 		out.println("1") ;
 		
 	}else {
 		System.out.println("더이상 카드를 추가할 수 없습니다.");
 		
 		out.println("0");
 	}

   
%>