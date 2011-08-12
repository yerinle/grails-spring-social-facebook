<html>
<head>
    <title>Facebook Profile</title>
    <meta name='layout' content='facebookLayout'/>
</head>

<body>

	<h3>Your Facebook Profile</h3>
	<p>Hello, ${profile.firstName}</p>
	<dl>
		<dt>Facebook ID:</dt>
		<dd>${profile.id}</dd>
		<dt>Name:</dt>
		<dd>${profile.name}</dd>
		<dt>Email:</dt>
		<dd>${profile.email}</dd>
	</dl>

	<c:url value="/connect/facebook" var="disconnectUrl"/>
	<form id="disconnect" action="${disconnectUrl}" method="post">
		<button type="submit">Disconnect from Facebook</button>	
		<input type="hidden" name="_method" value="delete" />
	</form>
	
	<g:form method="DELETE" mapping="springSocialConnect" params="[providerId:'facebook']">
	    <button type="submit">Disconnect from Facebook</button>
	</g:form>


</body>
</html>
