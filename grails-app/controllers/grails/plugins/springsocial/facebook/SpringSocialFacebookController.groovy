/* Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package grails.plugins.springsocial.facebook

import grails.plugins.springsocial.UserConnection;

import org.springframework.social.connect.ConnectionKey
import org.springframework.social.facebook.api.impl.FacebookTemplate

class SpringSocialFacebookController {
	def facebook
	def connectionRepository
	def index = {
		if (isConnected()) {
			def fb=facebook.userOperations()
			try{
				def model = ["profile": fb.getUserProfile(session.providerUserId.toString()),"token":fb.restTemplate.requestFactory.accessToken]
				render(view: SpringSocialFacebookUtils.config.facebook.page.profile, model: model)
			}catch (Exception e){
				render(view: SpringSocialFacebookUtils.config.facebook.page.connect)
			}
		} else {
			render(view: SpringSocialFacebookUtils.config.facebook.page.connect)
		}
	}
	def profilePhoto = {
		if (isConnected()) {
			response.outputStream << facebook.userOperations().getUserProfileImage(mediaOperations)
		}
	}
	def feed = {
		if (isConnected()) {

			try{
				def model = ['feed': facebook.feedOperations().getFeed()]
				render(view: SpringSocialFacebookUtils.config.facebook.page.feed, model: model)
			}catch (Exception e){
				render(view: SpringSocialFacebookUtils.config.facebook.page.connect)
			}
		} else {
			render(view: SpringSocialFacebookUtils.config.facebook.page.connect)
		}
	}

	def update = {
		if (isConnected()) {
			try{
				def message = params.id ?: params.message
				facebook.feedOperations().updateStatus(message);
				redirect(action: feed)
			}catch (Exception e){
				render(view: SpringSocialFacebookUtils.config.facebook.page.connect)
			}
		} else {
			render(view: SpringSocialFacebookUtils.config.facebook.page.connect)
		}
	}

	def friends = {
		if (isConnected()) {

			try{
				def model = ["friends": facebook.friendOperations().getFriendProfiles()]
				render(view: SpringSocialFacebookUtils.config.facebook.page.friends, model: model)
			}catch (Exception e){
				render(view: SpringSocialFacebookUtils.config.facebook.page.connect)
			}
		} else {
			render(view: SpringSocialFacebookUtils.config.facebook.page.connect)
		}
	}

	def albums = {
		if (isConnected()) {

			try{
				def model = ["albums": facebook.mediaOperations().getAlbums()]
				render(view: SpringSocialFacebookUtils.config.facebook.page.albums, model: model)
			}catch (Exception e){
				render(view: SpringSocialFacebookUtils.config.facebook.page.connect)
			}
		} else {
			render(view: SpringSocialFacebookUtils.config.facebook.page.connect)
		}
	}

	def album = {
		if (isConnected()) {

			try{
				def albumId = params.id ?: params.albumId
				def model = [:]
				model.album = facebook.mediaOperations().getAlbum(albumId)
				model.photos = facebook.mediaOperations().getPhotos(albumId)

				render(view: SpringSocialFacebookUtils.config.facebook.page.album, model: model)
			}catch (Exception e){
				render(view: SpringSocialFacebookUtils.config.facebook.page.connect)
			}
		} else {
			render(view: SpringSocialFacebookUtils.config.facebook.page.connect)
		}
	}

	Boolean isConnected() {
		if(session.providerUserId!=null){
			def providerId = session.providerId
			def providerUserId = session.providerUserId
			ConnectionKey ck = new ConnectionKey(providerId,providerUserId);
			connectionRepository.getConnection(ck)
			def u = UserConnection.findByProviderUserIdAndProviderId(providerUserId,providerId)
			facebook= new FacebookTemplate(u.accessToken);
		}else{
			return false
		}
	}
}