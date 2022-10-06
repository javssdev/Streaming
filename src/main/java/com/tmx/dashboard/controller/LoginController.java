package com.tmx.dashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {

	@GetMapping({"/login", "/"})
	public ModelAndView index(Model model){
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
}
