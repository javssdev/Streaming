package com.tmx.dashboard.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "STRCATETAP")
@NamedQuery(name = "Etapas.findAll", query = "SELECT o FROM Etapas o")
@Getter
@Setter
public class Etapas implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "STRCECVETA")
	private String clave;

	@Column(name = "STRCEDEETA")
	private String descripcion;

	@OneToOne(mappedBy = "etapa")
	private Errores errores;
}
