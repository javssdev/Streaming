package com.tmx.dashboard.catalogos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tmx.dashboard.database.DatabaseOperations;
import com.tmx.dashboard.model.Etapas;
import com.tmx.dashboard.model.Plataformas;
import com.tmx.dashboard.utils.Cs;

@Controller
public class EtapasController {

	private static final String REDIRECT = "redirect:/etapas";

	@Autowired
	private DatabaseOperations<Etapas> dbo;

	@RequestMapping("/etapas")
	public ModelAndView init(ModelAndView m){
		m.addObject("menu", Cs.MENU);
		m.addObject("catalogos", Cs.MENU_CAT);
		m.addObject("list", getCatalog());
		m.addObject("object", new Plataformas());

		m.setViewName("/catalogos/etapas");
		return m;
	}

	@PostMapping("/save/etapa")
	public String save(Etapas obj, RedirectAttributes flash){
		try {
			dbo.save(obj);
			flash.addFlashAttribute(Cs.SUCCSES, "Registro agregado exitosamente");
		} catch (Exception e){
			e.printStackTrace();
			flash.addFlashAttribute(Cs.ERROR, "Error al guardar el registro");
		}
		return REDIRECT;
	}

	@PostMapping("/update/etapa")
	public String update(Etapas obj, RedirectAttributes flash){
		try {
			dbo.updateById(obj);
			flash.addFlashAttribute(Cs.SUCCSES, "Registro actualizado exitosamente");
		} catch (Exception e){
			e.printStackTrace();
			flash.addFlashAttribute(Cs.ERROR, "Error al actualziar el registro");
		}
		return REDIRECT;
	}

	@PostMapping("/delete/etapa")
	public String delete(String id, RedirectAttributes flash){
		try {
			dbo.deleteById(Etapas.class, id);
			flash.addFlashAttribute(Cs.ERROR, "Registro eliminado exitosamente");
		} catch (Exception e){
			e.printStackTrace();
			flash.addFlashAttribute(Cs.ERROR, "Error al eliminar el registro");
		}
		return REDIRECT;
	}

	@GetMapping("/find/etapa{id}")
	public ResponseEntity<Etapas> find(@PathVariable(value = "id") String id){
		try {
			return new ResponseEntity<>(dbo.findById(Etapas.class, id), HttpStatus.OK);
		} catch (Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	private List<Etapas> getCatalog(){
		List<Etapas> list = new ArrayList<>();
		try {
			list = dbo.findAll(Etapas.class, null);
		} catch (Exception e){
			e.printStackTrace();
		}
		return list;
	}
}
