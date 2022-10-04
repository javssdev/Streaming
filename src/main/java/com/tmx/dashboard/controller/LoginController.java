package com.tmx.dashboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tmx.dashboard.database.DatabaseOperations;
import com.tmx.dashboard.model.Usuario;


@Controller
public class LoginController {

	@Autowired
	private DatabaseOperations<Usuario> DBOUsuario;

//modulo de aprovisanamiento de plataformas de straming
	@GetMapping({"/login", "/"})
	public ModelAndView index(Model model){
		ModelAndView mav = new ModelAndView("login");

		List<Usuario> list = DBOUsuario.findAll(Usuario.class, null);
		System.err.println("path server :: "+System.getProperty("user.dir"));
		System.err.println("list :: "+list.size());
		mav.addObject("usuarios", list);
		list.forEach((Usuario u) -> {
			System.err.println(u.getCvUsuario().concat(" ==> ").concat(Long.toString(u.getTipoUsuario())));
			
		});

		return mav;
	}
}
