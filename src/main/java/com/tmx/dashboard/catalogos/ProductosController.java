package com.tmx.dashboard.catalogos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductosController {

	@RequestMapping("/productos")
	public ModelAndView productos(){
		return new ModelAndView("/catalogos/productos");
	}
}
