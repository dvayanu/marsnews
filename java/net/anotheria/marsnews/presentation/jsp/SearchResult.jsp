<%@ page language="java" contentType="text/html;charset=UTF-8" session="true"
%><%@ taglib prefix="ano" uri="http://www.anotheria.net/ano-tags"
%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>MarsNews</title>
	<link type="text/css" rel="stylesheet" rev="stylesheet" href="/mn/css/common.css"/>
</head>
<body>
<script type="text/javascript" src="js/jquery-1.4.min.js"></script>
<script type="text/javascript" src="js/function.js"></script>
<jsp:include page="Menu.jsp" flush="false"/>
<div class="main">
<div class="content">
<div class="top">
	<div></div>
</div>
<div class="in">
	<div class="in_r">

<ano:present name="rankedCountries">
	<h3>Search result for <ano:write name="lastSearchCriteria" ignore="true"/></h3>		
	<jsp:include page="ScoresSlice.jsp" flush="false"/>
</ano:present>
<ano:notPresent name="rankedCountries">
	<h3>Nothing found for <ano:write name="lastSearchCriteria" ignore="true"/></h3>
</ano:notPresent>
</div>
</div>
<div class="bot">
	<div></div>
</div>
</div>
</div>
<jsp:include page="Footer.jsp" flush="false"/>
</body>
</html>

