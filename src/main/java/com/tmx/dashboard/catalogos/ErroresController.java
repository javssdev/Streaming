package com.tmx.dashboard.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tmx.dashboard.database.DatabaseOperations;
import com.tmx.dashboard.model.Errores;
import com.tmx.dashboard.utils.Cs;

@Controller
public class ErroresController {

	@Autowired
	private DatabaseOperations<Errores> DBO;

	@RequestMapping("/errores")
	public ModelAndView init(ModelAndView m){
		List<Errores> list = DBO.findAll(Errores.class, null);
		m.addObject("menu", Cs.MENU);
		m.addObject("catalogos", Cs.MENU_CAT);
		m.addObject("list", list);

		m.setViewName("/catalogos/errores");
		return m;
	}
}
