package com.example.inflearnspringbootinitwebmvc;

import com.example.inflearnspringbootinitwebmvc.exception.SampleError;
import com.example.inflearnspringbootinitwebmvc.exception.SampleException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

    @GetMapping("/hi")
    public String hi(Model model) {
        model.addAttribute("name", "doyun");

        return "hi";
    }

    @GetMapping("/sample-error")
    public String error() {
        throw new SampleException();
    }

    @ExceptionHandler(SampleException.class)
    public @ResponseBody SampleError sampleError(SampleException e) {
        SampleError error = new SampleError();
        error.setMessage("error.app.key");
        error.setReason("not known");

        return error;
    }
}
