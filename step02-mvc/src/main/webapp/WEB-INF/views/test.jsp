<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test JSP</title>
</head>
<body>
	<!-- HTML -->
	<%--JSP 주석 --%>
	<%! String id = "cloud"; %>
	<%= id %>
	<% String grade = "junior"; 
		System.out.println(grade);
	%>
	
	${2 > 3}
</body>
</html>