package com.jsp.WeatherReportWebService.Service;
import java.util.List;

import java.util.Optional;

//import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsp.WeatherReportWebService.Exception.AppException;
import com.jsp.WeatherReportWebService.Repository.WeatherRepository;
import com.jsp.WeatherReportWebService.Util.WeatherMapper;

import jakarta.validation.Valid;

import com.jsp.WeatherReportWebService.DTO.WeatherRequestDTO;
import com.jsp.WeatherReportWebService.DTO.WeatherResponseDTO;
import com.jsp.WeatherReportWebService.DTO.WeatherUpdateDTO;
import com.jsp.WeatherReportWebService.Entity.Weather;

@Service
public class WeatherService {
//@Autowired
	// private ChatClient chatClient;
	
@Autowired
	WeatherRepository repository;

// getWeatherEntity
public Weather getWeatherEntityById(int id) {
    return repository.findById(id)
        .orElseThrow(() -> new AppException("Weather not found", HttpStatus.NOT_FOUND));
}

// to insert an object into DB
public  WeatherResponseDTO insertWeather(@Valid WeatherRequestDTO dto)
{        
	return  WeatherMapper.toResponse(repository.save(WeatherMapper.toEntity(dto)));
}

// to search and fetch an object from DB based on PHK i.e.. weatherid
public  WeatherResponseDTO getWeatherById(int id)
{
		Optional<Weather> opt = repository.findById(id);
		if (opt.isPresent())
			   return  WeatherMapper.toResponse(opt.get());
		else
		{
            throw new AppException("User not found", HttpStatus.NOT_FOUND);	
		 }
}
	
// to delete an object from DB using ID
public String deleteWeatherId(int id)
{
		Weather w = getWeatherEntityById(id);
		if (w != null) {
			repository.delete(w);
			// repository.deleteById(id);
			// repository.deleteAll();
			return "Weather report deleted successfully.";
		} else {
			return "Weather Report doesnt exist.";
		}
}
// update weather details  
public String updateWeatherById( @RequestParam int id,@Valid @RequestBody WeatherUpdateDTO dto)
{
		Weather w = getWeatherEntityById(id);
		if (w != null) {
			w.setConditions(dto.getConditions());
			w.setTemperature(dto.getTemperature());
			repository.save(w);
			return "Weather report is updated successfully.";
		} 
		else {
			return "Weather report doesnt exist.";
		}
}	
	
public List<Weather> getAllWeather()
{
		 return repository.findAll();
}
/*public String getWeatherSummary(int id) {

    Weather weather = getWeatherEntityById( id) ;

    String response = chatClient.prompt()
        .user("Explain this weather in simple terms: " + weather.toString())
        .call()
        .content();

    return response;
}*/

}













