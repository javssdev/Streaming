package com.tmx.dashboard.reports;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tmx.dashboard.dao.GraficaBarras;
import com.tmx.dashboard.utils.Cs;

@Controller
public class ContAprovController {

	@RequestMapping("/cont_vs_aprov")
	public ModelAndView init(ModelAndView m){
		
		
		List<GraficaBarras> dataBar = new ArrayList<>(Arrays.asList(
				new GraficaBarras("Netflix",725,"./resources/img/streaming_sprite/001-netflix.svg", new GraficaBarras().new Fill("#A92B1D")),
				new GraficaBarras("Disney",625,"./resources/img/streaming_sprite/002-disney-logo.svg", new GraficaBarras().new Fill("#000000")),
				new GraficaBarras("Start+",602,"./resources/img/streaming_sprite/003-star-logo.svg", new GraficaBarras().new Fill("#F3B737")),
				new GraficaBarras("Hbo+",509,"./resources/img/streaming_sprite/004-hbo.svg", new GraficaBarras().new Fill("#29251C")),
				new GraficaBarras("Prime",422,"./resources/img/streaming_sprite/005-amazon-prime-video-logo.svg", new GraficaBarras().new Fill("#5C63CE")),
				new GraficaBarras("Start tv",354,"./resources/img/streaming_sprite/006-star.svg", new GraficaBarras().new Fill("#5C63FE")),
				new GraficaBarras("F1",284,"./resources/img/streaming_sprite/007-f1.svg", new GraficaBarras().new Fill("#E94936"))
			));
		m.addObject("menu", Cs.MENU);
		m.addObject("catalogos", Cs.MENU_CAT);
		m.addObject("dataBar", dataBar);
		m.setViewName("/dashboard/cont_aprov");
		return m;
	}
}
