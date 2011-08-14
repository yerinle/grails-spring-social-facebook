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
package grails.plugins.springsocial.config.facebook

import javax.inject.Inject

import org.springframework.social.connect.Connection
import org.springframework.social.connect.ConnectionFactoryLocator
import org.springframework.social.connect.ConnectionRepository
import org.springframework.social.connect.support.ConnectionFactoryRegistry

import grails.plugins.springsocial.facebook.SpringSocialFacebookUtils
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.social.facebook.api.Facebook
import org.springframework.social.facebook.api.impl.FacebookTemplate
import org.springframework.social.facebook.connect.FacebookConnectionFactory

@Configuration
class FacebookConfig {
    @Inject
    ConnectionFactoryLocator connectionFactoryLocator
    @Inject
    ConnectionRepository connectionRepository

    @Bean
    String fooFacebook() {
        println "Configuring SpringSocial Facebook"
        def facebookConfig = SpringSocialFacebookUtils.config.facebook
        def clientId = facebookConfig.clientId
        def clientSecret = facebookConfig.clientSecret
        ((ConnectionFactoryRegistry) connectionFactoryLocator).addConnectionFactory(new FacebookConnectionFactory(clientId, clientSecret))
        "fooFacebook"
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public Facebook facebook() {
        Connection<Facebook> facebook = connectionRepository.findPrimaryConnection(Facebook)
        facebook != null ? facebook.getApi() : new FacebookTemplate()
    }
}
