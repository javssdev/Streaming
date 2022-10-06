package com.tmx.dashboard.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tmx.dashboard.database.DatabaseOperations;
import com.tmx.dashboard.model.Plataformas;
import com.tmx.dashboard.utils.Cs;

@Controller
public class PlataformasController {

	@Autowired
	private DatabaseOperations<Plataformas> DBO;

	@RequestMapping("/plataformas")
	public ModelAndView init(ModelAndView m){
		List<Plataformas> list = DBO.findAll(Plataformas.class, null);
		m.addObject("menu", Cs.MENU);
		m.addObject("catalogos", Cs.MENU_CAT);
		m.addObject("list", list);
		m.setViewName("/catalogos/plataformas");
		return m;
	}
}
