package com.tmx.dashboard.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tmx.dashboard.database.DatabaseOperations;
import com.tmx.dashboard.model.Productos;
import com.tmx.dashboard.utils.Cs;

@Controller
public class ProductosController {

	@Autowired
	private DatabaseOperations<Productos> DBO;

	@RequestMapping("/productos")
	public ModelAndView init(ModelAndView m){
		List<Productos> list = DBO.findAll(Productos.class, null);
		m.addObject("menu", Cs.MENU);
		m.addObject("catalogos", Cs.MENU_CAT);
		m.addObject("list", list);

		m.setViewName("/catalogos/productos");
		return m;
	}
}
