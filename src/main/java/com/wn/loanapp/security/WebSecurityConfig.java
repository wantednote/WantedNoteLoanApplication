package com.wn.loanapp.security;

import java.util.Arrays;
import java.util.List;

import javax.servlet.MultipartConfigElement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.wn.loanapp.security.filter.CustomLoginFilter;
import com.wn.loanapp.security.provider.PartnerAuthenticationProvider;
import com.wn.loanapp.security.provider.UserAuthenticationProvider;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages="com.wn.loanapp.*") 
//@EnableGlobalMethodSecurity( prePostEnabled = true)
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
/*@Autowired	
private PartnerCustomAuthenticationProvider partnerCustomAuthenticationProvider;

@Autowired
private EmplCustomAuthenticationProvider emplCustomAuthenticationProvider;
*/	/**
	 * Declared Logger	
	 */
	private Log log = LogFactory.getLog(this.getClass());
	
	private static final Integer INTEGER_ONE= 1;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	log.info("configure start");
        http
            .authorizeRequests()
            	.antMatchers("/" , "/sessionExpired").permitAll()
            	//.antMatchers("/swagger-ui.html").hasAnyAuthority("SUPER_ADMIN","TECH_ADMIN")
            	//.antMatchers("/v2/api-docs").hasAnyAuthority("SUPER_ADMIN","TECH_ADMIN")
            	.antMatchers("/admin/**").hasAnyAuthority("ADMIN")
            	.antMatchers("/partner/**").hasAnyAuthority("PARTNER")
            	//.antMatchers("/admin/**").hasAnyAuthority("ADMIN" , "SUPER_ADMIN","PRODUCT_MANAGER","SALES","DSA")
            	//.antMatchers("/productmgr/**").hasAnyAuthority("PRODUCT_MANAGER")
            	//.antMatchers("/logout").permitAll()
            	.anyRequest().permitAll()
                .and()
            .formLogin()
                .loginPage("/login")
                .failureHandler(failureHandler())
                //.failureUrl("/login?error=Failure")
                .successHandler(successHandler())
                //.failureHandler(new SimpleUrlAuthenticationFailureHandler("/loginFailure"))
               .permitAll()
                .and()
            .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))/*.logoutSuccessUrl("/loginAdmin")*/
                .deleteCookies("JSESSIONID","remember-me")
                .permitAll()
                .and()
          //.logout().logoutUrl("/signout")
               /* .deleteCookies("JSESSIONID")
                    .invalidateHttpSession( true )*/
                 //.logout()
			     //.addLogoutHandler(customLogoutHandler())
			     //.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .sessionManagement()
	            .maximumSessions(1)
	            .sessionRegistry(sessionRegistry())
	            .expiredUrl("/sessionExpired");
            // .invalidSessionUrl("/login")
        
        /*http.addFilterBefore(customLoginFilter(), UsernamePasswordAuthenticationFilter.class)
		  .addFilterBefore(userAndSmeLogoutFilter(),ConcurrentSessionFilter.class);*/
        http.addFilterBefore(customLoginFilter(), UsernamePasswordAuthenticationFilter.class);
        
        http.csrf().disable();
        //.csrfTokenRepository(csrfTokenRepository());
        http.exceptionHandling().accessDeniedPage("/accessDenied");
       // http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
       log.info("configure end");
    }
    
    // Registering HttpSessionEventPublisher, responsible for removing expired sessions from session registry
    @Bean
    public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
    }
    
    @Bean
    public CustomLoginFilter customLoginFilter() {
    	  CustomLoginFilter filter = new CustomLoginFilter();
	      filter.setAuthenticationManager(authenticationManager());
	      filter.setAuthenticationSuccessHandler(successHandler());
	      filter.setAuthenticationFailureHandler(failureHandler());
	      filter.setSessionAuthenticationStrategy(sessionStrategy());
	      filter.setAllowSessionCreation(true);
	      return filter;
    }
    
    
    // In order to be able to inject SessionRegistry in our beans, we have to define the bean and hand-over it to 
    // the session management 
    @Bean 
    public SessionRegistry sessionRegistry(){
    	SessionRegistry sessionRegistry = new SessionRegistryImpl();
    	return sessionRegistry;
    }
    
    // Registering sessions strategy that delegates to individual strategies
    @Bean
    public CompositeSessionAuthenticationStrategy sessionStrategy(){
    	List<SessionAuthenticationStrategy> strategyList=Arrays.asList(concurrentSesisonStrategy(), sessionFixationStrategy(), sessionRegistrationStrategy());
    	CompositeSessionAuthenticationStrategy delegateStrategies= new CompositeSessionAuthenticationStrategy(strategyList);
    	return delegateStrategies;
    }
    
    // Registering Session Strategies  for handling concurrent sessions
    @Bean
    public ConcurrentSessionControlAuthenticationStrategy concurrentSesisonStrategy(){
    	ConcurrentSessionControlAuthenticationStrategy concurrentSesionStrategy= new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry());
    	concurrentSesionStrategy.setExceptionIfMaximumExceeded(false);
    	concurrentSesionStrategy.setMaximumSessions(INTEGER_ONE);
    	return concurrentSesionStrategy;
    }
    
    // Session strategy for handling session fixation
    @Bean
    public SessionFixationProtectionStrategy sessionFixationStrategy(){
    	SessionFixationProtectionStrategy sessionFixationStrategy= new SessionFixationProtectionStrategy();
    	sessionFixationStrategy.setMigrateSessionAttributes(true);
    	return sessionFixationStrategy;
    }
    
    // session strategy for registering new authenticated session (user)
    // needs reference to SessionRegistryImpl
    @Bean
    public RegisterSessionAuthenticationStrategy sessionRegistrationStrategy(){
    	RegisterSessionAuthenticationStrategy sessionRegistrationStrategy = new RegisterSessionAuthenticationStrategy(sessionRegistry());
    	return sessionRegistrationStrategy;
    }
    
    @Bean
    public CustomLogoutHandler customLogoutHandler() {
        return new CustomLogoutHandler();
    }
    
    @Bean
    public CustomAuthenticationFailureHandler failureHandler(){
    	CustomAuthenticationFailureHandler failureHandler = new CustomAuthenticationFailureHandler("/loginFailure");
    	return failureHandler;
    }
    @Bean
    public AuthenticationManager authenticationManager() {
      return new ProviderManager(Arrays.asList(userCustomAuthenticationProvider(), partnerCustomAuthenticationProvider()));
    }
    
    @Bean
    public AuthenticationSuccessHandler successHandler() {
    	CustomAuthenticationSuccessHandler handler = new CustomAuthenticationSuccessHandler();
        return handler;
    }
  
    @Bean
    public PartnerAuthenticationProvider partnerCustomAuthenticationProvider() {
    	PartnerAuthenticationProvider partnerCustomAuthenticationProvider = new PartnerAuthenticationProvider();
        return partnerCustomAuthenticationProvider;
    }
    
    @Bean
    public UserAuthenticationProvider userCustomAuthenticationProvider() {
    	UserAuthenticationProvider userCustomAuthenticationProvider = new UserAuthenticationProvider();
        return userCustomAuthenticationProvider;
    }
    
   /* @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        List<AuthenticationProvider> authenticationProviderList = new ArrayList<AuthenticationProvider>();
        authenticationProviderList.add(partnerCustomAuthenticationProvider);
        authenticationProviderList.add(emplCustomAuthenticationProvider);
        AuthenticationManager authenticationManager = new ProviderManager(authenticationProviderList);
        return authenticationManager;
    }*/
    /*@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	 System.out.println(" custom method inMemoryAuthentication configureGlobal(AuthenticationManagerBuilder auth)");
        auth
            .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }*/
    
   /* @Bean
    public FilterChainProxy springSecurityFilterChain() throws Exception {
    	 // HttpSessionSecurityContextRepository
        HttpSessionSecurityContextRepository httpSessionSecurityContextRepository = new HttpSessionSecurityContextRepository();
    
    	// SessionRegistry
        SessionRegistry sessionRegistry = new SessionRegistryImpl();
       
        // ConcurrentSessionControlStrategy
        ConcurrentSessionFilter concurrentSessionFilter = new ConcurrentSessionFilter(sessionRegistry , "/");

        // SessionManagementFilter
        SessionManagementFilter sessionManagementFilter = new SessionManagementFilter(httpSessionSecurityContextRepository);
       
    	SecurityFilterChain chain = new DefaultSecurityFilterChain(new AntPathRequestMatcher("/**"),
                concurrentSessionFilter,
                sessionManagementFilter);
        return new FilterChainProxy(chain);
    }*/
    
    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("50MB");
        factory.setMaxRequestSize("100MB");
        return factory.createMultipartConfig();
    }
    
   /* private CsrfTokenRepository csrfTokenRepository() 
    { 
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository(); 
        repository.setSessionAttributeName("_csrf");
        return repository; 
    }*/
   /* @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        log.info(" custom method configure(AuthenticationManagerBuilder auth)");
        super.configure(auth);
        auth.parentAuthenticationManager(authenticationManagerBean());
      
    }*/
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/assets/**");
    }
    
   /* @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(partnerCustomAuthenticationProvider);
        auth.authenticationProvider(emplCustomAuthenticationProvider);

    }*/

}
