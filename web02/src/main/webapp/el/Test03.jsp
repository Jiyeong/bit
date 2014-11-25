<%@page import="java.util.HashSet"%>
<%@page import="java63.servlets.test05.domain.Product"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1> 값 꺼내기 </h1>
<h2> 배열 값 꺼내기</h2>
<%
pageContext.setAttribute("socres", 
    new int[]{100, 90, 80});
%>
<% ((int[])pageContext.getAttribute("socres"))[1]%><br>
${pageScope.scores[1]}<br>
${socres[1]}<br>

<h2> 리스트 값 꺼내기</h2>
<%
List<String> names = new ArrayList<String>();
names.add("홍길동");
names.add("임꺽정");
names.add("윤봉길");
names.add("안중근");
pageContext.setAttribute("names",names);
%>
<% ((List<String>)pageContext.getAttribute("names")).get(2)%><br>
${pageScope.names[2] }<br>
${names[2]}<br>

<h2> 맵에서 값 꺼내기</h2>
<%
Map<String, Integer> maps = new HashMap<String, Integer>();
maps.add("홍길동", 178);
maps.add("임꺽정", 180);
maps.add("이순신", 175);
maps.add("lee", 175);
pageContext.setAttribute("maps",maps);
%>
<% 
${pageContext.maps["홍길동"]}%><br>
${maps["임꺽정"]}<br>
${maps.lee}<br>

<h2> 자바 일반 객체에서 값 꺼내기</h2>
<%
Product prorduct = new Product();
product.setNo(10);
product.setName("아이폰");
product.setQuantity(200);
product.setMakerNo(2);

pageContext.setAttribute("product",product);
%>

${pageScope.product.no}<br>
${product.name}<br>
${product.quantity}<br>
${product.name}<br>
</body>
</html>