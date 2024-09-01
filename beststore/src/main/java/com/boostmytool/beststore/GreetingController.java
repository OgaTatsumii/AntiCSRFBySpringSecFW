package com.boostmytool.beststore;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.boostmytool.beststore.models.ProductDto;

@Controller
@RequestMapping("/home")
public class GreetingController {
	@GetMapping
	public String showHomePage() {
		return "greetings/greeting";
	}
}
