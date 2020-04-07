package com.elsospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.elsospring.service.HomeService;

@RestController
public class HomeController {
	
	private HomeService homeService;

	@Autowired
	public void setHomeService(HomeService homeService) {
		this.homeService = homeService;
	}

	@PostMapping("/")
	public String index(@RequestBody String name){
		return homeService.nameCheck(name);
	}

}