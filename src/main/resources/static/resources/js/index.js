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


/* ----------------------------------------------------------------INI SELECION MENU */
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
});
