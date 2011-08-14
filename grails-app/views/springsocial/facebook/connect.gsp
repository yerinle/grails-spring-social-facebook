<html>
<head>
    <title>Facebook Connect</title>
    <meta name='layout' content='facebookLayout'/>
</head>

<body>
	
	<h3>Connect to Facebook</h3>
	
	<g:form method="POST" mapping="springSocialConnect" params="[providerId:'facebook']">
	    <input type="hidden" name="scope" value="user_about_me,user_activities,user_birthday,user_checkins,user_education_history,user_events,user_groups,user_hometown,user_interests,user_likes,user_location,user_notes,user_online_presence,user_photo_video_tags,user_photos,user_relationships,user_relationship_details,user_religion_politics,user_status,user_videos,user_website,user_work_history,email,read_friendlists,read_insights,read_mailbox,read_requests,read_stream,xmpp_login,ads_management,create_event,manage_friendlists,manage_notifications,offline_access,publish_checkins,publish_stream,rsvp_event,sms,manage_pages" />
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
