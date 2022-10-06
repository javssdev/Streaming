package com.tmx.dashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tmx.dashboard.utils.Cs;

@Controller
public class IndexController {

	@RequestMapping({"/dashboard", "/", "home"})
	public ModelAndView index(ModelAndView m){
		m.addObject("menu", Cs.MENU);
		m.addObject("catalogos", Cs.MENU_CAT);
		m.setViewName("/dashboard/salud_sistema");
		return m;
	}
}
