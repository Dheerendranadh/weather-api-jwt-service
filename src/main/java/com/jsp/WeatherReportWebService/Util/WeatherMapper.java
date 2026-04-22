package com.jsp.WeatherReportWebService.Util;

import com.jsp.WeatherReportWebService.DTO.*;
import com.jsp.WeatherReportWebService.Entity.Weather;

public class WeatherMapper{

    public static Weather toEntity(WeatherRequestDTO dto)
    {
        Weather w = new Weather();
        w.setCity(dto.getCity());
        w.setTemperature(dto.getTemperature());
        w.setConditions(dto.getConditions());
        w.setDate(dto.getDate());
        return w;
    }

    public static WeatherResponseDTO toResponse(Weather w) 
    {
        WeatherResponseDTO dto = new WeatherResponseDTO();
        dto.setWeatherid(w.getWeatherid());
        dto.setCity(w.getCity());
        dto.setTemperature(w.getTemperature());
        dto.setConditions(w.getConditions());
        dto.setDate(w.getDate());
        return dto;
    }
}