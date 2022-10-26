package com.tmx.dashboard.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "STRHSALUD")
@NamedQuery(name = "HistoricoSalud.findAll", query = "SELECT o FROM HistoricoSalud o")
@Getter
@Setter
public class HistoricoSalud implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SOLICITUD_GENERATOR", sequenceName = "STRFOLHSAL", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SOLICITUD_GENERATOR")
	@Column(name = "STRHFOLINT")
	private Long folio;

	@Column(name = "STRHETAP")
	private String etapa;

	@Column(name = "STRHCANT")
	private Long cantidad;

	@Column(name = "STRHCOLOR")
	private String color;

	@Temporal(TemporalType.DATE)
	@Column(name = "STRHFECHA")
	private Date fechaReg = new Date();

}
