<html>
<head>
    <title>Facebook Albums</title>
    <meta name='layout' content='facebookLayout'/>
</head>

<body>
	<h3>Your Facebook Photo Albums</h3>

	<ul class="albums">
		<g:each in="${friends}" var="friend">
			<li><g:link action="album" controller="springSocialFacebook" id="${album.id}"/>${album.name}</g:link></li>
		</g:each>
	</ul>

</body>
</html>
