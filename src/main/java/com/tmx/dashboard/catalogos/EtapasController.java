package com.tmx.dashboard.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tmx.dashboard.database.DatabaseOperations;
import com.tmx.dashboard.model.Etapas;
import com.tmx.dashboard.utils.Cs;

@Controller
public class EtapasController {

	@Autowired
	private DatabaseOperations<Etapas> DBO;

	@RequestMapping("/etapas")
	public ModelAndView init(ModelAndView m){
		List<Etapas> list = DBO.findAll(Etapas.class, null);
		m.addObject("menu", Cs.MENU);
		m.addObject("catalogos", Cs.MENU_CAT);
		m.addObject("list", list);

		m.setViewName("/catalogos/etapas");
		return m;
	}
}
