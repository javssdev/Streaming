package com.tmx.dashboard.cron;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tmx.dashboard.database.DatabaseOperations;
import com.tmx.dashboard.model.HistoricoSalud;
import com.tmx.dashboard.model.Intercambio;
import com.tmx.dashboard.model.Parametros;
import com.tmx.dashboard.model.Solicitud;
import com.tmx.dashboard.utils.Cs;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@EnableScheduling
public class AppScheduler {

	private static final String TIME_ZONE =	  "America/Mexico_City";

	@Autowired
	private DatabaseOperations<Intercambio> dbo;

	@Autowired
	private DatabaseOperations<Solicitud> dboSol;

	@Autowired
	private DatabaseOperations<Parametros> dboParam;

	@Autowired
	private DatabaseOperations<HistoricoSalud> dboHistorico;


	@Scheduled(cron = "${schedule.cron}", zone = TIME_ZONE)
	public void taskWithCronExpression() {
		log.warn("Task with Cron Expression, " + (LocalTime.now().getSecond()));
		Map<String, Object> totales = getTotales();
		totales.forEach((key, val) -> {
			String semaforo = semaforo(String.valueOf(key), (Long) val);
			if(semaforo.equals("AMARILLO") || semaforo.equals("ROJO")) {
				log.warn(Cs.EXCEPTION, "INSERTA BITACORA");
				addHistorico(String.valueOf(key), (Long) val, semaforo);
			}
		});
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
				if(total.intValue() >= Integer.parseInt(warning.get(0).getValor().trim()) && total < Integer.parseInt(danger.get(0).getValor().trim()))
					semaforo = "AMARILLO";

				if(total.intValue() >= Integer.parseInt(danger.get(0).getValor().trim()))
					semaforo = "ROJO";
			}
		}catch (Exception e){
			log.warn(Cs.EXCEPTION, e);
			e.printStackTrace();
		}
		return semaforo;
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
}