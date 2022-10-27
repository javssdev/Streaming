package com.tmx.dashboard.dao;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GraficaBarras {

	String platform;
	Long ventas;
	Long aprov;

}