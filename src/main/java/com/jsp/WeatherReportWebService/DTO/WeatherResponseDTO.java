package com.jsp.WeatherReportWebService.DTO;

import java.time.LocalDate;

public class WeatherResponseDTO {

    private int weatherid;
    private String city;
    private String temperature;
    private String conditions;
    private LocalDate date;
	public int getWeatherid() {
		return weatherid;
	}
	public void setWeatherid(int weatherid) {
		this.weatherid = weatherid;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getConditions() {
		return conditions;
	}
	public void setConditions(String conditions) {
		this.conditions = conditions;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}

}
