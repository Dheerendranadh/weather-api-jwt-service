package com.jsp.WeatherReportWebService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jsp.WeatherReportWebService.Entity.User;
import com.jsp.WeatherReportWebService.Enum.Role;
import com.jsp.WeatherReportWebService.Repository.UserRepository;

@SpringBootApplication
public class WeatherReportWebServiceApplication{

public static void main(String[] args)
{
		SpringApplication.run(WeatherReportWebServiceApplication.class, args);
}

@Bean
public CommandLineRunner runner(UserRepository repo, PasswordEncoder encoder) {
	    return args ->{
	    	if (repo.findByUsername("deeru") == null) {
	        User user = new User();
	        user.setUsername("deeru");
	        user.setPassword(encoder.encode("1234"));
	        user.setRole(Role.ROLE_USER);
	        repo.save(user);
	    	}
	    	if (repo.findByUsername("admin") == null) {
	        User admin = new User();
	        admin.setUsername("admin");
	        admin.setPassword(encoder.encode("admin1234"));
	        admin.setRole(Role.ROLE_ADMIN);
	        repo.save(admin);
	     }
	
	    };
}

}


















