package com.tmx.dashboard.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "CMPRUSUARI")
@NamedQuery(name = "CMPRUSUARI.findAll", query = "SELECT u FROM Usuario u")
@Data
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CMPUSTIUSU")
	private Long tipoUsuario;

	@Column(name = "CMPUSTIPER")
	private Long tipoPersona;

	@Column(name = "CMPUSTCVUS")
	private String cvUsuario;

	@Column(name = "CMPUSTEXPE")
	private String expediente;

	@Column(name = "CMPUSTNOMB")
	private String nombre;

	@Column(name = "CMPUSTPATE")
	private String paterno;

	@Column(name = "CMPUSTMATE")
	private String materno;

	@Temporal(TemporalType.DATE)
	@Column(name = "CMPUSTFIAC")
	private Date fechaInicial;

	@Temporal(TemporalType.DATE)
	@Column(name = "CMPUSTFFAC")
	private Date fechaFinal;

	@Column(name = "CMPUSTPASS")
	private String passwword;
}
