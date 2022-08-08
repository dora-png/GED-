package com.microservice.ged.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class HomeResource {
	@GetMapping("/toto")
	public String index() {
		return "toto fonctionne";
	}

}
