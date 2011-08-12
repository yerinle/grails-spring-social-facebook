<html>
<head>
    <title>Facebook Connect</title>
    <meta name='layout' content='facebookLayout'/>
</head>

<body>
	
	<h3>Connect to Facebook</h3>
	
	<g:form method="POST" mapping="springSocialConnect" params="[providerId:'facebook']">
	    <input type="hidden" name="scope" value="publish_stream,user_photos,offline_access" />
		<div class="formInfo">
			<p>You aren't connected to Facebook yet. Click the button to connect Spring Social Showcase with your Facebook account.</p>
		</div>
		<p>
			<button type="submit"><img
		            src="${createLinkTo(dir: 'images/springsocial/facebook', file: 'connect_light_medium_short.gif')}"/></button>
			</p>
		
		
	</g:form>


</body>
</html>
