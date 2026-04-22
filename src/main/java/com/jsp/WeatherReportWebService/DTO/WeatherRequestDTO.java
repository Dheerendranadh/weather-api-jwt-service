package com.jsp.WeatherReportWebService.DTO;
import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
//for POST
public class WeatherRequestDTO {

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "Temperature is required")
    private String temperature;

    @NotBlank(message = "Conditions are required")
    private String conditions;

    @NotNull(message = "Date is required")
    private LocalDate date;

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
