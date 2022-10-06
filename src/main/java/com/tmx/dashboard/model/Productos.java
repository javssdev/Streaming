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
@Table(name = "STRCATPROC")
@NamedQuery(name = "Productos.findAll", query = "SELECT o FROM Productos o")
@Getter
@Setter
public class Productos implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "STRCPCVEPR")
	private String clave;

	@Column(name = "STRCPDEPRO")
	private String descripcion;

	@ManyToOne
	@JoinColumn(name="STRCPCVEPL")
	private Plataformas plataforma;
}
