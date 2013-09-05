<%@ page language="java" contentType="text/html;charset=UTF-8" session="true"
%><%@ taglib prefix="ano" uri="http://www.anotheria.net/ano-tags"
%>
<div class="footer">
	<span>
		<ano:present name="genTimestamp">
			Generated at <ano:write name="genTimestamp"/>
		</ano:present>
		<ano:present name="lastNewsEntry">
			, lastNews: <ano:write name="lastNewsEntry"/>
		</ano:present>
	</span>
	<a href="http://www.anotheria.net"><img src="../images/powered.gif" alt="Hosted and developed by anotheria.net"/></a>
	<div class="clear"></div>
</div>
<!-- google analytics -->
<script src="http://www.google-analytics.com/urchin.js" type="text/javascript">
</script>
<script type="text/javascript">
_uacct = "UA-2363606-1";
urchinTracker();
</script>
<!-- /google analytics -->
