<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@page import="com.db.CardSearchDB"%>
    
 <%
 CardSearchDB cardsearchDB = CardSearchDB.getInstance();
	
   String id = request.getParameter("id");
   
   System.out.println(id);
	
   
	String result=  cardsearchDB.SearchDB(id);
 	System.out.println("---------------------");
 	System.out.println(id);
 	System.out.println(result);
 

 	if(result.equals("OO")) {
 		System.out.println("ī���������OK");

 		out.println("1") ;
 		
 	}else {
 		System.out.println("ī�����������ü�����");
 		
 		out.println("0");
 	}

    %>