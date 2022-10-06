package com.tmx.dashboard.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "STRCATPLAT")
@NamedQuery(name = "Plataformas.findAll", query = "SELECT o FROM Plataformas o")
@Getter
@Setter
public class Plataformas implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "STRCPCVEPL")
	private String clave;

	@Column(name = "STRCPDEPLA")
	private String descripcion;

	@OneToMany(mappedBy = "plataforma")
	private List<Productos> productos;
}
