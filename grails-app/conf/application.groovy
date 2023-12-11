

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'hrapp.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'hrapp.UserRole'
grails.plugin.springsecurity.authority.className = 'hrapp.Role'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
    //Stateless chain
    [
        pattern: '/api/**',
        filters: 'JOINED_FILTERS,-anonymousAuthenticationFilter,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter,-rememberMeAuthenticationFilter'
    ],

    //Traditional, stateful chain
    [
        pattern: '/stateful/**',
        filters: 'JOINED_FILTERS,-restTokenValidationFilter,-restExceptionTranslationFilter'
    ]
]

grails.plugin.springsecurity.rest.token.storage.gorm.tokenDomainClassName = 'hrapp.AuthenticationToken'