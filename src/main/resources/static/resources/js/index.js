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
		$(".box-info-st span").removeClass("text-dark");
		$(this).addClass("active");
		$(this).children().addClass("text-dark");
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