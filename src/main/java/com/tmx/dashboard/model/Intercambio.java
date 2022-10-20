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
@Table(name = "STRPRALINT")
@NamedQuery(name = "Intercambio.findAll", query = "SELECT o FROM Intercambio o")
@Getter
@Setter
public class Intercambio implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SOLICITUD_GENERATOR", sequenceName = "STRFOLNFIL", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SOLICITUD_GENERATOR")
	@Column(name = "STRFOLINT")
	private Long folioIntercambio;

	@Column(name = "STRFOLIO")
	private Long folioSolicitud;

	@Column(name = "STRPLATAF")
	private String plataforma;

	@Column(name = "STRTIPOPRO")
	private String tipoProd;

	@Column(name = "STRMOVIM")
	private String movimiento;

	@Column(name = "STRETAPA")
	private String etapa;

	@Column(name = "STRSTSG3")
	private String estatusG3;

	@Column(name = "STRSTSPLEM")
	private String estatuPlem;

	@Temporal(TemporalType.DATE)
	@Column(name = "STRFHINS")
	private Date fechaReg = new Date();

	@Column(name = "STRUSRINS")
	private String usuario;
	
	@Column(name = "STRORDEN")
	private String ordenServ;
	
	@Column(name = "STRCELULAR")
	private String celular;
	
	@Column(name = "STRIDCTE")
	private String idCliente;
	
	@Column(name = "STRIDCONT")
	private String idContratacion;
	
	@Column(name = "STRNOMBRE")
	private String nombre;
	
	@Column(name = "STRTELEF")
	private String telefono;
	
	@Column(name = "STRCORREO")
	private String correo;
	
	@Column(name = "STRCANALV")
	private String canalVenta;
	
	@Column(name = "STROFICINA")
	private String ofComercial;
	
	@Column(name = "STRIDTRANS")
	private String idTransCanal;
	
	@Column(name = "STRCODPRO")
	private String codPromo;
	
	@Column(name = "STRSALDOA")
	private Double saldoAduedo;
	
	@Column(name = "STREQUIPO")
	private String equipo;
	
	@Column(name = "STRLIBDB2")
	private String libreriaDB2;
	
	@Column(name = "STRLIBDDS")
	private String libreriaDDS;

	@Column(name = "STRESCENA")
	private String escenario;

}
