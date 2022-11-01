package com.tmx.dashboard.dao;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GraficaComparative {

	private String date;
	private Long ventas;
	private Long aprov;
	
}
