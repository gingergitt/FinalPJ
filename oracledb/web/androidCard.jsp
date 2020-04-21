<%@page import="java.net.HttpURLConnection"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="com.db.CardPayDB"%>
<%@page import="com.db.LoginDB"%>
    
<%
CardPayDB cardpayDB = CardPayDB.getInstance();

String cardno = request.getParameter("cardno");

String cardname = request.getParameter("cardname");
String id  = request.getParameter("id");
String cardagency = request.getParameter("cardagency");

String res = cardpayDB.cardsDB(cardno,cardname,id,cardagency);
System.out.println("======================!!");
System.out.println(cardno);
System.out.println(cardname);
System.out.println(id);
System.out.println(cardagency);

if(res.equals("OK")) {
		System.out.println("Card Register Success");

		out.println("11") ;
		
	}else {
		System.out.println("Card Register Fail");
		out.println("00");
	}


%>