<%@ page language="java" contentType="text/html;charset=UTF-8" session="true"
%><%@ taglib prefix="ano" uri="http://www.anotheria.net/ano-tags"
%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>MarsNews</title>
	<link type="text/css" rel="stylesheet" rev="stylesheet" href="/mn/css/common.css"/>
<ano:define toScope="request" type="java.lang.String" id="currentMenu">top</ano:define>	
</head>
<body>
<script type="text/javascript" src="js/jquery-1.4.min.js"></script>
<script type="text/javascript" src="js/function.js"></script>
<ano:define toScope="request" type="java.lang.String" id="currentMenu">war</ano:define>	
<jsp:include page="Menu.jsp" flush="false"/>
<div class="main">
<h2>Create a war report </h2>		
<div class="content">
<div class="top">
	<div></div>
</div>
<div class="in">
	<div class="in_r">

<table width=100% cellpadding="0" cellspacing="0" class="horizontal">
	<form action="warReport" method="get" id="wardialog" name="wardialog"> 
	<tr class="light">
		<td width="20%" align="right" >Start date:&nbsp;</td>
		<td width="80%" align="left"> 
			Day:&nbsp;
			<select name="pSinceDay"> 
				<ano:iterate name="days" type="net.anotheria.webutils.bean.LabelValueBean" id="day"><option value="<ano:write name="day" property="value"/>"><ano:write name="day" property="label"/></option></ano:iterate>
			</select>
			&nbsp;&nbsp;
			Month:&nbsp;
			<select name="pSinceMonth"> 
				<ano:iterate name="months" type="net.anotheria.webutils.bean.LabelValueBean" id="month"><option value="<ano:write name="month" property="value"/>"><ano:write name="month" property="label"/></option></ano:iterate>
			</select>
			&nbsp;&nbsp;
			Hour:&nbsp;
			<select name="pSinceHour"> 
				<ano:iterate name="hours" type="net.anotheria.webutils.bean.LabelValueBean" id="hour"><option value="<ano:write name="hour" property="value"/>"><ano:write name="hour" property="label"/></option></ano:iterate>
			</select>
			&nbsp;&nbsp;
			Minute:&nbsp;
			<select name="pSinceMinute"> 
				<ano:iterate name="minutes" type="net.anotheria.webutils.bean.LabelValueBean" id="minute"><option value="<ano:write name="minute" property="value"/>"><ano:write name="minute" property="label"/></option></ano:iterate>
			</select>
			&nbsp;&nbsp;
			<i>Optional, leave blank to see all news from set start.
		</td>
	</tr>
	<tr class="">
		<td width="20%" align="right" >End date:&nbsp;</td>
		<td width="80%" align="left"> 
			Day:&nbsp;
			<select name="pUntilDay"> 
				<ano:iterate name="days" type="net.anotheria.webutils.bean.LabelValueBean" id="day"><option value="<ano:write name="day" property="value"/>"><ano:write name="day" property="label"/></option></ano:iterate>
			</select>
			&nbsp;&nbsp;
			Month:&nbsp;
			<select name="pUntilMonth"> 
				<ano:iterate name="months" type="net.anotheria.webutils.bean.LabelValueBean" id="month"><option value="<ano:write name="month" property="value"/>"><ano:write name="month" property="label"/></option></ano:iterate>
			</select>
			&nbsp;&nbsp;
			Hour:&nbsp;
			<select name="pUntilHour"> 
				<ano:iterate name="hours" type="net.anotheria.webutils.bean.LabelValueBean" id="hour"><option value="<ano:write name="hour" property="value"/>"><ano:write name="hour" property="label"/></option></ano:iterate>
			</select>
			&nbsp;&nbsp;
			Minute:&nbsp;
			<select name="pUntilMinute"> 
				<ano:iterate name="minutes" type="net.anotheria.webutils.bean.LabelValueBean" id="minute"><option value="<ano:write name="minute" property="value"/>"><ano:write name="minute" property="label"/></option></ano:iterate>
			</select>
			&nbsp;&nbsp;
			<i>Optional, leave blank to see all news until now.
		</td>
	</tr>
	<tr class="light">
		<td width="20%" align="right" >Side A:&nbsp;</td>
		<td width="80%" align="left"> 
			<table width="100% cellpadding="3" cellspacing="3" border="0">
			<ano:iterate name="clanNames" type="java.util.List" id="list">
				<tr>
				<ano:iterate name="list" type="java.lang.String" id="clanName">
					<td><input type="checkbox" class="fll" name="<ano:write name="clanName"/>" value="sideA"/><label for="ch" class="r_label"><ano:write name="clanName"/></label></td>
				</ano:iterate>
				</tr>
			</ano:iterate>
			</table>
		</td>
	</tr>
	<tr class="">
		<td width="20%" align="right" >Side B:&nbsp;</td>
		<td width="80%" align="left"> 
			<table width="100% cellpadding="3" cellspacing="3" border="0">
			<ano:iterate name="clanNames" type="java.util.List" id="list">
				<tr>
				<ano:iterate name="list" type="java.lang.String" id="clanName">
					<td>&nbsp;&nbsp;<input type="checkbox" name="<ano:write name="clanName"/>" value="sideB"/>&nbsp;<ano:write name="clanName"/></td>
				</ano:iterate>
				</tr>
			</ano:iterate>
			</table>
		</td>
	</tr>
	<tr class="light">
		<td width="20%" align="right" >&nbsp;</td>
		<td width="80%" align="left"> &nbsp;
		</td>
	</tr>
	<tr class="">
		<td width="20%" align="right" >&nbsp;<input type="hidden" name="query" value="true"/></td>
		<td width="80%" align="left"> <a href="#" class="button" onclick="document.wardialog.submit(); return false"><span>Submit</span></a>&nbsp;<a href="#" class="f_14 mt_3 fll" onclick="document.wardialog.reset(); return false>reset form</a>
		<div class="clear"/>
		</td>
	</tr>
	
</table>
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


