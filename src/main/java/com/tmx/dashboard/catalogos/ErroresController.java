package com.tmx.dashboard.catalogos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErroresController {

	@RequestMapping("/errores")
	public ModelAndView errores(){
		return new ModelAndView("/catalogos/errores");
	}
}
