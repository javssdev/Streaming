package com.tmx.dashboard.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class ParametrosPk implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nombre;
	private String valor;
}