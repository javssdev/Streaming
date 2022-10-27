package com.tmx.dashboard.reports;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tmx.dashboard.dao.GraficaBarras;
import com.tmx.dashboard.database.DatabaseOperations;
import com.tmx.dashboard.model.Inventario;
import com.tmx.dashboard.model.Plataformas;
import com.tmx.dashboard.model.Solicitud;
import com.tmx.dashboard.utils.Cs;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ContAprovController {

	@Autowired
	private DatabaseOperations<Plataformas> dboPlat;

	@Autowired
	private DatabaseOperations<Solicitud> dboSol;

	@Autowired
	private DatabaseOperations<Inventario> dboInv;

	@RequestMapping("/cont_vs_aprov")
	public ModelAndView init(ModelAndView m){
		SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
		try {
			List<GraficaBarras> dataVtasAprov = ventas(DateFor.parse("2022-10-01"), DateFor.parse("2022-10-30"));
			m.addObject("menu", Cs.MENU);
			m.addObject("catalogos", Cs.MENU_CAT);
			m.addObject("dataBar", dataVtasAprov);
			m.setViewName("/dashboard/cont_aprov");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return m;
	}

	private List<GraficaBarras> ventas(Date ini, Date fin){
		List<GraficaBarras> list = new ArrayList<>();
		try {
			List<Plataformas> plataformas = dboPlat.findAll(Plataformas.class, new String[] {"descripcion", "ASC"});
			plataformas.forEach(plataforma -> {
				Map<String, Object> filters = new HashMap<>();
				filters.put("plataforma", plataforma.getDescripcion());
				Long totSol = dboSol.count(Solicitud.class, filters, new Object[] {"DATE_fechaReg", ini, fin});

				filters = new HashMap<>();
				filters.put("plataforma", plataforma.getDescripcion());
				Long totInv = dboInv.count(Inventario.class, filters, new Object[] {"DATE_fechaReg", ini, fin});
				list.add(new GraficaBarras(plataforma.getDescripcion(), totSol, totInv));
			});
		} catch (Exception e){
			e.printStackTrace();
		}
		return list;
	}

}