<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<jsp:include page="header.html"/>
    	<jsp:include page="gwt-ccs.css"/>	
    	<script type="text/javascript" language="javascript" src="<%= request.getParameter("gwtModule") %>/<%= request.getParameter("gwtModule") %>.nocache.js"></script>
	</head>
	<body>
		<jsp:include page="logo.html"/>
		<center>
			<div id="gwtCrappyCommentsHook"></div>
			<br><br>
		</center>
	</body>
	<jsp:include page="traker.html"/>
</html>