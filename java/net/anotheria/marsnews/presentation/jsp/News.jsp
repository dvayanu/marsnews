<%@ page language="java" contentType="text/html;charset=UTF-8" session="true"
%><%@ taglib prefix="ano" uri="http://www.anotheria.net/ano-tags"
%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>MarsNews</title>
	<link type="text/css" rel="stylesheet" rev="stylesheet" href="/mn/css/common.css"/>
<ano:define toScope="request" type="java.lang.String" id="currentMenu">news</ano:define>	
</head>
<body>
<script type="text/javascript" src="js/jquery-1.4.min.js"></script>
<script type="text/javascript" src="js/function.js"></script>
<jsp:include page="Menu.jsp" flush="false"/>
<div class="main">
<ano:present name="intervals" scope="request">
	<jsp:include page="IntervalSelection.jsp" flush="false"/>
</ano:present>
<ano:present name="clans" scope="request">
	<jsp:include page="ClanSelection.jsp" flush="false"/>
</ano:present>

<div class="content">
<div class="top">
	<div></div>
</div>
<div class="in">
	<div class="in_r">
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
			<tbody>
			<tr>
				<td class="pr_per" valign="top">
					<h2>News for clan Chaos</h2>
					<table cellpadding="0" cellspacing="0" border="0" width="100%" class="horizontal">
						<thead>
						<tr>
							<th>Date</th>
							<th>Type</th>
							<th>Attacker</th>
							<th>Defender</th>
							<th>Result</th>
						</tr>
						</thead>
						<tbody>
						<tr>
							<td colspan="5" class="ads">
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
							</td>
						</tr>
						
				<ano:iterate name="entries" type="net.anotheria.marsnews.presentation.bean.NewsItemBean" id="entry" indexId="i">
					<tr class="<%=i.intValue()%2==1 ? "light" : ""%> ">	
						<td><ano:write name="entry" property="date"/></td>
						<td><ano:write name="entry" property="type"/></td>
						<td><a href="newsForCountry?pCountryId=<ano:write name="entry" property="attackerId"/>"><ano:write name="entry" property="attacker"/></a>
						<ano:equal name="entry" property="attackerTagged" value="true">
							&nbsp;[<a href="newsForClan?pClanId=<ano:write name="entry" property="attackerClan"/>"><ano:write name="entry" property="attackerClan"/></a>]
						</ano:equal>
						</td>
						<td><a href="newsForCountry?pCountryId=<ano:write name="entry" property="defenderId"/>"><ano:write name="entry" property="defender"/></a>
						<ano:equal name="entry" property="defenderTagged" value="true">
							&nbsp;[<a href="newsForClan?pClanId=<ano:write name="entry" property="defenderClan"/>"><ano:write name="entry" property="defenderClan"/></a>]
						</ano:equal>
						</td>
						<td>
							<ano:equal name="entry" property="held" value="true">
								Held
							</ano:equal>
							<ano:equal name="entry" property="held" value="false">
								<ano:equal name="entry" property="kill" value="true">
									<font color="red"><b>
								</ano:equal>
								<ano:write name="entry" property="result"/>
								<ano:equal name="entry" property="kill" value="true">
									</b></font>
								</ano:equal>
							</ano:equal>
						</td>
					</tr>
				</ano:iterate>
			</table>
			<ul class="paginator">
		           <ano:equal name="paging" property="hasPrevious" value="true"><li class="prev"><a href="<ano:write name="linkToCurrentPage" ignore="true"/>&amp;page=<ano:write name="paging" property="previousPageNumber"/>">prev</a></li></ano:equal>
		
		           <ano:iterate name="paging" property="elements" id="element">
		           	<ano:notEqual name="element" property="separator" value="true">
		           	<li <ano:equal name="element" property="active" value="true">class="curr"</ano:equal>>
		           	<ano:equal name="element" property="linked" value="true">
		               <a href="<ano:write name="linkToCurrentPage" ignore="true"/>&amp;page=<ano:write name="element" property="pagingParameter"/>"></ano:equal
		               	 ><ano:write name="element" property="caption"/><ano:equal name="element" property="linked" value="true"
		               	 ></a>
		           	</ano:equal>
		           </li>
		           	</ano:notEqual>
		           	<ano:equal name="element" property="separator" value="true">
		           	<li>...</li>
		           	</ano:equal>
		
		           </ano:iterate>
		
						
		           <ano:equal name="paging" property="hasNext" value="true"><li class="next"><a href="<ano:write name="linkToCurrentPage" ignore="true"/>&amp;page=<ano:write name="paging" property="nextPageNumber"/>">next</a></li></ano:equal>
