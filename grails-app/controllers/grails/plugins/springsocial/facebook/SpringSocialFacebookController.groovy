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

import grails.plugins.springsocial.facebook.SpringSocialFacebookUtils
import org.springframework.social.facebook.api.Facebook
import org.springframework.social.connect.Connection

class SpringSocialFacebookController {
	def facebook
    def connectionRepository

    def index = {
        if (isConnected()) {
            def model = ["profile": facebook.userOperations().getUserProfile()]
            render(view: SpringSocialFacebookUtils.config.facebook.page.profile, model: model)
        } else {
            render(view: SpringSocialFacebookUtils.config.facebook.page.connect)
        }
    }

	def feed = {
		if (isConnected()) {
			def model = ['feed': facebook.feedOperations().getFeed()]
			render(view: SpringSocialFacebookUtils.config.facebook.page.feed, model: model)
        } else {
            render(view: SpringSocialFacebookUtils.config.facebook.page.connect)
        }
	}
	
	def update = {
		if (isConnected()) {
			def message = params.id ?: params.message
			facebook.feedOperations().updateStatus(message);
			redirect(action:feed)
        } else {
            render(view: SpringSocialFacebookUtils.config.facebook.page.connect)
        }
	}
	
	def friends = {
		if (isConnected()) {
			def model = ["friends": facebook.friendOperations().getFriendProfiles()]
			render(view: SpringSocialFacebookUtils.config.facebook.page.friends, model: model)
        } else {
            render(view: SpringSocialFacebookUtils.config.facebook.page.connect)
        }
	}
	
	def albums = {
		if (isConnected()) {
			def model = ["albums": facebook.mediaOperations().getAlbums()]
			render(view: SpringSocialFacebookUtils.config.facebook.page.albums, model: model)
        } else {
            render(view: SpringSocialFacebookUtils.config.facebook.page.connect)
        }
	}
	
	def album = {
		if (isConnected()) {
			def albumId = params.id ?: params.albumId
			
			def model = [:]
			model.album = facebook.mediaOperations().getAlbum(albumId)
			model.photos = facebook.mediaOperations().getPhotos(albumId)
			
			render(view: SpringSocialFacebookUtils.config.facebook.page.album, model: model)
        } else {
            render(view: SpringSocialFacebookUtils.config.facebook.page.connect)
        }
	}
	
    Boolean isConnected() {
		connectionRepository.findPrimaryConnection(Facebook)
    }
}
