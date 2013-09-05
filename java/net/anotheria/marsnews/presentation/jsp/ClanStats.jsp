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
<ano:present name="clans" scope="request">
	<jsp:include page="ClanSelection.jsp" flush="false"/>
</ano:present>
<div class="top">
	<div></div>
</div>
<div class="in">
	<div class="in_r">

<h2>Stats <ano:write name="subtitle" ignore="true"/></h2>		
<ano:notPresent name="clanStats.countries" scope="session">
<h3>No data or nothing selected</h3>		
</ano:notPresent>
<br/>
<jsp:include page="ScoresSlice.jsp" flush="false"/>
<br/><center>
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
<br/></center>
<table width="100%" cellpadding="0" cellspacing="0" border="0" class="horizontal">
	<thead>
	<tr>
		<th>&nbsp;</th>
		<th>id</th>
		<ano:iterate name="captions" id="caption" type="net.anotheria.marsnews.presentation.bean.CaptionBean">
			<th>
				<ano:equal name="caption" property="selected" value="true">
					<b><ano:write name="caption" property="caption"/></b>
				</ano:equal>
				<ano:equal name="caption" property="selected" value="false">
					<a href="<ano:write name="caption" property="link"/>"><ano:write name="caption" property="caption"/></a>
				</ano:equal>
			</th>
		</ano:iterate>
	</tr>
	</thead>
	<ano:present name="clanStats.countries" scope="session">
	<tbody>
	<ano:iterate name="clanStats.countries" type="net.anotheria.marsnews.presentation.bean.ClanStatsCountryBean" id="country" indexId="i">
		<tr class="<%= i%2==1 ? "light" : ""%>">	
			<td><%=(i+1)%></td>
			<td><ano:write name="country" property="id"/></td>
			<td><ano:write name="country" property="name"/></td>
			<%-- <td> - </td> --%>
			
			<td><ano:write name="country" property="kills"/></td>
			<td><b><ano:write name="country" property="attackCount"/></b></td>
			<td><ano:write name="country" property="attackCountSS"/></td>
			<td><ano:write name="country" property="attackCountPS"/></td>
			<td><ano:write name="country" property="attackCountGS"/></td>
			<td><ano:write name="country" property="attackCountBR"/></td>
			<td><ano:write name="country" property="attackCountAB"/></td>
			<td><ano:write name="country" property="attackCountNM"/></td>
			<td><ano:write name="country" property="attackCountCM"/></td>
			<td><ano:write name="country" property="attackCountEM"/></td>
				
			<td><b><ano:write name="country" property="defendCount"/></b></td>
			<td><ano:write name="country" property="defendCountSS"/></td>
			<td><ano:write name="country" property="defendCountPS"/></td>
			<td><ano:write name="country" property="defendCountGS"/></td>
			<td><ano:write name="country" property="defendCountBR"/></td>
			<td><ano:write name="country" property="defendCountAB"/></td>
			<td><ano:write name="country" property="defendCountNM"/></td>
			<td><ano:write name="country" property="defendCountCM"/></td>
			<td><ano:write name="country" property="defendCountEM"/></td>
		</tr>
	</ano:iterate>
	</tbody>
		<thead>
		<tr>	
			<td colspan="3" align="right"><strong>Totals:</strong></th>
			<%-- <th> - </th> --%>
			
			<th><ano:write name="clanStats.totals" property="kills"/></th>
			<th><b><ano:write name="clanStats.totals" property="attackCount"/></b></th>
			<th><ano:write name="clanStats.totals" property="attackCountSS"/></th>
			<th><ano:write name="clanStats.totals" property="attackCountPS"/></th>
			<th><ano:write name="clanStats.totals" property="attackCountGS"/></th>
			<th><ano:write name="clanStats.totals" property="attackCountBR"/></th>
			<th><ano:write name="clanStats.totals" property="attackCountAB"/></th>
			<th><ano:write name="clanStats.totals" property="attackCountNM"/></th>
			<th><ano:write name="clanStats.totals" property="attackCountCM"/></th>
			<th><ano:write name="clanStats.totals" property="attackCountEM"/></th>
				
			<th><b><ano:write name="clanStats.totals" property="defendCount"/></b></th>
			<th><ano:write name="clanStats.totals" property="defendCountSS"/></th>
			<th><ano:write name="clanStats.totals" property="defendCountPS"/></th>
			<th><ano:write name="clanStats.totals" property="defendCountGS"/></th>
			<th><ano:write name="clanStats.totals" property="defendCountBR"/></th>
			<th><ano:write name="clanStats.totals" property="defendCountAB"/></th>
			<th><ano:write name="clanStats.totals" property="defendCountNM"/></th>
			<th><ano:write name="clanStats.totals" property="defendCountCM"/></th>
			<th><ano:write name="clanStats.totals" property="defendCountEM"/></th>
		</tr>
		</thead>
		</ano:present>
</table>
<br/>
<ano:present name="ranking">
<center> 
<jsp:include page="SingleRanking.jsp" flush="false"/>
</center>
<br/>
</ano:present>
<jsp:include page="Footer.jsp" flush="false"/>
</body>
</html>

