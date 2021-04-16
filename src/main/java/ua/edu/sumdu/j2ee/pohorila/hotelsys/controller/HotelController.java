package ua.edu.sumdu.j2ee.pohorila.hotelsys.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HotelController implements ErrorController {
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("Everything is ok");
    }

    @RequestMapping("/error")
    public ResponseEntity<?> handleError() {
        return ResponseEntity.ok("WHAT ARE YOU DOING?");
    }

    @Override
    public String getErrorPath() {
        return null;
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main() {

        return "main";
    }
}
