package com.tmx.dashboard.catalogos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfiguracionMensajesController {

	@RequestMapping("/conf_msjs")
	public ModelAndView configuracionMensajes(){
		return new ModelAndView("/catalogos/conf_msjs");
	}
}
