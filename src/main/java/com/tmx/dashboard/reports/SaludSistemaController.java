package com.tmx.dashboard.reports;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tmx.dashboard.database.DatabaseOperations;
import com.tmx.dashboard.model.HistoricoSalud;
import com.tmx.dashboard.model.Intercambio;
import com.tmx.dashboard.model.Parametros;
import com.tmx.dashboard.model.Solicitud;
import com.tmx.dashboard.utils.Cs;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SaludSistemaController {

	@Autowired
	private DatabaseOperations<Intercambio> dbo;

	@Autowired
	private DatabaseOperations<Solicitud> dboSol;

	@Autowired
	private DatabaseOperations<Parametros> dboParam;

	@Autowired
	private DatabaseOperations<HistoricoSalud> dboHistorico;


	@RequestMapping("/salud_sistema")
	public ModelAndView init(ModelAndView m){
		Map<String, Object> totales = getTotales();
		m.addObject("menu", Cs.MENU);
		m.addObject("catalogos", Cs.MENU_CAT);
		m.addObject("list", new ArrayList<>());
		m.addObject("timer", 10);

		totales.forEach((key, val) -> {
			m.addObject(key, val);
			m.addObject("CLASE_"+key, semaforo(String.valueOf(key), (Long) val));
		});

		m.setViewName("/dashboard/salud_sistema");
		return m;
	}

	@RequestMapping("/semafaro/{etapa}")
	public String getData(Model model, @PathVariable("etapa") String etapa){
		try {
			List<Solicitud> list = null;
			List<Intercambio> listInter = null;
			Map<String, Object> filters;
			switch(etapa){
				case Cs.ETA_VAL:
					filters = new HashMap<>();
					filters.put("etapa", etapa);
					list = dboSol.find(Solicitud.class, filters, null);
					model.addAttribute("list", list);
					break;
				case Cs.ETA_GES:
					filters = new HashMap<>();
					filters.put("etapa", etapa);
					filters.put("estatusG3", "Pendiente");
					listInter = dbo.find(Intercambio.class, filters, null);
					model.addAttribute("list", listInter);
					break;
				case Cs.ETA_ACO:
					filters = new HashMap<>();
					filters.put("etapa", etapa);
					filters.put("estatuPlem", "Pendiente");
					listInter = dbo.find(Intercambio.class, filters, null);
					model.addAttribute("list", listInter);
					break;
				default:
					break;
			}
			model.addAttribute("etapa", etapa);
			return "/dashboard/salud_sistema :: data";
		} catch (Exception e) {
			log.warn(Cs.EXCEPTION, e);
			e.printStackTrace();
			return null;
		}
	}

	private Map<String, Object> getTotales(){
		Map<String, Object> filters = new HashMap<>();
		Map<String, Object> filEta;
		try{
			//ETAPA VAL
			filEta = new HashMap<>();
			filEta.put("etapa", Cs.ETA_VAL);
			filEta.put("estatus", "Pendiente");
			filters.put("VAL", dboSol.count(Solicitud.class, filEta, null));
			//ETAPA GES
			filEta = new HashMap<>();
			filEta.put("etapa", Cs.ETA_GES);
			filEta.put("estatusG3", "Pendiente");
			filters.put("GES", dbo.count(Intercambio.class, filEta, null));
			//ETAPA ACO
			filEta = new HashMap<>();
			filEta.put("etapa", Cs.ETA_ACO);
			filEta.put("estatuPlem", "Pendiente");
			filters.put("ACO", dbo.count(Intercambio.class, filEta, null));
			//ETAPA VIN
			filEta = new HashMap<>();
			filEta.put("etapa", Cs.ETA_VIN);
			//filEta.put("estatusG3", "Pendiente");
			filters.put("VIN", dbo.count(Intercambio.class, filEta, null));
			//ETAPA TER
			filEta = new HashMap<>();
			filEta.put("etapa", Cs.ETA_TER);
			//filEta.put("estatusG3", "Pendiente");
			filters.put("TER", dbo.count(Intercambio.class, filEta, null));
		} catch (Exception e){
			e.printStackTrace();
		}
		return filters;
	}

	private String semaforo(String filtro, Long total){
		String semaforo = "border-left-success";
		List<Parametros> warning = new ArrayList<>();
		List<Parametros> danger = new ArrayList<>();
		Map<String, Object> filters;
		try {
			filters = new HashMap<>();
			filters.put("nombre", Cs.SEMAFORO_AMARILLO+"_"+filtro);
			warning = dboParam.find(Parametros.class, filters, null);

			filters = new HashMap<>();
			filters.put("nombre", Cs.SEMAFORO_ROJO+"_"+filtro);
			danger = dboParam.find(Parametros.class, filters, null);

			if(!warning.isEmpty() && !danger.isEmpty()){
				System.err.println("total :: "+total);
				System.err.println("warning :: "+warning.get(0).getValor().trim());
				System.err.println("danger :: "+danger.get(0).getValor().trim());
				if(total.intValue() >= Integer.parseInt(warning.get(0).getValor().trim()) && total < Integer.parseInt(danger.get(0).getValor().trim())){
					semaforo = "border-left-warning";
					addHistorico(filtro, total, "AMARILLO");
				}

				if(total.intValue() >= Integer.parseInt(danger.get(0).getValor().trim())){
					semaforo = "border-left-danger";
					addHistorico(filtro, total, "ROJO");
				}
			}
		}catch (Exception e){
			log.warn(Cs.EXCEPTION, e);
			e.printStackTrace();
		}
		return semaforo;
	}

	private void addHistorico(String etapa, Long cant, String color){
		try {
			HistoricoSalud obj = new HistoricoSalud();
			obj.setEtapa(etapa);
			obj.setCantidad(cant);
			obj.setColor(color);
			dboHistorico.save(obj);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}