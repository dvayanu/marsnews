<%@ page language="java" contentType="text/html;charset=UTF-8" session="true"
%><%@ taglib prefix="ano" uri="http://www.anotheria.net/ano-tags"
%>
<div class="header">
	<div class="in">
		<a href="#" class="logo"><img src="../images/logo.gif" alt="MarsNews"/></a>

		<div class="flr">
			<div class="login">
				<ul>
					<li><a href="#">Register</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
					<li><a href="#">Login</a>&nbsp;&nbsp;</li>
<%--
					<li class="log active"><a href="#">Login</a>
						<div class="log_form">
							<input type="text" value="username"/>
							<input type="password" value="password"/>
							<a href="#" class="button"><span>Login</span></a>
						</div> 
					</li>
--%>
				</ul>
			</div>
			<div class="clear"></div>
			<div class="search">
				<form name="Search" id="Search" action="search" method="get"> 
				<input type="text" name="pCriteria" value="<ano:write name="lastSearchCriteria" ignore="true"/>"/>
				<a href="#" class="button" onclick="document.Search.submit(); return false"><span>Search</span></a>
				</form>
			</div>
		</div>
		<div class="clear"></div>
	</div>
</div>
<ul class="menu">
	<li <ano:equal name="currentMenu" value="news">class="active"</ano:equal>><a href="news"><span>News</span></a></li>
	<li <ano:equal name="currentMenu" value="clanstats">class="active"</ano:equal>><a href="statsForClan"><span>Clan Stats</span></a></li>
	<li <ano:equal name="currentMenu" value="war">class="active"</ano:equal>><a href="warReport"><span>War Report</span></a></li>
	<li <ano:equal name="currentMenu" value="scores">class="active"</ano:equal>><a href="scores"><span>Scores</span></a></li>
	<li <ano:equal name="currentMenu" value="top">class="active"</ano:equal>><a href="top"><span>Top</span></a></li>
</ul>

<%--
<table width="100%" cellpadding="0" cellspacing="0" border="0">
	<form name="Search" action="search" method="get">
	<tr>
		<td align="left">
			<a href="news">News</a>&nbsp;&nbsp;<a href="statsForClan">ClanStats</a>&nbsp;&nbsp;<a href="warReport">WarReport</a>&nbsp;&nbsp;<a href="scores">Scores</a>&nbsp;&nbsp;<a href="top">Top</a>
		</td>
		<td align="right" width="20%" >
			<span>Country name or number:&nbsp;<input type="text" size="20" name="pCriteria" value="<ano:write name="lastSearchCriteria" ignore="true"/>">&nbsp;&nbsp;<input type="submit" value="Search"></span>
		</td>
	</tr>
	</form>
</table>
<br/>
<center>
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
</center>
<hr size="1">
--%>