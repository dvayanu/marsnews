<%@ page language="java" contentType="text/html;charset=iso-8859-15" session="true"
%><%@ taglib prefix="ano" uri="http://www.anotheria.net/ano-tags"
%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Marsnews</title>
<link rel="stylesheet" href="/mn/css/mui.css">
</head>
<body>
<jsp:include page="Menu.jsp" flush="false"/>
<br/>
<h3>Score History for <ano:write name="subtitle" ignore="true"/></h3>		
<jsp:include page="ScoresSlice.jsp" flush="false"/>
<jsp:include page="Footer.jsp" flush="false"/>

</body>
</html>

