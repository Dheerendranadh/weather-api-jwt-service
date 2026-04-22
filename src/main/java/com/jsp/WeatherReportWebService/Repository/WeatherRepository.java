package com.jsp.WeatherReportWebService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.WeatherReportWebService.Entity.Weather;

public interface WeatherRepository extends JpaRepository<Weather,Integer>
{
	   // all inherited methods are present here.
}
