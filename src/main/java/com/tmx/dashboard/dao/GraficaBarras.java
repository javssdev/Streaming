package com.tmx.dashboard.dao;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GraficaBarras {

	private String platform;
	private Long ventas;
	private Long aprov;

}