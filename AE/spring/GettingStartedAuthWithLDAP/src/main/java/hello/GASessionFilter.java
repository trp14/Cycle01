package hello;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;

public class GASessionFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		Logger logger = LoggerFactory.getLogger("hello.GASessionFilter");
		logger.info("doFilter()");
		
//		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
//		StatusPrinter.print(lc);
		
		/*
		 * Query the Spring Authentication object to find the name of the currently authenticated user.
		 */
		
		String username;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		}
		logger.info("Username={}", username);		// much faster than parameter constructions when logging disabled
		
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		LoggerFactory.getLogger("hello.GASessionFilter").info("init()");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		LoggerFactory.getLogger("hello.GASessionFilter").info("destroy()");
	}


}
