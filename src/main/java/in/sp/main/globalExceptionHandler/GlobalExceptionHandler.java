package in.sp.main.globalExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import in.sp.main.customexceptions.EmailIsNullException;
import in.sp.main.customexceptions.JoinedDateNullException;
import in.sp.main.customexceptions.NameIsNullException;

@RestControllerAdvice
public class GlobalExceptionHandler 
{
	
	
	@ExceptionHandler(NameIsNullException.class)
	
	public ResponseEntity<String> nameIsNullException(NameIsNullException exception)
	{
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client Error : "+exception.getMessage());
		
	}
	
	
	@ExceptionHandler(EmailIsNullException.class)
	
	public ResponseEntity<String> emailIsNullException(EmailIsNullException exception)
	{
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client Error : "+exception.getMessage());
		
	}
	
	
	@ExceptionHandler(JoinedDateNullException.class)
	
	public ResponseEntity<String> joinedDateIsNullException(EmailIsNullException exception)
	{
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client Error : "+exception.getMessage());
		
	}

}
