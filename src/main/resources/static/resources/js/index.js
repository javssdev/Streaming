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
	}else{
		$('.g-sidenav-show').addClass('rtl');
	}
});

$('aside .navbar-nav .nav-item .nav-link').click(function(){
	$('aside .navbar-nav .nav-item .nav-link').removeClass('active');
	$(this).addClass('active');
});