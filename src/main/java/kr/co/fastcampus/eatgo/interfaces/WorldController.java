package kr.co.fastcampus.eatgo.interfaces;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorldController {

    @GetMapping("/")
    public String helloworld(){
        return "Hello World!!!!!!";
    }
}
