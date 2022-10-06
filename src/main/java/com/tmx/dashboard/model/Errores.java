package com.tmx.dashboard.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "STRCATERRO")
@NamedQuery(name = "Errores.findAll", query = "SELECT o FROM Errores o")
@Getter
@Setter
public class Errores implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "STRCECVEER")
	private String clave;

	@Column(name = "STRCEDEERR")
	private String descripcion;

	@Column(name = "STRCEPERSI")
	private String persistencia;

	@Column(name = "STRCEINTEN")
	private Long intentos;

	@ManyToOne
	@JoinColumn(name="STRCECVETA")
	private Etapas etapa;
}
