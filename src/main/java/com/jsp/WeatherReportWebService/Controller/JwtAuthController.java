package com.jsp.WeatherReportWebService.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.WeatherReportWebService.Model.AuthRequest;
import com.jsp.WeatherReportWebService.Util.JWTUtil;
@RestController
public class JwtAuthController{
@Autowired
AuthenticationManager authenticationManager;
@Autowired
JWTUtil jwtutil;

@PostMapping("/authenticate")	
public String authenticate(@RequestBody AuthRequest authrequest) {	     
 try {	
	 System.out.println("in auth manager step 1");
	authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authrequest.getUsername(),authrequest.getPassword())); 
		// Create Jwt Token
	System.out.println("in auth manager 2 ");
		 return jwtutil.generateToken( authrequest.getUsername());
	}
 catch(Exception e)
 {
	 throw e;
 }
 }
}
