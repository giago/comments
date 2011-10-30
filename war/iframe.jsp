<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<jsp:include page="fragment/header.html"/>
    	<jsp:include page="fragment/gwt-ccs.css"/>	
    	<script type="text/javascript" language="javascript" src="iframe/iframe.nocache.js"></script>
    	<style>#iframeAds{width:500px;text-align:right;}#iframeTitle{font-size:10px; margin-bottom:-15px;} #iframeTitle a {}</style>
	</head>
	<body>
		<center>
			<div id="iframeAds"><div id="iframeTitle">by <a href="http://crappycomments.appspot.com/" target="_blanck">crappycomments</a></div></div>
			<div id="gwtCrappyCommentsHook"></div>
		</center>
	</body>
	<jsp:include page="fragment/traker.html"/>
</html>