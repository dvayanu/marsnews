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
<jsp:include page="Menu.jsp" flush="false"/>
<div class="main">
<div class="content">
<div class="top">
	<div></div>
</div>
<div class="in">
	<div class="in_r">

<%
 String s = "-1";
 if (request.getAttribute("currentSelection")!=null)
 	s = ""+request.getAttribute("currentSelection");
 %>
 
<h2>Clan ranking by <ano:write name="currentValue"/></h2>
<table cellpadding="0" cellspacing="0" border="0" width="100%">
	<tr>
		<td align="left" width="30%" valign="top">
			<table width="100%" cellspacing="0" cellpadding="0" border="0" class="horizontal">
				<ano:iterate name="selection" id="item" type="net.anotheria.marsnews.presentation.bean.TopSelectionBean">
					<tr>
						<td>
							<ano:equal name="item" property="selection" value="<%=s%>">
								&raquo;&nbsp;<ano:write name="item" property="caption"/>
							</ano:equal>
							<ano:notEqual name="item" property="selection" value="<%=s%>">
								<a href="top?selection=<ano:write name="item" property="selection"/>">&raquo;&nbsp;<ano:write name="item" property="caption"/></a>
							</ano:notEqual>
						</td>
					</tr>
				</ano:iterate>
			</table>
		</td>
		<td align="left" valign="top" width="70%">
			<table cellspacing="0" cellpadding="0" border="0" width="100%" class="horizontal">
				<thead>
				<tr>
					<th width="30%">Rank</th>
					<th width="30%">Clan</th>
					<th width="40%"><ano:write name="currentValue"/></th>
				</tr>
				</thead>
				<tbody>
				<% int i=0;%>
				<ano:iterate name="top" id="t" type="net.anotheria.marsnews.presentation.bean.TopValueBean">
					<% i++;%>
						<% if(i==10){%>
					<%--ano:equal name="i" value="10"--%>
						<% i++;%>
						<tr class=""><td colspan="3" align="center" >
<!-- adsense -->
<script type="text/javascript"><!--
google_ad_client = "pub-4291652120495728";
google_ad_width = 728;
google_ad_height = 90;
google_ad_format = "728x90_as";
google_ad_type = "text_image";
//2007-08-08: MARSNEWSMAIN
google_ad_channel = "5548698917";
google_color_border = "336699";
google_color_bg = "FFFFFF";
google_color_link = "0000FF";
google_color_text = "000000";
google_color_url = "008000";
google_ui_features = "rc:6";
//-->
</script>
<script type="text/javascript"
  src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
</script>
<!-- / adsense -->
						</td></tr>
						<%}%>
					<%--/ano:equal--%>
					<tr class="<%= i%2==1 ? "light" : ""%>">	
						<td><ano:write name="t" property="rank"/></td>
						<td><a href="statsForClan?pClanId=<ano:write name="t" property="clan"/>"><ano:write name="t" property="clan"/></a></td>
						<td><ano:write name="t" property="value"/></td>
					</tr>
				</ano:iterate>
				</tbody>
			</table>
		</td>
	</tr>
<ano:present name="ranking">
	<tr>
		<td align="left" width="30%" valign="top">
			&nbsp;
		</td>
		<td align="left" valign="top" width="70%">
			<br/><br/>
			<h2>Server totals:</h2>
			<jsp:include page="SingleRanking.jsp" flush="false"/>
		</td>
	</tr>
</ano:present>
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

