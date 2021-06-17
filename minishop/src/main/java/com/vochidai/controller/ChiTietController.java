package com.vochidai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chitiet")
public class ChiTietController {
	
	@GetMapping
	public String Default() {
		
		
		
		return "chitiet";
	}
	
}
