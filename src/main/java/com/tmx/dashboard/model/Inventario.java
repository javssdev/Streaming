package com.tmx.dashboard.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "STRINVENTA")
@NamedQuery(name = "Inventario.findAll", query = "SELECT o FROM Inventario o")
@Getter
@Setter
public class Inventario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "STRFOLINF")
	private String folioInformatico;

	@Column(name = "STRTELEF")
	private String telefono;

	@Column(name = "STRORDEN")
	private String ordenServicio;

	@Column(name = "STRCELULAR")
	private String celular;

	@Column(name = "STRPLATAF")
	private String plataforma;

	@Column(name = "STRACTIVO")
	private String activo;

	@Column(name = "STRTIPOPRO")
	private String tipoProducto;

	@Column(name = "STRCORREO")
	private String correo;

	@Column(name = "STRIDCONT")
	private String idContratacion;

	@Column(name = "STRIDTRANS")
	private String idTransCanal;

	@Column(name = "STRVIRTUAL")
	private String numeroVirtual;

	@Column(name = "STRNUMCONT")
	private String numVecesCont;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "STRFHBAJA")
	private Date fechaBaja;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "STRFHAPROV")
	private Date fechaAprov;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "STRFHINS")
	private Date fechaReg;

	@Column(name = "STRUSRINS")
	private String userRegistro;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "STRFHMOD")
	private Date fechaUltimaAct;

	@Column(name = "STRUSRMOD")
	private String userUltimaAct;
}