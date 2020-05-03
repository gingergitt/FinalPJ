<%@page import="java.net.HttpURLConnection"%>
<%@page import="com.db.ConnectDB"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   ConnectDB connectDB = ConnectDB.getInstance();
	
   String id = request.getParameter("id");
   String pwd = request.getParameter("pwd");
   int age = Integer.parseInt(request.getParameter("age"));
   String gender = request.getParameter("gender");
   String phone = request.getParameter("phone");
   String email = request.getParameter("email");
   String address = request.getParameter("address");
   String agree = request.getParameter("agree");
   int adcategoryno = Integer.parseInt(request.getParameter("adcategoryno"));;
   String living= request.getParameter("living").trim();
   String food= request.getParameter("food").trim();
   String fashion= request.getParameter("fashion").trim();
   String culture= request.getParameter("culture").trim();
   String beauty= request.getParameter("beauty").trim();
   String pet= request.getParameter("pet").trim();
   String sport= request.getParameter("sport").trim();
   String publicad= request.getParameter("publicad").trim();
   String economy= request.getParameter("economy").trim();
   
	
	
	String result= connectDB.connectionDB(id,pwd,age,gender,phone,email,address,agree,adcategoryno,living,food,fashion,culture,beauty,pet,sport,publicad,economy);
 	System.out.println("---------------------");
 	System.out.println(id);
 	System.out.println(pwd);
 	System.out.println(age);
 	System.out.println(gender);
 	System.out.println(phone);
 	System.out.println(email);
 	System.out.println(address);
 	System.out.println(agree);
 	System.out.println(adcategoryno);
 	System.out.println(living+" "+food+" "+fashion+" "+culture+" "+beauty+" "+pet+" "+sport+" "+publicad+" "+economy);
 	System.out.println(result);
 	
	
 	 out.print(result);
 	 
   

   // 안드로이드로 전송
  	//out.println(results);
   
   
   //HttpURLConnection conn2 = (HttpURLConnection) http
   
   // HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    //conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    //conn.setRequestMethod("POST");//데이터를 POST 방식으로 전송합니다.

    //conn.setDoInput(true);
    //conn.connect();

   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
%>