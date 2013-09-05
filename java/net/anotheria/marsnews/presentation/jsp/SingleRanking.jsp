<%@ page language="java" contentType="text/html;charset=UTF-8" session="true"
%><%@ taglib prefix="ano" uri="http://www.anotheria.net/ano-tags"
%>
<table border="0" cellpadding="0" cellspacing="0" class="horizontal">
	<thead>
	<tr>
		<th colspan="4" align="center"> Totals </th>
	</tr>
	</thead><tbody>
	<tr class="">
		<td>Total networth</td><td><ano:write name="ranking" property="networth"/></td>
		<td>Average networth</td><td><ano:write name="ranking" property="averageNetworth"/></td>
	</tr>
	<tr class="light">
		<td>Total land</td><td><ano:write name="ranking" property="land"/></td>
		<td>Average land</td><td><ano:write name="ranking" property="averageLand"/></td>
	</tr>
	<tr class="">
		<td>Total countries</td><td><ano:write name="ranking" property="totalCountries"/></td>
		<td>Active countries</td><td><ano:write name="ranking" property="activeCountries"/></td>
	</tr>
	<tr class="light">
		<td>NW/Land Ratio</td><td><ano:write name="ranking" property="nwLandRatio"/></td>
		<td>Countries in GDI</td><td><ano:write name="ranking" property="gdiCountries"/></td>
	</tr>
	<tr class="">
		<td>Countries under protection</td><td><ano:write name="ranking" property="countriesUnderProtection"/></td>
		<td>Dead countries</td><td><ano:write name="ranking" property="deadCountries"/></td>
	</tr>
	<tr class="light">
		<td>TOP 10 Countries</td><td><ano:write name="ranking" property="top10Countries"/></td>
		<td>TOP 25 Countries</td><td><ano:write name="ranking" property="top25Countries"/></td>
	</tr>
	<tr class="">
		<td>TOP 50 Countries</td><td><ano:write name="ranking" property="top50Countries"/></td>
		<td>TOP 100 Countries</td><td><ano:write name="ranking" property="top100Countries"/></td>
	</tr>
	</tbody>
</table>

