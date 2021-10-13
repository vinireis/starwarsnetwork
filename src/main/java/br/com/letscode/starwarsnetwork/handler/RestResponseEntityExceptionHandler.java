package br.com.letscode.starwarsnetwork.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.log4j.Log4j2;

@RestControllerAdvice
@Log4j2
public class RestResponseEntityExceptionHandler {
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ErrorApiResponse> handleApiException(ApiException ex) {
		log.error("API Exception", ex);
		return ex.toResponseEntity();
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorApiResponse> handleApiException(Exception ex) {
		log.error("Exception", ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorApiResponse.builder()
				.description("INTERNAL SERVER ERROR!")
				.message("PLEASE INFORM THE SYSTEM ADMINISTRATOR.")
				.build());
	}
}