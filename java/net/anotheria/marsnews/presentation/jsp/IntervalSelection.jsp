<%@ page language="java" contentType="text/html;charset=iso-8859-15" session="true"
%><%@ taglib prefix="ano" uri="http://www.anotheria.net/ano-tags"
%>
<ul class="filter">
	<li class="first">Timeline:</li>
	<ano:iterate name="intervals" id="interval" type="net.anotheria.marsnews.presentation.bean.IntervalSelectionBean">
		<ano:equal name="interval" property="selected" value="false">
			<li><a href="<ano:write name="linkToCurrentPage" ignore="true"/>&amp;pInterval=<ano:write name="interval" property="intervalName"/>"><ano:write name="interval" property="intervalName"/></a></li>
		</ano:equal>
		<ano:equal name="interval" property="selected" value="true">
			<li class="active"><a href="#"><ano:write name="interval" property="intervalName"/></a></li>
		</ano:equal>
	</ano:iterate>
	<%--<li class="last"><a href="#">all</a></li>--%>
</ul>

<%--
Timeline:&nbsp;
<ano:iterate name="intervals" id="interval" type="net.anotheria.marsnews.presentation.bean.IntervalSelectionBean">
	<ano:equal name="interval" property="selected" value="false">
		<a href="<ano:write name="linkToCurrentPage" ignore="true"/>&amp;pInterval=<ano:write name="interval" property="intervalName"/>"><ano:write name="interval" property="intervalName"/></a>&nbsp;
	</ano:equal>
	<ano:equal name="interval" property="selected" value="true">
		<b><ano:write name="interval" property="intervalName"/></b>&nbsp;
	</ano:equal>
</ano:iterate>
<br><br>
--%>