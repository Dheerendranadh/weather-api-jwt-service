package com.jsp.WeatherReportWebService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.WeatherReportWebService.Entity.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	  User findByUsername(String username);

}
