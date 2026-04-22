package com.jsp.WeatherReportWebService.Exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.WeatherReportWebService.Error.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;
@RestControllerAdvice
public class GlobalExceptionHandler {
	
   @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorResponse>  handleAppException(AppException ex,HttpServletRequest request) {
	   ErrorResponse error = new ErrorResponse(
               LocalDateTime.now(),
               ex.getStatus().value(),
               ex.getStatus().getReasonPhrase(),
               ex.getMessage(),
               request.getRequestURI()  );

       return ResponseEntity
               .status(ex.getStatus())
               .body(error);
       //  return new ResponseEntity<>(response, ex.getStatus());
    }
   
  @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<ErrorResponse> handleValidationException(
           MethodArgumentNotValidException ex,
           HttpServletRequest request) {

       List<String> messages = ex.getBindingResult()
               .getFieldErrors()
               .stream()
               .map(err -> err.getField() + ": " + err.getDefaultMessage())
               .toList();

       String combinedMessage = String.join(", ", messages);

       ErrorResponse error = new ErrorResponse(
               LocalDateTime.now(),
               HttpStatus.BAD_REQUEST.value(),
               HttpStatus.BAD_REQUEST.getReasonPhrase(),
               combinedMessage,
               request.getRequestURI()
       );

       return ResponseEntity
               .status(HttpStatus.BAD_REQUEST)
               .body(error);
   }
   
 /*
    @ExceptionHandler(Exception.class)
   public ResponseEntity<ErrorResponse> handleGenericException(
           Exception ex,
           HttpServletRequest request) {

       ErrorResponse response = new ErrorResponse(
    		   LocalDateTime.now(),
               HttpStatus.INTERNAL_SERVER_ERROR,
               ex.getStatus().getReasonPhrase(),
                "Something went wrong",
               request.getRequestURI()
       );

       return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
   }*/
   

  
}