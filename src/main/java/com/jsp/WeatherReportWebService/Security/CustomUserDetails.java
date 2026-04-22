package com.jsp.WeatherReportWebService.Security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jsp.WeatherReportWebService.Entity.User;
import com.jsp.WeatherReportWebService.Enum.Permissions;

public class CustomUserDetails implements UserDetails{
	
	private User user;
	
	public CustomUserDetails(User user) {
	    this.user = user;
	}
@Override
public Collection<? extends GrantedAuthority> getAuthorities()
{

	    Set<SimpleGrantedAuthority> authorities = new HashSet<>();

	    //Add ROLE
	    authorities.add(
	            new SimpleGrantedAuthority(user.getRole().name())
	    );

	    // Add PERMISSIONS
	    for(Permissions permission: user.getRole().getPermissions() ) {
	        authorities.add(
	                new SimpleGrantedAuthority(permission.name())
	        );
	    }

	    return authorities;
}

@Override
public String getPassword()
{
		 return user.getPassword();	
}
	
@Override
public String getUsername()
{
		
		return  user.getUsername();
}


}
