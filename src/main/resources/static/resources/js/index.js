/* ----------------------------------------------------------------INI LOGIN */
const inputs = document.querySelectorAll(".input");

function addcl(){
	let parent = this.parentNode.parentNode;
	parent.classList.add("focus");
}

function remcl(){
	let parent = this.parentNode.parentNode;
	if(this.value == ""){
		parent.classList.remove("focus");
	}
}

inputs.forEach(input => {
	input.addEventListener("focus", addcl);
	input.addEventListener("blur", remcl);
});
/* ----------------------------------------------------------------FIN LOGIN */

/* ----------------------------------------------------------------INI SELECION MENU */
$(".icon-nav-menu-burguer, .icon-nav-menu-close").click(function(){
	if($('.g-sidenav-show').hasClass('rtl')){
		$('.g-sidenav-show').removeClass('rtl');
		$('.main-container-layout').removeClass('rtl');
		$('.main-container-wk').removeClass('rtl');
	}else{
		$('.g-sidenav-show').addClass('rtl');
		$('.main-container-layout').addClass('rtl');
		$('.main-container-wk').addClass('rtl');
	}
});

$('.menu_s .nav-link').click(function(){
	if(!$(this).hasClass('dropdown-toggle')){
		$('.menu_s .nav-link').removeClass("active");
		$(this).addClass('active');
		localStorage["page"] = this.id;
		
		if(!$(this).parent().parent().parent().parent().children().eq(0).hasClass('dropdown-toggle')){
			localStorage["list"] = "";
		}
	}else{
		localStorage["list"] = this.id;
	}
});

(function() {
	$('.menu_s .nav-link').removeClass("active");
	if(localStorage["page"]!=""){
		$("#" + localStorage["page"]).addClass('active');
	}else{
		$('.menu_s .nav-link').removeClass("active");
	}
	
	if(localStorage["list"]!=""){
		$("#" + localStorage["list"]).removeClass('collapsed');
		$("#" + localStorage["list"]).attr("aria-expanded","true");
		$("#" + localStorage["page"]).parent().parent().parent().addClass("show");
	}
	
	var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
				var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
				  return new bootstrap.Tooltip(tooltipTriggerEl)
				});
})();

$("#cerrar_sesion").click(function(){
	localStorage["page"] = "";
	localStorage["list"] = "";
});
/* ----------------------------------------------------------------FIN SELECION MENU */

$(document).ready(function() {
	var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
	var tooltipList = tooltipTriggerList.map(function(tooltipTriggerEl) {
		return new bootstrap.Tooltip(tooltipTriggerEl)
	});
	
	/* INI SALUD_DEL_SISTEMA */
	var meses = new Array("Enero", "Febrero", "Marzo", "Abril", "Mayo",
		"Junio", "Julio", "Agosto", "Septiembre", "Octubre",
		"Noviembre", "Diciembre");
	var f = new Date();
	$(".card-header .text-date").html(f.getDate() + " de " + meses[f.getMonth()] + " de "
		+ f.getFullYear());
		
	$(".box-info-st").click(function() {
		$(".box-info-st").removeClass("active");
		$(this).addClass("active");
	});
	/* FIN SALUD_DEL_SISTEMA */
	
	/* INI **VISOR_SOPORTE** */
	$("table .collapse").click(function(){
		console.log("Folio: " + $(this).children(2)[1].innerText);
		$(".cont-card-cat-etapas").attr("class","col-xl-9 col-md-12 col-sm-12 mb-xl-0 mb-4 cont-card-cat-etapas");
		$(".cont-card-verif-ent").fadeIn(300);
	});
	
	/* FIN **VISOR_SOPORTE** */
});


var show = (function (){
	var X99100 = function(){
		var param = arguments[0];
		if (param == 'show') {
			$('#load_screen').show();
		}else{
			$('#load_screen').hide();
		}
	}

	var X99200 = function(){
		var param = arguments[0];
		$('#'+param).modal('show');
	}

	return{
		loading: X99100,
		modal: X99200,
	}
})();

/* ----------------------------------------------------------------INI CUENTA REGRESIVA */
//var end = new Date('00/00/0000 9:30 AM');
const cuentaRegresiva = 1;
var end = new Date();
end.setMinutes(end.getMinutes() + cuentaRegresiva); //se le agrega 10 minutos 
var _second = 1000;
var _minute = _second * 60;
var _hour = _minute * 60;
var _day = _hour * 24;
var timer;

function showRemaining() {
	$("#countdown button").removeClass("d-none");
    var now = new Date();
    var distance = end - now;
    
    if (distance <= 0) {
        clearInterval(timer);
        $("#countdown .countdown-text").html('EXPIRED');
        window.location.reload();
        //return;
    }
    
    var days = Math.floor(distance / _day);
    var hours = Math.floor((distance % _day) / _hour);
    var minutes = Math.floor((distance % _hour) / _minute);
    var seconds = Math.floor((distance % _minute) / _second);
	$("#countdown .countdown-text").html('');
    $("#countdown .countdown-text").html(minutes + 'm:'+seconds + 's');
}

function initTimer(){
	timer = setInterval(showRemaining, 1000);
}
/* ----------------------------------------------------------------FIN CUENTA REGRESIVA */