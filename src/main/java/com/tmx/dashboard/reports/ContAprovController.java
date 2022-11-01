package com.tmx.dashboard.reports;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tmx.dashboard.dao.GraficaBarras;
import com.tmx.dashboard.dao.GraficaComparative;
import com.tmx.dashboard.database.DatabaseOperations;
import com.tmx.dashboard.model.Inventario;
import com.tmx.dashboard.model.Plataformas;
import com.tmx.dashboard.model.Solicitud;
import com.tmx.dashboard.utils.Cs;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ContAprovController {
	
	public final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	static String timeIni = "00:00:00";
	static String timeFin = "23:59:59";
	static String platform = "plataforma";

	@Autowired
	private DatabaseOperations<Plataformas> dboPlat;

	@Autowired
	private DatabaseOperations<Solicitud> dboSol;

	@Autowired
	private DatabaseOperations<Inventario> dboInv;

	@Autowired
	private DatabaseOperations<Plataformas> dboPlatform;



	@RequestMapping("/cont_vs_aprov")
	public ModelAndView init(ModelAndView m){
		Date date = new Date();
		StringBuilder todate = new StringBuilder(sdf.format(date));
		String fechaIni = todate.append(" ").append(timeIni).toString();
		todate = new StringBuilder(sdf.format(date));
		String fechaFin = todate.append(" ").append(timeFin).toString();
		log.warn(fechaIni);
		log.warn(fechaFin);
		List<GraficaBarras> dataVtasAprov = ventas(Timestamp.valueOf(fechaIni), Timestamp.valueOf(fechaFin));
		List<GraficaBarras> dataVtasBajas = bajas(Timestamp.valueOf(fechaIni), Timestamp.valueOf(fechaFin));
		List<Plataformas> listPlatform = dboPlatform.findAll(Plataformas.class, null);
		m.addObject("menu", Cs.MENU);
		m.addObject("catalogos", Cs.MENU_CAT);
		m.addObject("ventas", dataVtasAprov);
		m.addObject("bajas", dataVtasBajas);
		m.addObject("listPlatform", listPlatform);
		m.setViewName("/dashboard/cont_aprov");
		return m;
	}

	@RequestMapping("/ventas/{fechaIni}/{fechaFin}")
	public String getFilterVtas(Model model, @PathVariable("fechaIni") String f1, @PathVariable("fechaFin") String f2){
		try {
			log.warn(f1);
			log.warn(f2);
			StringBuilder fechaIni = new StringBuilder(f1).append(" ").append(timeIni);
			StringBuilder fechaFin = new StringBuilder(f2).append(" ").append(timeFin);
			List<GraficaBarras> dataVtasAprov = ventas(Timestamp.valueOf(fechaIni.toString()), Timestamp.valueOf(fechaFin.toString()));
			model.addAttribute("ventas", dataVtasAprov);
			return "/dashboard/cont_aprov :: data";
		} catch (Exception e) {
			log.warn(Cs.EXCEPTION, e);
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping("/bajas/{fechaIni}/{fechaFin}")
	public String getFilterBajas(Model model, @PathVariable("fechaIni") String f1, @PathVariable("fechaFin") String f2){
		try {
			log.warn(f1);
			log.warn(f2);
			StringBuilder fechaIni = new StringBuilder(f1).append(" ").append(timeIni);
			StringBuilder fechaFin = new StringBuilder(f2).append(" ").append(timeFin);
			List<GraficaBarras> dataVtasBajas = bajas(Timestamp.valueOf(fechaIni.toString()), Timestamp.valueOf(fechaFin.toString()));
			model.addAttribute("bajas", dataVtasBajas);
			return "/dashboard/cont_aprov :: bajasData";
		} catch (Exception e) {
			log.warn(Cs.EXCEPTION, e);
			e.printStackTrace();
			return null;
		}
	}


	@RequestMapping("/compare/{fechaIni}/{fechaFin}/{plataforma}")
	public String getComparative(Model model, 
			@PathVariable("plataforma") String plataforma,
			@PathVariable("fechaIni") String f1, 
			@PathVariable("fechaFin") String f2) {
		try {
			log.warn(plataforma);
			log.warn(f1);
			log.warn(f2);
			List<GraficaComparative> dataCompare = comparative(plataforma, f1, f2);
			model.addAttribute("comparative", dataCompare);
			return "/dashboard/cont_aprov :: compareData";
		} catch (Exception e) {
			log.warn(Cs.EXCEPTION, e);
			e.printStackTrace();
			return null;
		}
	}

	private List<GraficaBarras> ventas(Date ini, Date fin){
		List<GraficaBarras> list = new ArrayList<>();
		try {
			List<Plataformas> plataformas = dboPlat.findAll(Plataformas.class, new String[] {"descripcion", "ASC"});
			plataformas.forEach(plataforma -> {

				Map<String, Object> filters = new HashMap<>();
				filters.put(platform, plataforma.getDescripcion());
				filters.put("movimiento", "APROVISIONA");
				Long totSol = dboSol.count(Solicitud.class, filters, new Object[] {"DATE_fechaReg", ini, fin});

				filters = new HashMap<>();
				filters.put(platform, plataforma.getDescripcion());
				Long totInv = dboInv.count(Inventario.class, filters, new Object[] {"DATE_fechaAprov", ini, fin});
				list.add(new GraficaBarras(plataforma.getDescripcion(), totSol, totInv));
			});
		} catch (Exception e){
			e.printStackTrace();
			log.warn(Cs.EXCEPTION, e);
		}
		return list;
	}

	private List<GraficaBarras> bajas(Date ini, Date fin){
		List<GraficaBarras> list = new ArrayList<>();
		try {
			List<Plataformas> plataformas = dboPlat.findAll(Plataformas.class, new String[] {"descripcion", "ASC"});
			plataformas.forEach(plataforma -> {
				Map<String, Object> filters = new HashMap<>();
				filters.put(platform, plataforma.getDescripcion());
				filters.put("movimiento", "APROVISIONA");
				Long totSol = dboSol.count(Solicitud.class, filters, new Object[] {"DATE_fechaReg", ini, fin});

				filters = new HashMap<>();
				filters.put(platform, plataforma.getDescripcion());
				Long totInv = dboInv.count(Inventario.class, filters, new Object[] {"DATE_fechaBaja", ini, fin});
				list.add(new GraficaBarras(plataforma.getDescripcion(), (totSol + totInv), totInv));
			});
		} catch (Exception e){
			e.printStackTrace();
			log.warn(Cs.EXCEPTION, e);
		}
		return list;
	}

	private List<GraficaComparative> comparative(String plataforma, String f1, String f2){
		List<GraficaComparative> list = new ArrayList<>();
		try {
			LocalDate start = LocalDate.parse(f1);
			LocalDate end = LocalDate.parse(f2);
			for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
				log.warn("date" + date);
				StringBuilder todate = new StringBuilder(date.toString());
				String fechaIni = todate.append(" ").append(timeIni).toString();

				todate = new StringBuilder(date.toString());
				String fechaFin = todate.append(" ").append(timeFin).toString();
				log.warn("fechaIni -> " + fechaIni);
				log.warn("fechaFin -> " + fechaFin);
				log.warn("plataforma -> " + plataforma);

				Map<String, Object> filters = new HashMap<>();
				filters.put(platform, plataforma);
				filters.put("movimiento", "APROVISIONA");
				Long totSol = dboSol.count(Solicitud.class, filters,
						new Object[] { "DATE_fechaReg", Timestamp.valueOf(fechaIni), Timestamp.valueOf(fechaFin) });

				filters = new HashMap<>();
				filters.put(platform, plataforma);
				Long totInv = dboInv.count(Inventario.class, filters, new Object[] { "DATE_fechaAprov",
						Timestamp.valueOf(fechaIni), Timestamp.valueOf(fechaFin) });

				log.warn("Ventas -> " + totSol);
				log.warn("Aprov -> " + totInv);

				list.add(new GraficaComparative(date.toString(), totSol, totInv));
			}
		} catch (Exception e){
			e.printStackTrace();
			log.warn(Cs.EXCEPTION, e);
		}
		return list;
	}
}