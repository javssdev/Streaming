package com.tmx.dashboard.catalogos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PlataformasController {

	@RequestMapping("/plataformas")
	public ModelAndView plataformas(){
		return new ModelAndView("/catalogos/plataformas");
	}
}
