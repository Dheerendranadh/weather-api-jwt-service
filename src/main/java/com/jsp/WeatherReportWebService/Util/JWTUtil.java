package com.jsp.WeatherReportWebService.Util;
import java.sql.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
@Component
public class JWTUtil  {
	private final String SECRET="mysecretkeymysecretkeymysecretkey123";
	private final long Expiration_Time=1000*60*60;
	private SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());
	 
public String generateToken(String username) {
		   return  Jwts.builder()
		         .setSubject(username)
		         .setIssuedAt(new Date(System.currentTimeMillis()))
		         .setExpiration(new Date(System.currentTimeMillis()+ Expiration_Time))
		         .signWith(key,SignatureAlgorithm.HS256) 
		         .compact(); 
 }  
 public String extractUsername(String token) {
		    Claims body=  extractClaims(token);
		  return extractedSubject(body);  	 
 }
	 
private String extractedSubject(Claims body) {
		return   body.getSubject();
  } 

 public Claims extractClaims(String token)
 {
		   return  Jwts.parserBuilder()
		       .setSigningKey(key)
		       .build()
		       .parseClaimsJws(token)
		      .getBody(); 
 } 

 public boolean validatetoken(String username,UserDetails userDetails ,String token)
{
		 // username in userdetails  &&  notexpired  
  return username.equals(userDetails.getUsername()) &&!isTokenExpired(token);
		 
} 
 
public boolean  isTokenExpired(String token) {
    return  extractClaims(token).getExpiration().before(new Date(System.currentTimeMillis()));		   
 }
	 	  
}
