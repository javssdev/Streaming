package com.tmx.dashboard.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "STRPARAM")
@IdClass(ParametrosPk.class)
@NamedQuery(name = "Parametros.findAll", query = "SELECT o FROM Parametros o")
@Getter
@Setter
public class Parametros implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "STRPNOMPA")
	private String nombre;

	@Id
	@Column(name = "STRPVALPA")
	private String valor;

	@Column(name = "STRPDESPA")
	private String desc;
}