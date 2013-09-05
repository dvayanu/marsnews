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
<h2>War report <ano:write name="sideA"/> against <ano:write name="sideB"/> </h2>		
<div class="content">
<div class="top">
	<div></div>
</div>
<div class="in">
	<div class="in_r">
<table width="100%" cellpadding="0" cellspacing="0" border="0" class="horizontal">
	<thead>
	<tr >
		<th>SideA: <ano:iterate name="sideA" type="java.lang.String" id="clan"><ano:write name="clan"/> </ano:iterate></th>
		<th width="1%">&nbsp;</th>
		<th>SideB: <ano:iterate name="sideB" type="java.lang.String" id="clan"><ano:write name="clan"/> </ano:iterate></th>
	</tr>
	</thead>
	<tr><!-- Side A OVerview -->
		<td valign="top">
			<table width="100%" cellpadding="0" cellspacing="0" border="0" class="horizontal">
				<thead>
				<tr>
					<th><b>Overview</b></th>
					<th>Kills</th>
					<th>Targets</th>
					<th>Hits</th>
					<th>Success. Hits</th>
					<th>Eff</th>
					<th>HPK</th>
					<th>Countries</th>
					<th>HPC</th>
					<th>KPC</th>
				</tr>
				</thead>
				<tbody>
				<ano:iterate name="overview.sideA" type="net.anotheria.marsnews.presentation.bean.WarReportOverviewBean" id="entry" indexId="i">
					<tr class="<%= i%2==1 ? "light" : ""%>">
						<td><ano:write name="entry" property="key"/></td>
						<td align="right"><ano:write name="entry" property="kills"/></td>
						<td align="right"><ano:write name="entry" property="targets"/></td>
						<td align="right"><ano:write name="entry" property="totalHits"/></td>
						<td align="right"><ano:write name="entry" property="successfulHits"/></td>
						<td align="right"><ano:write name="entry" property="efficency"/></td>
						<td align="right"><ano:write name="entry" property="hitsPerKill"/></td>
						<td align="right"><ano:write name="entry" property="attackers"/></td>
						<td align="right"><ano:write name="entry" property="hitsPerAttacker"/></td>
						<td align="right"><ano:write name="entry" property="killsPerAttacker"/></td>
					</tr>
				</ano:iterate>
				</tbody>
			</table>
		</td>			
			
		<td width="1%">&nbsp;</td>

		<td valign="top">
			<table width="100%" cellpadding="0" cellspacing="0" border="0" class="horizontal">
				<thead>
				<tr>
					<th><b>Overview</b></th>
					<th>Kills</th>
					<th>Targets</th>
					<th>Hits</th>
					<th>Success. Hits</th>
					<th>Eff</th>
					<th>HPK</th>
					<th>Countries</th>
					<th>HPC</th>
					<th>KPC</th>
				</tr>
				</thead>
				<tbody>
				<ano:iterate name="overview.sideB" type="net.anotheria.marsnews.presentation.bean.WarReportOverviewBean" id="entry" indexId="i">
					<tr class="<%= i%2==1 ? "light" : ""%>">
						<td><ano:write name="entry" property="key"/></td>
						<td align="right"><ano:write name="entry" property="kills"/></td>
						<td align="right"><ano:write name="entry" property="targets"/></td>
						<td align="right"><ano:write name="entry" property="totalHits"/></td>
						<td align="right"><ano:write name="entry" property="successfulHits"/></td>
						<td align="right"><ano:write name="entry" property="efficency"/></td>
						<td align="right"><ano:write name="entry" property="hitsPerKill"/></td>
						<td align="right"><ano:write name="entry" property="attackers"/></td>
						<td align="right"><ano:write name="entry" property="hitsPerAttacker"/></td>
						<td align="right"><ano:write name="entry" property="killsPerAttacker"/></td>
					</tr>
				</ano:iterate>
				</tbody>
			</table>
		</td>			
	</tr>
	<tr><!-- Side A OVerview -->
		<td valign="top">
			<table width="100%" cellpadding="0" cellspacing="0">
				<thead>
				<tr>
					<th><b>Totals</b></th>
					<th>Kills</th>
					<th>Targets</th>
					<th>Hits</th>
					<th>Success. Hits</th>
					<th>Eff</th>
					<th>HPK</th>
					<th>Countries</th>
					<th>HPC</th>
					<th>KPC</th>
				</tr>
				</thead>
				<tbody>
				<tr class="light">
					<td>Side A vs. Side B</td>
					<td align="right"><ano:write name="overview.sideA.cumulated" property="kills"/></td>
					<td align="right"><ano:write name="overview.sideA.cumulated" property="targets"/></td>
					<td align="right"><ano:write name="overview.sideA.cumulated" property="totalHits"/></td>
					<td align="right"><ano:write name="overview.sideA.cumulated" property="successfulHits"/></td>
					<td align="right"><ano:write name="overview.sideA.cumulated" property="efficency"/></td>
					<td align="right"><ano:write name="overview.sideA.cumulated" property="hitsPerKill"/></td>
					<td align="right"><ano:write name="overview.sideA.cumulated" property="attackers"/></td>
					<td align="right"><ano:write name="overview.sideA.cumulated" property="hitsPerAttacker"/></td>
					<td align="right"><ano:write name="overview.sideA.cumulated" property="killsPerAttacker"/></td>
				</tr>
				</tbody>
			</table>
		</td>			
			
		<td width="1%">&nbsp;</td>

		<td valign="top">
			<table width="100%" cellpadding="0" cellspacing="0">
				<thead>
				<tr>
					<th><b>Totals</b></th>
					<th>Kills</th>
					<th>Targets</th>
					<th>Hits</th>
					<th>Success. Hits</th>
					<th>Eff</th>
					<th>HPK</th>
					<th>Countries</th>
					<th>HPC</th>
					<th>KPC</th>
				</tr>
				</thead>
				<tbody>
				<tr class="light">
					<td>Side B vs. Side A</td>
					<td align="right"><ano:write name="overview.sideB.cumulated" property="kills"/></td>
					<td align="right"><ano:write name="overview.sideB.cumulated" property="targets"/></td>
					<td align="right"><ano:write name="overview.sideB.cumulated" property="totalHits"/></td>
					<td align="right"><ano:write name="overview.sideB.cumulated" property="successfulHits"/></td>
					<td align="right"><ano:write name="overview.sideB.cumulated" property="efficency"/></td>
					<td align="right"><ano:write name="overview.sideB.cumulated" property="hitsPerKill"/></td>
					<td align="right"><ano:write name="overview.sideB.cumulated" property="attackers"/></td>
					<td align="right"><ano:write name="overview.sideB.cumulated" property="hitsPerAttacker"/></td>
					<td align="right"><ano:write name="overview.sideB.cumulated" property="killsPerAttacker"/></td>
				</tr>
				</tbody>
			</table>
		</td>			
	</tr>
	<tr><td colspan="3">&nbsp;</td></tr>
	<!-- Attack Details -->
	<tr>
		<td valign="top">
			<table width="100%" cellpadding="0" cellspacing="0">
				<thead>
				<tr>
					<th>Attack details</th>
					<th>Attacks</th>
					<th>SS</th>
					<th>PS</th>
					<th>GS</th>
					<th>BR</th>
					<th>AB</th>
					<th>NM</th>
					<th>CM</th>
					<th>EM</th>
				</tr>
				</thead>
				<tbody>
				<ano:iterate name="attacks.sideA" type="net.anotheria.marsnews.presentation.bean.WarReportAttackDetailBean" id="entry" indexId="i">
					<tr class="<%= (i&1)==0 ? "light" : ""%>">
						<td><ano:write name="entry" property="key"/></td>
						<td align="right"><ano:write name="entry" property="total"/></td>
						<td align="right"><ano:write name="entry" property="ss"/></td>
						<td align="right"><ano:write name="entry" property="ps"/></td>
						<td align="right"><ano:write name="entry" property="gs"/></td>
						<td align="right"><ano:write name="entry" property="br"/></td>
						<td align="right"><ano:write name="entry" property="ab"/></td>
						<td align="right"><ano:write name="entry" property="nm"/></td>
						<td align="right"><ano:write name="entry" property="cm"/></td>
						<td align="right"><ano:write name="entry" property="em"/></td>
					</tr>
				</ano:iterate>
				</tbody>
			</table>
		</td>			
		<td width="1%">&nbsp;</td>
		<td>
			<table width="100%" cellpadding="0" cellspacing="0">
				<thead>
				<tr>
					<th>Attack details</th>
					<th>Attacks</th>
					<th>SS</th>
					<th>PS</th>
					<th>GS</th>
					<th>BR</th>
					<th>AB</th>
					<th>NM</th>
					<th>CM</th>
					<th>EM</th>
				</tr>
				</thead>
				<tbody>
				<ano:iterate name="attacks.sideB" type="net.anotheria.marsnews.presentation.bean.WarReportAttackDetailBean" id="entry" indexId="i">
					<tr class="<%= (i&1)==0 ? "light" : ""%>">
						<td><ano:write name="entry" property="key"/></td>
						<td align="right"><ano:write name="entry" property="total"/></td>
						<td align="right"><ano:write name="entry" property="ss"/></td>
						<td align="right"><ano:write name="entry" property="ps"/></td>
						<td align="right"><ano:write name="entry" property="gs"/></td>
						<td align="right"><ano:write name="entry" property="br"/></td>
						<td align="right"><ano:write name="entry" property="ab"/></td>
						<td align="right"><ano:write name="entry" property="nm"/></td>
						<td align="right"><ano:write name="entry" property="cm"/></td>
						<td align="right"><ano:write name="entry" property="em"/></td>
					</tr>
				</ano:iterate>
				</tbody>
			</table>
		</td>			
	</tr>
	<tr>
		<td valign="top">
			<table width="100%" cellpadding="0" cellspacing="0">
				<thead>
				<tr>
					<th>Totals</th>
					<th>Attacks</th>
					<th>SS</th>
					<th>PS</th>
					<th>GS</th>
					<th>BR</th>
					<th>AB</th>
					<th>NM</th>
					<th>CM</th>
					<th>EM</th>
				</tr>
				</thead>
				<tbody>
				<tr class="light">
					<td>Side A vs. Side B</td>
					<td align="right"><ano:write name="attacks.sideA.cumulated" property="total"/></td>
					<td align="right"><ano:write name="attacks.sideA.cumulated" property="ss"/></td>
					<td align="right"><ano:write name="attacks.sideA.cumulated" property="ps"/></td>
					<td align="right"><ano:write name="attacks.sideA.cumulated" property="gs"/></td>
					<td align="right"><ano:write name="attacks.sideA.cumulated" property="br"/></td>
					<td align="right"><ano:write name="attacks.sideA.cumulated" property="ab"/></td>
					<td align="right"><ano:write name="attacks.sideA.cumulated" property="nm"/></td>
					<td align="right"><ano:write name="attacks.sideA.cumulated" property="cm"/></td>
					<td align="right"><ano:write name="attacks.sideA.cumulated" property="em"/></td>
				</tr>
				</tbody>
			</table>
		</td>			
		<td width="1%">&nbsp;</td>
		<td valign="top">
			<table width="100%" cellpadding="0" cellspacing="0">
				<thead>
				<tr>
					<th>Totals</th>
					<th>Attacks</th>
					<th>SS</th>
					<th>PS</th>
					<th>GS</th>
					<th>BR</th>
					<th>AB</th>
					<th>NM</th>
					<th>CM</th>
					<th>EM</th>
				</tr>
				</thead>
				<tbody>
				<tr class="light">
					<td>Side B vs. Side A</td>
					<td align="right"><ano:write name="attacks.sideB.cumulated" property="total"/></td>
					<td align="right"><ano:write name="attacks.sideB.cumulated" property="ss"/></td>
					<td align="right"><ano:write name="attacks.sideB.cumulated" property="ps"/></td>
					<td align="right"><ano:write name="attacks.sideB.cumulated" property="gs"/></td>
					<td align="right"><ano:write name="attacks.sideB.cumulated" property="br"/></td>
					<td align="right"><ano:write name="attacks.sideB.cumulated" property="ab"/></td>
					<td align="right"><ano:write name="attacks.sideB.cumulated" property="nm"/></td>
					<td align="right"><ano:write name="attacks.sideB.cumulated" property="cm"/></td>
					<td align="right"><ano:write name="attacks.sideB.cumulated" property="em"/></td>
				</tr>
				</tbody>
			</table>
		</td>			
	</tr>			
	<tr><td colspan="3">&nbsp;</td></tr>
	<!-- Destruction Details -->
	<tr>
		<td valign="top">
			<table width="100%" cellpadding="0" cellspacing="0">
				<thead>
				<tr>
					<th>Destruction</th>
					<th>A total</th>
					<th>A taken</th>
					<th>A destr.</th>
					<th>B total</th>
					<th>B taken</th>
					<th>B destr.</th>
					<th>C killed</th>
					<th>F destr.</th>
				</tr>
				</thead>
				<tbody>
				<ano:iterate name="destruction.sideA" type="net.anotheria.marsnews.presentation.bean.WarReportDestructionDetailBean" id="entry" indexId="i">
					<tr class="<%= (i&1)==0 ? "light" : ""%>">
						<td><ano:write name="entry" property="key"/></td>
						<td align="right"><ano:write name="entry" property="landTotal"/></td>
						<td align="right"><ano:write name="entry" property="landTaken"/></td>
						<td align="right"><ano:write name="entry" property="landDestroyed"/></td>
						<td align="right"><ano:write name="entry" property="buildingsTotal"/></td>
						<td align="right"><ano:write name="entry" property="buildingsTaken"/></td>
						<td align="right"><ano:write name="entry" property="buildingsDestroyed"/></td>
						<td align="right"><ano:write name="entry" property="civiliansDestroyed"/></td>
						<td align="right"><ano:write name="entry" property="foodDestroyed"/></td>
					</tr>
				</ano:iterate>
				</tbody>
			</table>
		</td>			
		<td width="1%">&nbsp;</td>
		<td valign="top">
			<table width="100%" cellpadding="0" cellspacing="0">
				<thead>
				<tr>
					<th>Destruction</th>
					<th>A total</th>
					<th>A taken</th>
					<th>A destr.</th>
					<th>B total</th>
					<th>B taken</th>
					<th>B destr.</th>
					<th>C killed</th>
					<th>F destr.</th>
				</tr>
				</thead>
				<tbody>
				<ano:iterate name="destruction.sideB" type="net.anotheria.marsnews.presentation.bean.WarReportDestructionDetailBean" id="entry" indexId="i">
					<tr class="<%= (i&1)==0 ? "light" : ""%>">
						<td><ano:write name="entry" property="key"/></td>
						<td align="right"><ano:write name="entry" property="landTotal"/></td>
						<td align="right"><ano:write name="entry" property="landTaken"/></td>
						<td align="right"><ano:write name="entry" property="landDestroyed"/></td>
						<td align="right"><ano:write name="entry" property="buildingsTotal"/></td>
						<td align="right"><ano:write name="entry" property="buildingsTaken"/></td>
						<td align="right"><ano:write name="entry" property="buildingsDestroyed"/></td>
						<td align="right"><ano:write name="entry" property="civiliansDestroyed"/></td>
						<td align="right"><ano:write name="entry" property="foodDestroyed"/></td>
					</tr>
				</ano:iterate>
				</tbody>
			</table>
		</td>			
	</tr>
	<tr>
		<td valign="top">
			<table width="100%" cellpadding="0" cellspacing="0">
				<thead>
				<tr>
					<th>Totals:</th>
					<th>A total</th>
					<th>A taken</th>
					<th>A destr.</th>
					<th>B total</th>
					<th>B taken</th>
					<th>B destr.</th>
					<th>C killed</th>
					<th>F destr.</th>
				</tr>
				</thead>
				<tbody>
				<tr class="light">
					<td>Side A vs. Side B</td>
					<td align="right"><ano:write name="destruction.sideA.cumulated" property="landTotal"/></td>
					<td align="right"><ano:write name="destruction.sideA.cumulated" property="landTaken"/></td>
					<td align="right"><ano:write name="destruction.sideA.cumulated" property="landDestroyed"/></td>
					<td align="right"><ano:write name="destruction.sideA.cumulated" property="buildingsTotal"/></td>
					<td align="right"><ano:write name="destruction.sideA.cumulated" property="buildingsTaken"/></td>
					<td align="right"><ano:write name="destruction.sideA.cumulated" property="buildingsDestroyed"/></td>
					<td align="right"><ano:write name="destruction.sideA.cumulated" property="civiliansDestroyed"/></td>
					<td align="right"><ano:write name="destruction.sideA.cumulated" property="foodDestroyed"/></td>
				</tr>
				</tbody>
			</table>
		</td>			
		<td width="1%">&nbsp;</td>
		<td valign="top">
			<table width="100%" cellpadding="0" cellspacing="0">
				<thead>
				<tr>
					<th>Totals:</th>
					<th>A total</th>
					<th>A taken</th>
					<th>A destr.</th>
					<th>B total</th>
					<th>B taken</th>
					<th>B destr.</th>
					<th>C killed</th>
					<th>F destr.</th>
				</tr>
				</thead>
				<tbody>
				<tr class="light">
					<td>Side B vs. Side A</td>
					<td align="right"><ano:write name="destruction.sideB.cumulated" property="landTotal"/></td>
					<td align="right"><ano:write name="destruction.sideB.cumulated" property="landTaken"/></td>
					<td align="right"><ano:write name="destruction.sideB.cumulated" property="landDestroyed"/></td>
					<td align="right"><ano:write name="destruction.sideB.cumulated" property="buildingsTotal"/></td>
					<td align="right"><ano:write name="destruction.sideB.cumulated" property="buildingsTaken"/></td>
					<td align="right"><ano:write name="destruction.sideB.cumulated" property="buildingsDestroyed"/></td>
					<td align="right"><ano:write name="destruction.sideB.cumulated" property="civiliansDestroyed"/></td>
					<td align="right"><ano:write name="destruction.sideB.cumulated" property="foodDestroyed"/></td>
				</tr>
				</tbody>
			</table>
		</td>			
	</tr>			
	<!-- Destruction Details END-->
	<tr><td colspan="3" class="ads" align="center">
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
	
	<!-- Kill list -->
	<tr>
		<td valign="top">
			<table width="100%" cellpadding="0" cellspacing="0" border="0" class="horizontal">
				<thead>
				<tr>
					<th colspan="4">Kills by Side A</th>
				</tr>
				</thead>
				<ano:iterate name="kills.sideA" type="net.anotheria.marsnews.presentation.bean.WarReportKillsBean" id="entry">
					<thead>
					<tr>
						<th><ano:write name="entry" property="key"/></th>
						<th>Killed</th>
						<th>Killer</th>
						<th>By</th>
					</tr>
					</thead>
					<tbody>
					<ano:iterate id="kill" name="entry" property="kills" type="net.anotheria.marsnews.presentation.bean.WarReportKillBean" indexId="i">
						<tr class="<%= (i&1)==0 ? "light" : ""%>">
							<td><%=(i+1)%></td>
							<td><ano:write name="kill" property="killedCountry"/></td>
							<td><ano:write name="kill" property="killerCountry"/></td>
							<td><ano:write name="kill" property="killedBy"/></td>
						</tr>
					</ano:iterate>
					</tbody>
					<tr>
						<td colspan="4">&nbsp;</td>
					</tr>
				</ano:iterate>
			</table>
		</td>			
		<td width="1%">&nbsp;</td>
		<td valign="top">
			<table width="100%" cellpadding="0" cellspacing="0" border="0" class="horizontal">
				<thead>
				<tr>
					<th colspan="4">Kills by Side B</th>
				</tr>
				</thead>
				<ano:iterate name="kills.sideB" type="net.anotheria.marsnews.presentation.bean.WarReportKillsBean" id="entry">
					<thead>
					<tr>
						<th><ano:write name="entry" property="key"/></th>
						<th>Killed</th>
						<th>Killer</th>
						<th>By</th>
					</tr>
					</thead>
					<tbody>
					<ano:iterate id="kill" name="entry" property="kills" type="net.anotheria.marsnews.presentation.bean.WarReportKillBean" indexId="i">
						<tr class="<%= (i&1)==0 ? "light" : ""%>">
							<td><%=(i+1)%></td>
							<td><ano:write name="kill" property="killedCountry"/></td>
							<td><ano:write name="kill" property="killerCountry"/></td>
							<td><ano:write name="kill" property="killedBy"/></td>
						</tr>
					</ano:iterate>
					</tbody>
					<tr>
						<td colspan="4">&nbsp;</td>
					</tr>
				</ano:iterate>
			</table>
		</td>			
	</tr>
	<tr><td colspan="3" class="ads" align="center">
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
	
	<!-- Target list -->
	<tr>
		<td valign="top">
			<table width="100%" cellpadding="0" cellspacing="0" border="0" class="horizontal">
				<thead>
				<tr>
					<th colspan="11">Targets attacked by Side A (alive) - Successful attacks / Total attacks</th>
				</tr>
				</thead>
				<ano:iterate name="targets.sideA" type="net.anotheria.marsnews.presentation.bean.WarReportCountryAttackDetailsForSideBean" id="targets">
					<thead>
					<tr>
						<th colspan="2"><ano:write name="targets" property="key"/></th>
						<th>Total</th>
						<th>SS</th>
						<th>PS</th>
						<th>GS</th>
						<th>BR</th>
						<th>AB</th>
						<th>NM</th>
						<th>CM</th>
						<th>EM</th>
					</tr>
					</thead>
					<tbody>
					<ano:iterate id="country" name="targets" property="countries" type="net.anotheria.marsnews.presentation.bean.WarReportCountryAttackDetailsBean" indexId="i">
						<tr class="<%= (i&1)==0 ? "light" : ""%>" title="Damage: <ano:write name="country" property="damage"/>">
							<td><%=(i+1)%></td>
							<td><ano:write name="country" property="countryDescription"/></td>
							<td align="right"><ano:write name="country" property="totalDesc"/></td>
							<td align="right"><ano:write name="country" property="ssDesc"/></td>
							<td align="right"><ano:write name="country" property="psDesc"/></td>
							<td align="right"><ano:write name="country" property="gsDesc"/></td>
							<td align="right"><ano:write name="country" property="brDesc"/></td>
							<td align="right"><ano:write name="country" property="abDesc"/></td>
							<td align="right"><ano:write name="country" property="nmDesc"/></td>
							<td align="right"><ano:write name="country" property="cmDesc"/></td>
							<td align="right"><ano:write name="country" property="emDesc"/></td>
						</tr>
					</ano:iterate>
					</tbody>
					<tr>
						<td colspan="4">&nbsp;</td>
					</tr>
				</ano:iterate>
			</table>
		</td>			
		<td width="1%">&nbsp;</td>
		<td valign="top">
			<table width="100%" cellpadding="0" cellspacing="0" border="0" class="horizontal">
				<thead>
				<tr>
					<th colspan="11">Targets attacked by Side B (alive) - Successful attacks / Total attacks</th>
				</tr>
				</thead>
				<ano:iterate name="targets.sideB" type="net.anotheria.marsnews.presentation.bean.WarReportCountryAttackDetailsForSideBean" id="targets">
					<thead>
					<tr>
						<th colspan="2"><ano:write name="targets" property="key"/></th>
						<th>Total</th>
						<th>SS</th>
						<th>PS</th>
						<th>GS</th>
						<th>BR</th>
						<th>AB</th>
						<th>NM</th>
						<th>CM</th>
						<th>EM</th>
					</tr>
					</thead>
					<tbody>
					<ano:iterate id="country" name="targets" property="countries" type="net.anotheria.marsnews.presentation.bean.WarReportCountryAttackDetailsBean" indexId="i">
						<tr class="<%= (i&1)==0 ? "light" : ""%>" title="Damage: <ano:write name="country" property="damage"/>">
							<td><%=(i+1)%></td>
							<td><ano:write name="country" property="countryDescription"/></td>
							<td align="right"><ano:write name="country" property="totalDesc"/></td>
							<td align="right"><ano:write name="country" property="ssDesc"/></td>
							<td align="right"><ano:write name="country" property="psDesc"/></td>
							<td align="right"><ano:write name="country" property="gsDesc"/></td>
							<td align="right"><ano:write name="country" property="brDesc"/></td>
							<td align="right"><ano:write name="country" property="abDesc"/></td>
							<td align="right"><ano:write name="country" property="nmDesc"/></td>
							<td align="right"><ano:write name="country" property="cmDesc"/></td>
							<td align="right"><ano:write name="country" property="emDesc"/></td>
						</tr>
					</ano:iterate>
					</tbody>
					<tr>
						<td colspan="4">&nbsp;</td>
					</tr>
				</ano:iterate>
			</table>
		</td>			
	</tr>
	<tr><td colspan="3">&nbsp;</td></tr>

	<!-- Attackers list -->
	<tr>
		<td valign="top">
			<table width="100%" cellpadding="0" cellspacing="0" border="0">
				<thead>
				<tr>
					<th colspan="12">Attackers attacking on Side A  - Successful attacks / Total attacks</th>
				</tr>
				</thead>
				<ano:iterate name="attackers.sideA" type="net.anotheria.marsnews.presentation.bean.WarReportCountryAttackDetailsForSideBean" id="targets">
					<thead>
					<tr>
						<th colspan="2"><ano:write name="targets" property="key"/></th>
						<th>K</th>
						<th>Tot</th>
						<th>SS</th>
						<th>PS</th>
						<th>GS</th>
						<th>BR</th>
						<th>AB</th>
						<th>NM</th>
						<th>CM</th>
						<th>EM</th>
					</tr>
					</thead>
					<tbody>
					<ano:iterate id="country" name="targets" property="countries" type="net.anotheria.marsnews.presentation.bean.WarReportCountryAttackDetailsBean" indexId="i">
						<tr class="<%= (i&1)==0 ? "light" : ""%>" title="Damage: <ano:write name="country" property="damage"/>">
							<td><%=(i+1)%></td>
							<td>
								<ano:equal name="country" property="dead" value="true"><font color="red"></ano:equal>
									<ano:write name="country" property="countryDescription"/>
								<ano:equal name="country" property="dead" value="true"></font></ano:equal>
							</td>
							<td align="right"><ano:write name="country" property="killsDesc"/></td>
							<td align="right"><ano:write name="country" property="totalDesc"/></td>
							<td align="right"><ano:write name="country" property="ssDesc"/></td>
							<td align="right"><ano:write name="country" property="psDesc"/></td>
							<td align="right"><ano:write name="country" property="gsDesc"/></td>
							<td align="right"><ano:write name="country" property="brDesc"/></td>
							<td align="right"><ano:write name="country" property="abDesc"/></td>
							<td align="right"><ano:write name="country" property="nmDesc"/></td>
							<td align="right"><ano:write name="country" property="cmDesc"/></td>
							<td align="right"><ano:write name="country" property="emDesc"/></td>
						</tr>
					</ano:iterate>
					</tbody>
					<tr>
						<td colspan="4">&nbsp;</td>
					</tr>
				</ano:iterate>
				
			</table>
		</td>			
		<td width="1%">&nbsp;</td>
		<td valign="top">
			<table width="100%" cellpadding="0" cellspacing="0" border="0">
				<thead>
				<tr>
					<th colspan="12">Attackers attacking on Side B  - Successful attacks / Total attacks</th>
				</tr>
				</thead>
				<ano:iterate name="attackers.sideB" type="net.anotheria.marsnews.presentation.bean.WarReportCountryAttackDetailsForSideBean" id="targets">
					<thead>
					<tr>
						<th colspan="2"><ano:write name="targets" property="key"/></th>
						<th>K</th>
						<th>Tot</th>
						<th>SS</th>
						<th>PS</th>
						<th>GS</th>
						<th>BR</th>
						<th>AB</th>
						<th>NM</th>
						<th>CM</th>
						<th>EM</th>
					</tr>
					</thead>
					<tbody>
					<ano:iterate id="country" name="targets" property="countries" type="net.anotheria.marsnews.presentation.bean.WarReportCountryAttackDetailsBean" indexId="i">
						<tr class="<%= (i&1)==0 ? "light" : ""%>" title="Damage: <ano:write name="country" property="damage"/>">
							<td><%=(i+1)%></td>
							<td>
								<ano:equal name="country" property="dead" value="true"><font color="red"></ano:equal>
									<ano:write name="country" property="countryDescription"/>
								<ano:equal name="country" property="dead" value="true"></font></ano:equal>
							</td>
							<td align="right"><ano:write name="country" property="killsDesc"/></td>
							<td align="right"><ano:write name="country" property="totalDesc"/></td>
							<td align="right"><ano:write name="country" property="ssDesc"/></td>
							<td align="right"><ano:write name="country" property="psDesc"/></td>
							<td align="right"><ano:write name="country" property="gsDesc"/></td>
							<td align="right"><ano:write name="country" property="brDesc"/></td>
							<td align="right"><ano:write name="country" property="abDesc"/></td>
							<td align="right"><ano:write name="country" property="nmDesc"/></td>
							<td align="right"><ano:write name="country" property="cmDesc"/></td>
							<td align="right"><ano:write name="country" property="emDesc"/></td>
						</tr>
					</ano:iterate>
					<tr>
						<td colspan="4">&nbsp;</td>
					</tr>
				</ano:iterate>
			</table>
		</td>			
	</tr>
	<tr><td colspan="3">&nbsp;</td></tr>

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



