<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<jsp:include page="header.html"/>
    	<jsp:include page="gwt-ccs.css"/>	
    	<script type="text/javascript" language="javascript" src="<%= request.getParameter("gwtModule") %>/<%= request.getParameter("gwtModule") %>.nocache.js"></script>
	</head>
	<body>
		<jsp:include page="logo.html"/>
		<div class="ccs-popularMenu"><a href="comment.jsp">Comments</a> | <a href="host.jsp">Hosts</a> | <a href="url.jsp">Urls</a> | <a href="image.jsp">Images</a> </div>
		<center>
			<div id="gwtCrappyCommentsHook"></div>
		</center>
		<br><br>
	</body>
	<jsp:include page="traker.html"/>
</html>