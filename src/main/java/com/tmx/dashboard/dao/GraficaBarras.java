package com.tmx.dashboard.dao;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GraficaBarras {

	public GraficaBarras() { }


	String servicio;
	int visits;
	String icon;
	Fill columnSettings;


	@Data
	@AllArgsConstructor
	public class Fill {
		String fill;
	}
}
