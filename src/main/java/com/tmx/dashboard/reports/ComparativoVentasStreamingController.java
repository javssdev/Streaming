package com.tmx.dashboard.reports;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ComparativoVentasStreamingController {

	@RequestMapping("/comparativo_ventas_streaming")
	public ModelAndView saludSistema(){
		return new ModelAndView("dashboard/comparativo_ventas_streaming");
	}
}
