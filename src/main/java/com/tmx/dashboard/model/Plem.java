package com.tmx.dashboard.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "STRDATPLEM")
@NamedQuery(name = "Plem.findAll", query = "SELECT o FROM Plem o")
@Getter
@Setter
public class Plem implements Serializable{
	private static final long serialVersionUID = 1L;

	@Column(name = "STRDPDIVIS")
	private String division;

	@Column(name = "STRDPTELEF")
	private String telefono;

	@Column(name = "STRDPNOMBR")
	private String nombreCliente;

	@Column(name = "STRDPDIREC")
	private String direccionCliente;

	@Column(name = "STRDPCELUL")
	private String celular;

	@Column(name = "STRDPCORRE")
	private String correo;

	@Column(name = "STRDPFOLIO")
	private String numeroOrden;

	@Column(name = "STRDPTFOLI")
	private String tipoOrden;

	@Column(name = "STRDPTSERV")
	private String tipoServicio;

	@Column(name = "STRDPETAPA")
	private String etapa;

	@Id
	@Column(name = "STRDPSKU")
	private String sku;

	@Column(name = "STRDPNOMPA")
	private String nombrePaquete;

	@Column(name = "STRDPCOPE")
	private String cope;

	@Column(name = "STRDPFCOPE")
	private String fechaCope;

	@Column(name = "STRDPFDUED")
	private String fecheDueDate;

	@Column(name = "STRDPFAGEN")
	private String fechaAgenda;

	@Column(name = "STRDPFZON8")
	private String fechaZona8;

	@Column(name = "STRDPMOCAN")
	private String motivoCancelacion;

	@Column(name = "STRDPHORAR")
	private String horario;

	@Column(name = "STRDPSALAD")
	private String saldoAdeudo;

	@Column(name = "STRDPDATRA")
	private String diasAtraso;

	@Column(name = "STRDPFCORT")
	private String fechaCorte;

	@Column(name = "STRDPFLITP")
	private String fechaLimitePago;

	@Column(name = "STRDPLITEL")
	private String lineaTelmex;

	@Column(name = "STRDPDFALT")
	private String diasFaltantes;

	@Column(name = "STRDPCAEXP")
	private String cantidadExp;

	@Column(name = "STRDPVIGEN")
	private String vigenciaPago;

	@Column(name = "STRDPOFICI")
	private String oficina;

	@Column(name = "STRDPCAJA")
	private String caja;

	@Column(name = "STRDPSECUE")
	private String secuenciaCaja;

	@Column(name = "STRDPFVENT")
	private String fechaVenta;

	@Column(name = "STRDPHVENT")
	private String horaVenta;

	@Column(name = "STRDPTICKE")
	private String ticket;

	@Column(name = "STRDPCONSE")
	private String consecutivoTicket;

	@Column(name = "STRDPSERIE")
	private String numeroSerie;

	@Column(name = "STRDPOENTR")
	private String obsEntrega;

	@Column(name = "STRDPMREEM")
	private String motivoReemplazo;

	@Column(name = "STRDPFASE")
	private String faseMensajeria;

	@Column(name = "STRDPNMENS")
	private String nombreMensajeria;

	@Column(name = "STRDPCMENS")
	private String claveMensajeria;

	@Column(name = "STRDPGUIA")
	private String guiaMensajeria;

	@Column(name = "STRDPTMENS")
	private String telMensajeria;

	@Column(name = "STRDPLMENS")
	private String ligaMensajeria;

	@Column(name = "STRDPLTMOD")
	private String ligaTipsMensajeria;

	@Column(name = "STRDPMOBJE")
	private String motivosObjecion;

	@Column(name = "STRDPOOBJE")
	private String obsObjecion;

	@Column(name = "STRDPITEM")
	private String item;

	@Column(name = "STRDPCOPRO")
	private String codPromVelAdi;

	@Column(name = "STRDPFGEOS")
	private String fechaGenOrdenServ;

	@Column(name = "STRDPPROMO")
	private String codPromVelAdd;

	@Column(name = "STRDPVELOC")
	private String velocidadAdicional;

	@Column(name = "STRDPIDBOL")
	private String idBoletaInt;

	@Column(name = "STRDPNOINT")
	private String numeroInt;

	@Column(name = "STRDPFINIC")
	private String fechaIniInt;

	@Column(name = "STRDPHINIC")
	private String horaIniInt;

	@Column(name = "STRDPTESTI")
	private String tiempoEstInt;

	@Column(name = "STRDPFESFI")
	private String fechaEstFinInt;

	@Column(name = "STRDPHESFI")
	private String horaEstFinInt;

	@Column(name = "STRDPNODO")
	private String nodoInt;

	@Column(name = "STRDPSIORI")
	private String sisOrigenInt;

	@Column(name = "STRDPMEINI")
	private String memoIniInt;

	@Column(name = "STRDPFFIN")
	private String fechaFinInt;

	@Column(name = "STRDPHFIN")
	private String horaFinInt;

	@Column(name = "STRDPACCIO")
	private String accionInt;

	@Column(name = "STRDPDEACC")
	private String descAccionInt;

	@Column(name = "STRDPDLIPA")
	private String diaLimitePago;

	@Column(name = "STRDPNOMTE")
	private String nombreTecnico;

	@Column(name = "STRDPNPORT")
	private String numeroPortado;

	@Column(name = "STRDPFPORT")
	private String folioPortabilidad;

	@Column(name = "STRDPLINK2")
	private String link2;

	@Column(name = "STRDPLINK3")
	private String link3;

	@Column(name = "STRDPSERVI")
	private String nomServPlem;

	@Column(name = "STRDPETAST")
	private String etapaStreaming;

	@Column(name = "STRDPESTAT")
	private String estatusProceso;

	@Column(name = "STRDPFECHA")
	private String fechaHoraRegistro;

	@Column(name = "STRDPUSUAR")
	private String usuarioRegistro;
}
