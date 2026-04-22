package com.jsp.WeatherReportWebService.Filter;
import java.io.IOException;
import java.net.http.HttpRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.jsp.WeatherReportWebService.Service.CustomUserDetailsService;
import com.jsp.WeatherReportWebService.Util.JWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

@Autowired
JWTUtil jwtUtil;
  @Autowired
	CustomUserDetailsService customUserDetailsService;
  
@Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	System.out.println("JWT FILTER EXECUTED");
	String path = request.getServletPath();

	if (path.startsWith("/authenticate") || path.startsWith("/public")) {
	    filterChain.doFilter(request, response);
	    return;
	}
	   String authHeader = request.getHeader("Authorization");
	   String token = null;
	   String username = null;
	  
	   if(authHeader != null &&authHeader.startsWith("Bearer ")) {
		  token =  authHeader.substring(7);
		  
		   username= jwtUtil.extractUsername(token);
		   System.out.println("USERNAME FROM TOKEN: " + username);
}
	   
// validate token and set to the securityContextHolder so furthur validations  Bipass
	   if(username != null && 
			   SecurityContextHolder.getContext().getAuthentication() == null) {
		     UserDetails userdetails = customUserDetailsService.loadUserByUsername(username);
		     System.out.println("AUTHORITIES: " + userdetails.getAuthorities());
		    	 if(jwtUtil.validatetoken(username, userdetails,token)){ 
	             // set to spring context 
	        	 UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userdetails,null,userdetails.getAuthorities());
	        	// authToken.setDetails(userdetails);
	       
	        	 authToken.setDetails(
	        	            new WebAuthenticationDetailsSource().buildDetails(request)
	        	        );
	        	 SecurityContextHolder.getContext().setAuthentication(authToken);
	        	 System.out.println("AUTH SET: " + SecurityContextHolder.getContext().getAuthentication());
	            }	    	
}   
	   
	  filterChain.doFilter(request, response); 
}
	
}
