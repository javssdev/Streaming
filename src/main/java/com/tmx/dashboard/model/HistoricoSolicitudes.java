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
@Table(name = "STRTSOLICI")
@NamedQuery(name = "HistoricoSolicitudes.findAll", query = "SELECT o FROM HistoricoSolicitudes o")
@Getter
@Setter
public class HistoricoSolicitudes implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "STRFOLIO")
	private Long folioSolicitud;

	@Column(name = "STROWNID")
	private String idGestionG3;

	@Column(name = "STRTELEF")
	private String telefono;

	@Column(name = "STRORDEN")
	private String ordenServicio;

	@Column(name = "STRETAPA")
	private String etapa;

	@Column(name = "STRCELULAR")
	private String celular;

	@Column(name = "STRIDCTE")
	private String idCliente;

	@Column(name = "STRIDCONT")
	private String idContratacion;

	@Column(name = "STRSTSPRO")
	private String estatus;

	@Column(name = "STRMOVIM")
	private String movimiento;

	@Column(name = "STRTIPOPRO")
	private String tipoProducto;

	@Column(name = "STRITEM")
	private String itemProducto;

	@Column(name = "STRPLATAF")
	private String plataforma;

	@Column(name = "STRNOMBRE")
	private String nombre;

	@Column(name = "STRCORREO")
	private String correo;

	@Column(name = "STRCANALV")
	private String canalVenta;

	@Column(name = "STROFICINA")
	private String oficinaComercial;

	@Column(name = "STRIDTRANS")
	private String idTransCanal;

	@Column(name = "STRCODPRO")
	private String codigoPromo;

	@Column(name = "STRSALDOA")
	private Double saldoAduedo;

	@Column(name = "STREQUIPO")
	private String equipo;

	@Column(name = "STRLIBDB2")
	private String libDB2;

	@Column(name = "STRLIBDDS")
	private String LibDDS;

	@Column(name = "STRUSRMOV")
	private String userMovim;

	@Temporal(TemporalType.DATE)
	@Column(name = "STRFHMOV")
	private Date fechaMov = new Date();

	@Temporal(TemporalType.DATE)
	@Column(name = "STRFHINS")
	private Date fechaReg = new Date();

	@Column(name = "STRUSRINS")
	private String usuarioReg;

	@Temporal(TemporalType.DATE)
	@Column(name = "STRFHMOD")
	private Date fechaUltAct = new Date();

	@Column(name = "STRUSRMOD")
	private String usuarioUltAct = "";

	@Column(name = "STRCODERR")
	private String codigoError;

	@Temporal(TemporalType.DATE)
	@Column(name = "STRFHERR")
	private Date fechaError = new Date();

	@Column(name = "STRDESCERR")
	private String descError;

	@Temporal(TemporalType.DATE)
	@Column(name = "STRFHURLVI")
	private Date fechaVigURL = new Date();

	@Temporal(TemporalType.DATE)
	@Column(name = "STRFHURLC")
	private Date fechaConfUrl = new Date();

	@Column(name = "STRCLIKURL")
	private Long numVecesClickUrl = 0L;

	@Column(name = "STRTIPOMOV")
	private String tipoTrigger;

	@Temporal(TemporalType.DATE)
	@Column(name = "SRTFHINST")
	private Date fechaTrigger = new Date();

	@Column(name = "STRUSRINST")
	private String userTrigger;

	@Column(name = "STRESCENA")
	private String escenarioPLEM;

	@Column(name = "STRURLCORT")
	private String urlCorta;

	@Column(name = "STRURLCOMP")
	private String urlComplet;
}