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
@Table(name = "STRSOLICIT")
@NamedQuery(name = "Solicitud.findAll", query = "SELECT o FROM Solicitud o")
@Getter
@Setter
public class Solicitud implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SOLICITUD_GENERATOR", sequenceName = "STRFOLIOSL", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SOLICITUD_GENERATOR")
	@Column(name = "STRFOLIO")
	private Long folioSolicitud;

	/* Generales */
	@Column(name = "STRTELEF")
	private String telefono;

	@Column(name = "STRNOMBRE")
	private String nombre;

	@Column(name = "STRCORREO")
	private String correo;

	@Column(name = "STRCELULAR")
	private String celular;

	@Column(name = "STRCANALV")
	private String canalVenta;

	@Column(name = "STROFICINA")
	private String oficinaComercial;

	@Column(name = "STRUSRINS")
	private String usuarioReg;

	@Column(name = "STRLIBDB2")
	private String libDB2;


	/* Stream */
	@Column(name = "STRIDCTE")
	private String idCliente;

	@Column(name = "STRIDCONT")
	private String idContratacion;
	
	@Column(name = "STRPLATAF")
	private String plataforma;

	@Column(name = "STRITEM")
	private String itemProd;

	@Column(name = "STRMOVIM")
	private String movimiento;

	@Column(name = "STRORDEN")
	private String ordenServicio;

	@Column(name = "STRCODPRO")
	private String codigoPromo;

	@Column(name = "STRIDTRANS")
	private String idTransCanal;


	/* Otros */
	@Column(name = "STRETAPA")
	private String etapa;

	@Column(name = "STRSTSPRO")
	private String estatus;

	@Column(name = "STRTIPOPRO")
	private String tipoProd = "";

	@Column(name = "STRSALDOA")
	private Double saldoAduedo = 0.0;

	@Column(name = "STREQUIPO")
	private String equipo = "";

	@Column(name = "STRLIBDDS")
	private String libDDS = "";

	@Column(name = "STRUSRMOV")
	private String usuarioMov = "";

	@Column(name = "STRUSRMOD")
	private String usuarioUltAct = "";

	@Column(name = "STRCODERR")
	private String codigoError = "";

	@Column(name = "STRDESCERR")
	private String descError = "";

	@Column(name = "STRURLCOMP")
	private String urlCompleta = "";

	@Column(name = "STRURLCORT")
	private String urlCorta = "";

	@Column(name = "STRCLIKURL")
	private Long numVecesClickUrl = 0L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "STRFHMOV")
	private Date fechaMov = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "STRFHINS")
	private Date fechaReg = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "STRFHMOD")
	private Date fechaUltAct = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "STRFHERR")
	private Date fechaError = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "STRFHURLVI")
	private Date fechaVigURL = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "STRFHURLC")
	private Date fechaConfUrl = new Date();
}
