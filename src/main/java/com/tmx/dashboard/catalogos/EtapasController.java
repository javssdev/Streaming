package com.tmx.dashboard.catalogos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EtapasController {

	@RequestMapping("/etapas")
	public ModelAndView etapas(){
		return new ModelAndView("/catalogos/etapas");
	}
}
