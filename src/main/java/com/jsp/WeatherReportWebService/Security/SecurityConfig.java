package com.jsp.WeatherReportWebService.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;

import com.jsp.WeatherReportWebService.Enum.Permissions;
import com.jsp.WeatherReportWebService.Filter.JwtAuthFilter;
import com.jsp.WeatherReportWebService.SecurityException.CustomAccessDeniedHandler;
import com.jsp.WeatherReportWebService.SecurityException.JwtAuthenticationEntryPoint;
import com.jsp.WeatherReportWebService.Service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
	
@Autowired
private AuthenticationEntryPoint authenticationEntryPoint;

@Autowired
private AccessDeniedHandler accessDeniedHandler;
	
@Autowired
JwtAuthFilter jwtAuthfilter;

@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
{
	
	http.csrf(csrf -> csrf.disable());

	http.sessionManagement(session -> 
	    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

	http.authorizeHttpRequests(auth -> auth
	    .requestMatchers("/public/**").permitAll()
	    .requestMatchers("/authenticate").permitAll()
	    .requestMatchers(HttpMethod.POST, "/weather", "/weather/**")
	        .hasAuthority("WEATHER_WRITE")
	    .requestMatchers(HttpMethod.GET, "/weather", "/weather/**")
	        .hasAuthority("WEATHER_READ")
	    .requestMatchers(HttpMethod.DELETE, "/weather", "/weather/**")
	        .hasAuthority("WEATHER_DELETE")
	    .anyRequest().authenticated());

	http.exceptionHandling(ex -> ex
	    .authenticationEntryPoint(authenticationEntryPoint)
	    .accessDeniedHandler(accessDeniedHandler));

	http.addFilterBefore(jwtAuthfilter, 
	    UsernamePasswordAuthenticationFilter.class);

	return http.build();
}


@Bean
public PasswordEncoder passwordEncoder()
{
	  return new BCryptPasswordEncoder();
}
 
@Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception 
{
     return config.getAuthenticationManager();
 }

/*
  @Bean
 public UserDetailsService userDetaialsService () {
	   return new CustomUserDetailsService();
 }
     @Bean
 public AuthenticationManager authenticationManager(PasswordEncoder PasswordEncoder,UserDetailsService userDetailsService) {
	  DaoAuthenticationProvider provider = new  DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
	 provider.setPasswordEncoder(PasswordEncoder);
	   return new ProviderManager(provider);	 
 }
  */
	
}
