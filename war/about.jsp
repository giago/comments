<html>
	<head>
		<jsp:include page="fragment/header.html"/>
		<style type="text/css"> 
		body, table td, select { font-family: Arial Unicode MS, Arial, sans-serif; font-size: small; }
		pre { font-family: "courier new", courier; font-size: small; }
		body { color: black; margin: 0px; border: 0px; padding: 0px; background: #fff; direction: ltr; }
		.info { width: 700px; text-align: justify;} 
		.ccs-menu { position: absolute; top: 3; left: 10;}
		#ccsif { width: 100%; height: 500px; border: 0px; margin: 20px 0px 0px 0px; }
		</style>
	</head>
	<body>
		<center>
			<jsp:include page="fragment/logo.html"/>
			<div class="info" align="center">
				<p>
					With this project we want to offer the ability to add comments to every 
					internet resource that can be indentify with an URL. The best way to 
					add comments is install our Chrome extension. 
					<div style="width:100%;text-align:center;">
						<button type="button" class="btn"
							onclick="window.location.href='https://chrome.google.com/extensions/detail/bobbgkponekhdgkclfgfiodcfndcankk';">
							<span><span>Click here to install the Crappy comments extension</span></span>
						</button>
					</div> 
				</p>
				<p>
					You can add, search, see most commented urls and host.	
					You can add a simple iframe to your website to have specific comments.	
					We are able to implement custom solution to manage filters and other administration 
					tools for specific web site.
				</p>
				<p>
					For more information or feedback please let us know at chickymate@googlemail.com or live a comment
					down here.
				</p>
				<p>More to come: best search engine, better way to show you web sites that are commented, 
					and a list of funny comments
				</p>
				<p>
					<b>Credits</b> : Andrea Boriero - Luigi Agosti
				</p>
			</div>
		</center>
		<div>
			<iframe id="ccsif" src="iframe.jsp?url=http://crappycomments.appspot.com/about.jsp" frameborder="0">Iframes not supported.</iframe>
		</div>
	</body>
	<jsp:include page="fragment/traker.html"/>
</html>