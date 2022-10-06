package com.tmx.dashboard.catalogos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tmx.dashboard.utils.Cs;

@Controller
public class EtapasController {

	@RequestMapping("/etapas")
	public ModelAndView init(ModelAndView m){
		m.addObject("menu", Cs.MENU);
		m.addObject("catalogos", Cs.MENU_CAT);
		m.setViewName("/catalogos/etapas");
		return m;
	}
}
