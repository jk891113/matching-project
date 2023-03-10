package com.dbzz.matchingproject.exception;

import com.dbzz.matchingproject.dto.response.StatusResponseDto;
import com.dbzz.matchingproject.enums.StatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.charset.Charset;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StatusResponseDto> IllegalArgumentExceptionHandler(IllegalArgumentException e) {
        StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.BAD_REQUEST, e.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(responseDto, headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StatusResponseDto> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.BAD_REQUEST, message);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(responseDto, headers, HttpStatus.BAD_REQUEST);
    }
}
