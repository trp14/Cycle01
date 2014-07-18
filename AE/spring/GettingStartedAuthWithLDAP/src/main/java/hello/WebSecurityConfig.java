package hello;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * Configure an AuthenticationManager which is used to authenticate a token for an authentication 
	 * request 
	 */
//	@Override
//	protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
//		System.out.println("HERE WE ARE!! ***************");
//		
//		authManagerBuilder
//			.ldapAuthentication()
//				.userDnPatterns("uid={0},ou=people")
//				.groupSearchBase("ou=groups")
//				.contextSource()
//					.ldif("classpath:test-server.ldif");
//	}
	
	
	/*
	 * Configure web based security for specific http requests
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("************** configure(HttpSecurity)");
		
		/*
		 * Allow anyone to access /signup and /about. All other urls require that user must be authenticated.
		 */
		http
//			.authorizeRequests()
//				.antMatchers("/signup", "/about").permitAll()
//				.anyRequest().authenticated()
//			.and()
			.authorizeRequests().anyRequest().permitAll()								// tell Spring Security to allow all requests
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)	// Never create an HttpSesssion and hence never use it to obtain the SecurityCongtext
			.and()		
			.addFilterAfter(new GASessionFilter(), FilterSecurityInterceptor.class);	// TODO add my filter to end of chain (how can I do it without naming a class?)
		
	}
	
	/*
	 * Primarily used to ignore certain requests - i.e. there will be no web security
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.WebSecurity)
	 */
	@Override
	public void configure(WebSecurity webSecurity) throws Exception {
		System.out.println("**************" +webSecurity.toString());
		webSecurity
			.ignoring()
			// All of Spring Security will ignore these requests (including filters)
				.antMatchers("/resources/**");	
//				.antMatchers("/**");	
	}
	
}