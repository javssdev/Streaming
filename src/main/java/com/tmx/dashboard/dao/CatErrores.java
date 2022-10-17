package com.tmx.dashboard.dao;

import lombok.Data;

@Data
public class CatErrores {
	private String clave;
	private String descripcion;
	private String persistencia;
	private Long intentos;
	private String etapa;
	private String etapaDesc;
}
