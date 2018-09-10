package com.itm.edu.vblockchain.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes("index")
public class IndexController {
	
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String loadPage(Model model) {
		
		model.addAttribute("");
		return "index";
	}
	
	
	
	


}
