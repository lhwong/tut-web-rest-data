package com.yummynoodlebar.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/contact")
public class ContactController {

@RequestMapping(method = RequestMethod.GET)
	
	public String getCurrentMenu(Model model) {
	
	return "/contact";
	}

}