<%--				<li class="prev dis"><a href="#">prev</a></li>
				<li class="curr"><a href="#">1</a></li>
				<li><a href="#">2</a></li>
				<li><a href="#">3</a></li>
				<li><a href="#">...</a></li>
				<li><a href="#">10</a></li>
				<li class="next"><a href="#">next</a></li> --%>
			</ul>
		</td>
		<td valign="top">
			<h2>Totals</h2>
			<ano:present name="totals.kills">
				<table cellpadding="0" cellspacing="0" border="0" width="100%" class="vertical">
					<thead>
						<tr><th colspan="2">Killed / Destroyed</th></tr>
					</thead>
					<tbody>
					<ano:iterate name="totals.kills" type="net.anotheria.marsnews.presentation.bean.NewsTotalBean" id="total" indexId="i">
						<tr class="<%=i.intValue()%2==1 ? "light" : ""%> ">	
							<td><strong><ano:write name="total" property="name"/>:</strong></td>
							<td><ano:write name="total" property="value"/><ano:write name="total" property="descriptor"/></td>
						</tr>
					</ano:iterate>				
					</tbody>
				</table>
			</ano:present>
			
			<ano:present name="totals.gains">
				<table cellpadding="0" cellspacing="0" border="0" width="100%" class="vertical">
					<thead>
						<tr><th colspan="2">Gained</th></tr>
					</thead>
					<ano:iterate name="totals.gains" type="net.anotheria.marsnews.presentation.bean.NewsTotalBean" id="total" indexId="i">
						<tr class="<%=i.intValue()%2==1 ? "light" : ""%> ">	
							<td><strong><ano:write name="total" property="name"/>:</strong></td>
							<td><ano:write name="total" property="value"/><ano:write name="total" property="descriptor"/></td>
						</tr>
					</ano:iterate>				
				</table>
			</ano:present>

			<ano:present name="totals.loosage">
				<table cellpadding="0" cellspacing="0" border="0" width="100%" class="vertical">
					<thead>
						<tr><th colspan="2">Lost</th></tr>
					</thead>
					<ano:iterate name="totals.loosage" type="net.anotheria.marsnews.presentation.bean.NewsTotalBean" id="total" indexId="i">
						<tr class="<%=i.intValue()%2==1 ? "light" : ""%> ">	
							<td><strong><ano:write name="total" property="name"/>:</strong></td>
							<td><ano:write name="total" property="value"/><ano:write name="total" property="descriptor"/></td>
						</tr>
					</ano:iterate>				
				</table>
			</ano:present>
	
			<ano:present name="totals.attacks">
				<table cellpadding="0" cellspacing="0" border="0" width="100%" class="vertical">
					<thead>
						<tr>
						<ano:present name="filterAttackerParam" scope="request">
							<th colspan="2">Attacks <span>(Total: <a href="filterNews?<ano:write name="filterAttackerParam"/>"><ano:write name="totals.attacks.total"/></a>, Broke: <a href="filterNews?<ano:write name="filterAttackerParam"/>&amp;pBrokeOnly=true"><ano:write name="totals.attacks.total.broke"/>)</span></th>
						</ano:present>
						<ano:notPresent name="filterAttackerParam" scope="request">
							<th colspan="2">Attacks <span>(Total: <ano:write name="totals.attacks.total"/>, Broke: <ano:write name="totals.attacks.total.broke"/>)</span></th>
						</ano:notPresent>
						</tr>
					</thead>

					<tbody>
					<ano:iterate name="totals.attacks" type="net.anotheria.marsnews.presentation.bean.AttackTypeCounterBean" id="counter" indexId="i">
						<tr class="<%=i.intValue()%2==1 ? "light" : ""%> ">	
							<td>
								<ano:present name="filterAttackerParam" scope="request">
									<strong><a href="filterNews?pType=<ano:write name="counter" property="type"/>&<ano:write name="filterAttackerParam"/>"><ano:write name="counter" property="type"/></a></strong>:
								</ano:present>
								<ano:notPresent name="filterAttackerParam" scope="request">
									<ano:write name="counter" property="type"/>:
								</ano:notPresent>
							</td>
							<td><ano:write name="counter" property="count"/>&nbsp;(<ano:write name="counter" property="brokeCount"/>/<ano:write name="counter" property="heldCount"/>)</td>
						</tr>
					</ano:iterate>				
					</tbody>
				</table>
			</ano:present>
	
			<ano:present name="totals.defends">
				<table cellpadding="0" cellspacing="0" border="0" width="100%" class="vertical">
					<thead>
						<tr>
						<ano:present name="filterDefenderParam" scope="request">
							<th colspan="2">Defends <span>(Total: <a href="filterNews?<ano:write name="filterDefenderParam"/>"><ano:write name="totals.defends.total"/></a>, Broke: <a href="filterNews?<ano:write name="filterDefenderParam"/>&amp;pBrokeOnly=true"><ano:write name="totals.defends.total.held"/>)</span></th>
						</ano:present>
						<ano:notPresent name="filterDefenderParam" scope="request">
							<th colspan="2">Defends <span>(Total: <ano:write name="totals.defends.total"/>, Broke: <ano:write name="totals.defends.total.held"/>)</span></th>
						</ano:notPresent>
						</tr>
					</thead>

					<tbody>
					<ano:iterate name="totals.defends" type="net.anotheria.marsnews.presentation.bean.AttackTypeCounterBean" id="counter" indexId="i">
						<tr class="<%=i.intValue()%2==1 ? "light" : ""%> ">	
							<td>
								<ano:present name="filterDefenderParam" scope="request">
									<strong><a href="filterNews?pType=<ano:write name="counter" property="type"/>&amp;<ano:write name="filterDefenderParam"/>"><ano:write name="counter" property="type"/></a></strong>:
								</ano:present>
								<ano:notPresent name="filterDefenderParam" scope="request">
									<ano:write name="counter" property="type"/>:
								</ano:notPresent>
							</td>
							<td><ano:write name="counter" property="count"/>&nbsp;(<ano:write name="counter" property="brokeCount"/>/<ano:write name="counter" property="heldCount"/>)</td>
						</tr>
					</ano:iterate>				
					</tbody>
				</table>
			</ano:present>
								
			<ano:present name="rankedCountry">
			<h2>Country Info</h2>
				<table cellpadding="0" cellspacing="0" border="0" width="100%" class="vertical">
					<thead>
					<tr>
						<th colspan="2">
							<ano:equal name="rankedCountry" property="dead" value="true"><font color="red"></ano:equal>
							<ano:write name="rankedCountry" property="goverment"/>&nbsp;
							<ano:write name="rankedCountry" property="name"/>(#<ano:write name="rankedCountry" property="countryId"/>)
							<ano:equal name="rankedCountry" property="dead" value="true"></font></ano:equal>
						</th>
					</tr>
					</thead>
					<tbody>
					<tr class="light">
						<td><strong>Networth:</strong></td>
						<td><ano:write name="rankedCountry" property="networth"/></td>
					</tr>
					<tr class="">
						<td><strong>Land:</strong></td>
						<td><ano:write name="rankedCountry" property="land"/></td>
					</tr>
					<tr class="light">
						<td><strong>Rank:</strong></td>
						<td><ano:write name="rankedCountry" property="rank"/></td>
					</tr>
					<ano:equal name="rankedCountry" property="gdi" value="true">
					<tr class="">
						<td colspan="2">GDI</td>
					</tr>
					</ano:equal>
					<ano:equal name="rankedCountry" property="underProtection" value="true">
					<tr class="">
						<td colspan="2">Under protection</td>
					</tr>
					</ano:equal>
					</tbody>
				</table>
			</ano:present>

				</td>
			</tr>
			</tbody>
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

