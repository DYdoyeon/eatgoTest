package kr.co.fastcampus.eatgo.interfaces;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ResponseStatus(HttpStatus.NOT_FOUND)
@ControllerAdvice
public class RestaurantErrorAdvice{
    @ExceptionHandler(RestaurantNotFoundException.class)
    public String handleNotFound(){
        return "{}";
    }
}