<%@ page language="java" contentType="text/xml;charset=UTF-8" session="true"
%><%@ taglib prefix="ano" uri="http://www.anotheria.net/ano-tags"
%><?xml version="1.0" encoding="UTF-8"?>
<news generated="<ano:write name="genTimestamp"/>" size="<%=((java.util.List)request.getAttribute("entries")).size()%>">
	<ano:iterate name="entries" type="net.anotheria.marsnews.presentation.bean.NewsItemBean" id="entry" indexId="i">
		<newsitem id="<ano:write name="entry" property="obfuscatedId"/>">
			<type><ano:write name="entry" property="type"/></type>
			<date><ano:write name="entry" property="date"/></date>
			<attackerId><ano:write name="entry" property="attackerId"/></attackerId>
			<attacker><ano:write name="entry" property="attacker"/></attacker>
			<ano:equal name="entry" property="attackerTagged" value="true">
				<attackerClan><ano:write name="entry" property="attackerClan"/></attackerClan>
			</ano:equal>
			<ano:notEqual name="entry" property="attackerTagged" value="true">
				<attackerClan/>
			</ano:notEqual>
			<defenderId><ano:write name="entry" property="defenderId"/></defenderId>
			<defender><ano:write name="entry" property="defender"/></defender>
			<ano:equal name="entry" property="defenderTagged" value="true">
				<defenderClan><ano:write name="entry" property="defenderClan"/></defenderClan>
			</ano:equal>
			<ano:notEqual name="entry" property="defenderTagged" value="true">
				<defenderClan/>
			</ano:notEqual>
			<kill><ano:write name="entry" property="kill"/></kill>
			<held><ano:write name="entry" property="held"/></held>
			<result><ano:write name="entry" property="result"/></result>
		</newsitem>
	</ano:iterate>
</news>
