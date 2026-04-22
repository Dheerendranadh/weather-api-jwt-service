package com.jsp.WeatherReportWebService.DTO;
import jakarta.validation.constraints.NotBlank;
//for PUT
//http.addFilterBefore(jwtAuthfilter,UsernamePasswordAuthenticationFilter.class);
//.httpBasic(withDefaults());
public class WeatherUpdateDTO {

	    @NotBlank
	    private String temperature;

	    @NotBlank
	    private String conditions;

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

	}

