package com.jsp.WeatherReportWebService.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jsp.WeatherReportWebService.Entity.User;
import com.jsp.WeatherReportWebService.Exception.AppException;
import com.jsp.WeatherReportWebService.Repository.UserRepository;
import com.jsp.WeatherReportWebService.Security.CustomUserDetails;
@Service
@Primary 
public class CustomUserDetailsService implements UserDetailsService{
@Autowired
UserRepository userRepository;
@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
{
		System.out.println("CUSTOM SERVICE CALLED");
		  User user = userRepository.findByUsername(username) ; 
		  if (user == null){
		  throw new UsernameNotFoundException("User not found"); 
		  }

		return  new CustomUserDetails(user);
	}

}
