<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@page import="com.db.PayHistoryDB"%>
<%
PayHistoryDB payhistoryDB = PayHistoryDB.getInstance();
	
   String id = request.getParameter("id");
   
   System.out.println(id);
	
   
	String result= payhistoryDB.HistroyDB(id);
 	System.out.println("---------------------");
 	System.out.println(id);
 	System.out.println(result);
 

 	if(result.equals("SS")) {
 		System.out.println("�ֱٰ������� ������");

 		out.println("111") ;
 		
 	}else {
 		System.out.println("������������");
 		
 		out.println("000");
 	}

    %>