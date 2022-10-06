package com.tmx.dashboard.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cs {

	public static final boolean DEVELOPMENT = true;

	public static final List<Menu> MENU = new ArrayList<>(Arrays.asList(
		new Menu("MNU1","Salud del sistema","fa-desktop","salud_sistema"),
		new Menu("MNU2","Contrataciones vs Aprovisonamientos","fa-ile-contract","cont_vs_aprov"),
		new Menu("MNU3","Ventas","fa-clipboard-check","ventas"),
		new Menu("MNU4","Visor de soporte","fa-life-ring","visor_soporte")
	));

	public static final List<Menu> MENU_CAT = new ArrayList<>(Arrays.asList(
			new Menu("SUB3","Plataformas","","plataformas"),
			new Menu("SUB4","Productos","","productos"),
			new Menu("SUB1","Etapas","","etapas"),
			new Menu("SUB2","Errores","","errores")
		));

}
