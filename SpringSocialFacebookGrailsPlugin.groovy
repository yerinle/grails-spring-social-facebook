class SpringSocialFacebookGrailsPlugin {
    def version = "0.1.3"
    def grailsVersion = "1.3 > *"
    def dependsOn = [springSocialCore: '0.1.25 > *']
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp",
			"web-app/js",
			"web-app/images/**",
			"web-app/images/skin/**"
    ]

    // TODO Fill in these fields
    def author = "Domingo Suarez Torres"
    def authorEmail = "domingo.suarez@gmail.com"
    def title = "Spring Social Facebook"
    def description = '''\\
Spring Social Facebook plugin.
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/spring-social-facebook"

    def doWithSpring = {
        xmlns context: "http://www.springframework.org/schema/context"
        context.'component-scan'('base-package': "grails.plugins.springsocial.config.facebook")
    }

}
