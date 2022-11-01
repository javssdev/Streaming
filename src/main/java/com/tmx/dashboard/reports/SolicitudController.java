package com.tmx.dashboard.reports;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tmx.dashboard.database.DatabaseOperations;
import com.tmx.dashboard.model.Solicitud;
import com.tmx.dashboard.utils.Cs;
import com.tmx.dashboard.utils.PageRender;

@Controller
public class SolicitudController {

	@Autowired
	private DatabaseOperations<Solicitud> dbo;

	@RequestMapping("/solicitudes")
	public ModelAndView init(@RequestParam(name = "page", defaultValue = "0") int page, ModelAndView m){
		Pageable pageRequest = PageRequest.of(page, 10);
		Page<Solicitud> solicitudes = getCatalog(pageRequest);
		PageRender<Solicitud> pageRender = new PageRender<>("/solicitudes", solicitudes);
		m.addObject("menu", Cs.MENU);
		m.addObject("catalogos", Cs.MENU_CAT);
		m.addObject("list", solicitudes);
		m.addObject("page", pageRender);
		m.addObject("object", new Solicitud());

		m.setViewName("/dashboard/solicitudes");
		return m;
	}

	@RequestMapping("/search/{filtro}")
	public String getInfo(Model model, @PathVariable("filtro") String filtro, @RequestParam(name = "page", defaultValue = "0") int page){
		try {
			Pageable pageRequest = PageRequest.of(page, 10);
			Map<String, Object> filters = new HashMap<>();
			filters.put("telefono", filtro);
			filters.put("correo", filtro);
			filters.put("etapa", filtro);
			filters.put("plataforma", filtro);
			Page<Solicitud> solicitudes = dbo.searchInfo(Solicitud.class, filters, new String[] {"folioSolicitud", "DESC"}, pageRequest);
			PageRender<Solicitud> pageRender = new PageRender<>("/solicitudes", solicitudes);

			model.addAttribute("list", solicitudes);
			model.addAttribute("page", pageRender);
			return "/dashboard/solicitudes :: data";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Page<Solicitud> getCatalog(Pageable page){
		Page<Solicitud> list = null;
		try {
			list = dbo.findAll(Solicitud.class, new String[] {"folioSolicitud", "DESC"}, page);
		} catch (Exception e){
			e.printStackTrace();
		}
		return list;
	}
}