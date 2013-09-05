<%@ page language="java" contentType="text/html;charset=UTF-8" session="true"
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
<ano:present name="intervals" scope="request">
	<jsp:include page="IntervalSelection.jsp" flush="false"/>
</ano:present>
<ano:present name="clans" scope="request">
	<jsp:include page="ClanSelection.jsp" flush="false"/>
</ano:present>
<br/><br/>
<table width="100%" cellpadding="3" cellspacing="3" border="0">
	<tr>
		<td width="80%" valign="top">
			<h3>News <ano:write name="subtitle" ignore="true"/></h3>		
			<table width="100%" border="0" cellspacing="2" cellpadding="2">
				<tr class="lineCaptions">
					<td>Date</td>
					<td>Type</td>
					<td>Attacker</td>
					<td>Defender</td>
					<td>Result</td>
				</tr>
				<ano:iterate name="entries" type="net.anotheria.marsnews.presentation.bean.NewsItemBean" id="entry" indexId="i">
					<tr class="<%=i.intValue()%2==1 ? "lineDark" : "lineLight"%> ">	
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
		</td>
		<td width="20%" valign="top">
			<h3>Totals</h3>
			<ano:present name="totals.kills">
				<table width="100%" cellpadding="2" cellspacing="2" border="0">
					<tr class="lineCaptions">
						<td colspan="2">Killed / Destroyed</td>
					</tr>
					<ano:iterate name="totals.kills" type="net.anotheria.marsnews.presentation.bean.NewsTotalBean" id="total" indexId="i">
						<tr class="<%=i.intValue()%2==1 ? "lineDark" : "lineLight"%> ">	
							<td><ano:write name="total" property="name"/>:&nbsp;</td>
							<td><ano:write name="total" property="value"/><ano:write name="total" property="descriptor"/></td>
						</tr>
					</ano:iterate>				
				</table>
			&nbsp;<br/>
			</ano:present>
			<ano:present name="totals.gains">
				<table width="100%" cellpadding="2" cellspacing="2" border="0">
					<tr class="lineCaptions">
						<td colspan="2">Gained</td>
					</tr>
					<ano:iterate name="totals.gains" type="net.anotheria.marsnews.presentation.bean.NewsTotalBean" id="total" indexId="i">
						<tr class="<%=i.intValue()%2==1 ? "lineDark" : "lineLight"%> ">	
							<td><ano:write name="total" property="name"/>:&nbsp;</td>
							<td><ano:write name="total" property="value"/><ano:write name="total" property="descriptor"/></td>
						</tr>
					</ano:iterate>				
			</table>
			&nbsp;<br/>
			</ano:present>
			<ano:present name="totals.loosage">
				<table width="100%" cellpadding="2" cellspacing="2" border="0">
					<tr class="lineCaptions">
						<td colspan="2">Lost</td>
					</tr>
					<ano:iterate name="totals.loosage" type="net.anotheria.marsnews.presentation.bean.NewsTotalBean" id="total" indexId="i">
						<tr class="<%=i.intValue()%2==1 ? "lineDark" : "lineLight"%> ">	
							<td><ano:write name="total" property="name"/>:&nbsp;</td>
							<td><ano:write name="total" property="value"/><ano:write name="total" property="descriptor"/></td>
						</tr>
					</ano:iterate>				
			</table>
			&nbsp;<br/>
			</ano:present>
			<ano:present name="totals.attacks">
				<table width="100%" cellpadding="2" cellspacing="2" border="0">
					<tr class="lineCaptions">
						<ano:present name="filterAttackerParam" scope="request">
							<td colspan="2">Attacks (Total: <a href="filterNews?<ano:write name="filterAttackerParam"/>"><ano:write name="totals.attacks.total"/></a>,&nbsp;Broke: <a href="filterNews?<ano:write name="filterAttackerParam"/>&pBrokeOnly=true"><ano:write name="totals.attacks.total.broke"/></a>)</td>
						</ano:present>
						<ano:notPresent name="filterAttackerParam" scope="request">
							<td colspan="2">Attacks (Total: <ano:write name="totals.attacks.total"/>,&nbsp;Broke: <ano:write name="totals.attacks.total.broke"/>)</td>
						</ano:notPresent>
					</tr>
					<ano:iterate name="totals.attacks" type="net.anotheria.marsnews.presentation.bean.AttackTypeCounterBean" id="counter" indexId="i">
						<tr class="<%=i.intValue()%2==1 ? "lineDark" : "lineLight"%> ">	
							<td>
								<ano:present name="filterAttackerParam" scope="request">
									<a href="filterNews?pType=<ano:write name="counter" property="type"/>&<ano:write name="filterAttackerParam"/>"><ano:write name="counter" property="type"/></a>:&nbsp;
								</ano:present>
								<ano:notPresent name="filterAttackerParam" scope="request">
									<ano:write name="counter" property="type"/>:&nbsp;
								</ano:notPresent>
							</td>
							<td><ano:write name="counter" property="count"/>&nbsp;(<ano:write name="counter" property="brokeCount"/>/<ano:write name="counter" property="heldCount"/>)</td>
						</tr>
					</ano:iterate>				
			</table>
			&nbsp;<br/>
			</ano:present>
			<ano:present name="totals.defends">
				<table width="100%" cellpadding="2" cellspacing="2" border="0">
					<tr class="lineCaptions">
						<ano:present name="filterDefenderParam" scope="request">
							<td colspan="2">Defends (Total: <a href="filterNews?<ano:write name="filterDefenderParam"/>"><ano:write name="totals.defends.total"/></a>,&nbsp;Held: <a href="filterNews?<ano:write name="filterDefenderParam"/>&pHeldOnly=true"><ano:write name="totals.defends.total.held"/></a>)</td>
						</ano:present>
						<ano:notPresent name="filterDefenderParam" scope="request">
							<td colspan="2">Defends (Total: <ano:write name="totals.defends.total"/>,&nbsp;Held: <ano:write name="totals.defends.total.held"/>)</td>
						</ano:notPresent>
					</tr>
					<ano:iterate name="totals.defends" type="net.anotheria.marsnews.presentation.bean.AttackTypeCounterBean" id="counter" indexId="i">
						<tr class="<%=i.intValue()%2==1 ? "lineDark" : "lineLight"%> ">	
							<td>
								<ano:present name="filterDefenderParam" scope="request">
									<a href="filterNews?pType=<ano:write name="counter" property="type"/>&<ano:write name="filterDefenderParam"/>"><ano:write name="counter" property="type"/></a>:&nbsp;
								</ano:present>
								<ano:notPresent name="filterDefenderParam" scope="request">
									<ano:write name="counter" property="type"/>:&nbsp;
								</ano:notPresent>
							</td>
							<td><ano:write name="counter" property="count"/>&nbsp;(<ano:write name="counter" property="brokeCount"/>/<ano:write name="counter" property="heldCount"/>)</td>
						</tr>
					</ano:iterate>				
			</table>
			&nbsp;<br/>
			</ano:present>
			<ano:present name="rankedCountry">
			<h3>Country Info</h3>
				<table width="100%" cellpadding="2" cellspacing="2" border="0">
					<tr class="lineCaptions">
						<td colspan="2">
							<ano:equal name="rankedCountry" property="dead" value="true"><font color="red"></ano:equal>
							<ano:write name="rankedCountry" property="goverment"/>&nbsp;
							<ano:write name="rankedCountry" property="name"/>(#<ano:write name="rankedCountry" property="countryId"/>)
							<ano:equal name="rankedCountry" property="dead" value="true"></font></ano:equal>
						</td>
					</tr>
					<tr class="lineLight">
						<td>Networth:&nbsp;</td>
						<td><ano:write name="rankedCountry" property="networth"/></td>
					</tr>
					<tr class="lineDark">
						<td>Land:&nbsp;</td>
						<td><ano:write name="rankedCountry" property="land"/></td>
					</tr>
					<tr class="lineLight">
						<td>Rank:&nbsp;</td>
						<td><ano:write name="rankedCountry" property="rank"/></td>
					</tr>
					<ano:equal name="rankedCountry" property="gdi" value="true">
					<tr class="lineDark" >
						<td colspan="2">GDI</td>
					</tr>
					</ano:equal>
					<ano:equal name="rankedCountry" property="underProtection" value="true">
					<tr class="lineDark" >
						<td colspan="2">Under protection</td>
					</tr>
					</ano:equal>
				</table>
			&nbsp;<br/>
			</ano:present>
		</td>
	</tr>
</table>
<jsp:include page="Footer.jsp" flush="false"/>
</body>
</html>

