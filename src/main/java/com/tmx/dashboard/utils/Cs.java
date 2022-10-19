package com.tmx.dashboard.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cs {

	public static final boolean DEVELOPMENT = true;
	public static final String SUCCSES = "success";
	public static final String ERROR = "error";
	public static final String EXCEPTION = "Exception";
	public static final String ETA_VAL = "VAL";
	public static final String ETA_GES = "GES";
	public static final String ETA_ACO = "ACO";
	public static final String ETA_VIN = "VIN";
	public static final String ETA_TER = "TER";
	public static final String SEMAFORO_AMARILLO  = "SEMAFORO_AMARILLO";
	public static final String SEMAFORO_ROJO  = "SEMAFORO_ROJO";

	public static final List<Menu> MENU = new ArrayList<>(Arrays.asList(
		new Menu("MNU1","Salud del sistema","fa-desktop","salud_sistema"),
		new Menu("MNU2","Contrataciones vs Aprovisonamientos","fa-file-contract","cont_vs_aprov"),
		new Menu("MNU4","Visor de soporte","fa-life-ring","visor_soporte"),
		new Menu("MNU5","Visor de Solicitudes","fa-life-ring","solicitudes")
	));

	public static final List<Menu> MENU_CAT = new ArrayList<>(Arrays.asList(
			new Menu("SUB3","Plataformas","","plataformas"),
			new Menu("SUB4","Productos","","productos"),
			new Menu("SUB1","Etapas","","etapas"),
			new Menu("SUB2","Errores","","errores")
		));

}
