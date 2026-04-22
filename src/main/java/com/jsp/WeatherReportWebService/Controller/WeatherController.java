package com.jsp.WeatherReportWebService.Controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.WeatherReportWebService.DTO.WeatherRequestDTO;
import com.jsp.WeatherReportWebService.DTO.WeatherResponseDTO;
import com.jsp.WeatherReportWebService.DTO.WeatherUpdateDTO;
import com.jsp.WeatherReportWebService.Entity.Weather;
import com.jsp.WeatherReportWebService.Exception.AppException;
import com.jsp.WeatherReportWebService.Service.WeatherService;

import jakarta.validation.Valid;
@RestController
public class WeatherController {
	
@Autowired
WeatherService service;	

@PostMapping("/weather")
public WeatherResponseDTO insertWeatherReport(@Valid @RequestBody WeatherRequestDTO dto) {
	System.out.println("CONTROLLER HIT");
	         return  service.insertWeather(dto); 
}

@GetMapping("/weather")
public WeatherResponseDTO getWeatherReportById(@RequestParam int id) {
	 if (id <= 0) {
	        throw new AppException("Invalid ID", HttpStatus.BAD_REQUEST);
	    }
	 return service.getWeatherById(id);    
}
	

@DeleteMapping("/weather")
public String deleteWeatherReportById(@RequestParam int id)
{
		return service.deleteWeatherId(id);
}
		
@PutMapping("/weather")
public String updateWeatherReport(@RequestParam int id,@RequestBody WeatherUpdateDTO dto) 
{
		return service.updateWeatherById(id,dto);
}
	
@GetMapping("/all")
public List<Weather> getALlWeatherReports() 
{
		return service.getAllWeather();
}


 
}
		














