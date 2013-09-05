<%@ page language="java" contentType="text/html;charset=iso-8859-15" session="true"
%><%@ taglib prefix="ano" uri="http://www.anotheria.net/ano-tags"
%>
<ano:present name="rankedCountries">
<table width="100%" cellpadding="0" cellspacing="0" border="0" class="horizontal">
	<thead>
	<tr>
		<ano:present name="scores.ShowDate" scope="request"><th align="center">Date</th></ano:present>
		<th align="center">Rank</th>
		<th align="center">Country</th>
		<th align="center">Land</th>
		<th align="center">Networth</th>
		<th align="center">Goverment</th>
		<th align="center">Clan</th>
		<th align="center">GDI</th>
		<ano:present name="scores.ShowNewsLink" scope="request"><th>&nbsp;</th></ano:present>
		<ano:present name="scores.ShowHistoryLink" scope="request"><th>&nbsp;</th></ano:present>
	</tr>
	</thead><tbody>
	<ano:iterate name="rankedCountries" type="net.anotheria.marsnews.presentation.bean.RankedCountryBean" id="country" indexId="i">
		<tr class="<%= i%2==1 ? "light" : ""%>">	
			<ano:present name="scores.ShowDate" scope="request"><td><ano:write name="country" property="date"/></td></ano:present>
			<td><ano:write name="country" property="rank"/></td>
			<td>
				<ano:equal name="country" property="dead" value="true"><font color="red"></ano:equal>
				<ano:equal name="country" property="underProtection" value="true"><font color="green"></ano:equal>
				<ano:write name="country" property="name"/>(#<ano:write name="country" property="countryId"/>)
				<ano:equal name="country" property="dead" value="true"></font></ano:equal>
				<ano:equal name="country" property="underProtection" value="true"></font></ano:equal>
			</td>
			<td><ano:write name="country" property="land"/></td>
			<td><ano:write name="country" property="networth"/></td>
			<td><ano:write name="country" property="goverment"/></td>
			<td>
				<ano:notEqual name="country" property="clan" value="">
					<a href="statsForClan?pClanId=<ano:write name="country" property="clan"/>"><ano:write name="country" property="clan"/></a>
				</ano:notEqual>
				<ano:equal name="country" property="clan" value="">
					<ano:write name="country" property="clan"/>
				</ano:equal>
			</td>
			<td align="center"><ano:equal name="country" property="gdi" value="true">X</ano:equal></td>
			<ano:present name="scores.ShowNewsLink" scope="request"><td><a href="newsForCountry?pCountryId=<ano:write name="country" property="countryId"/>&pInterval=24h">24H News</a></td></ano:present>
			<ano:present name="scores.ShowHistoryLink" scope="request"><td><a href="scoreshistory?pCountryId=<ano:write name="country" property="countryId"/>">History</a></td></ano:present>
		</tr>
	</ano:iterate>
	</tbody>
</table>
</ano:present>