package com.tmx.dashboard.reports;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tmx.dashboard.database.DatabaseOperations;
import com.tmx.dashboard.model.Solicitud;
import com.tmx.dashboard.utils.Cs;

@Controller
public class SolicitudController {

	@Autowired
	private DatabaseOperations<Solicitud> dbo;

	@RequestMapping("/solicitudes")
	public ModelAndView init(ModelAndView m){
		m.addObject("menu", Cs.MENU);
		m.addObject("catalogos", Cs.MENU_CAT);
		m.addObject("list", getCatalog());
		m.addObject("object", new Solicitud());

		m.setViewName("/dashboard/solicitudes");
		return m;
	}

	private List<Solicitud> getCatalog(){
		List<Solicitud> list = new ArrayList<>();
		try {
			list = dbo.findAll(Solicitud.class, new String[] {"folioSolicitud", "DESC"});
		} catch (Exception e){
			e.printStackTrace();
		}
		return list;
	}
}