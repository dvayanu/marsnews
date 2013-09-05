<%@ page language="java" contentType="text/html;charset=UTF-8" session="true"
%><%@ taglib prefix="ano" uri="http://www.anotheria.net/ano-tags"
%>
<ul class="filter">
	<li class="first">Clans:</li>
	<ano:iterate name="clans" id="clan" type="java.lang.String">
		<li><a href="<ano:write name="clanLink"/>?pClanId=<ano:write name="clan"/>"><ano:write name="clan"/></a></li>
	</ano:iterate>
</ul>
