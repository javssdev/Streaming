package com.tmx.dashboard.catalogos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import com.tmx.dashboard.model.Plataformas;
import com.tmx.dashboard.utils.Cs;

@Controller
public class PlataformasController {

	private static final String REDIRECT = "redirect:/plataformas";

	@Autowired
	private DatabaseOperations<Plataformas> dbo;

	@RequestMapping("/plataformas")
	public ModelAndView init(ModelAndView m){
		m.addObject("menu", Cs.MENU);
		m.addObject("catalogos", Cs.MENU_CAT);
		m.addObject("list", getCatalog());
		m.addObject("object", new Plataformas());
		m.setViewName("/catalogos/plataformas");
		return m;
	}

	@PostMapping("/save/plataforma")
	public String save(Plataformas obj, RedirectAttributes flash){
		try {
			dbo.save(obj);
			flash.addFlashAttribute(Cs.SUCCSES, "Registro agregado exitosamente");
		} catch (Exception e){
			e.printStackTrace();
			flash.addFlashAttribute(Cs.ERROR, "Error al guardar el registro");
		}
		return REDIRECT;
	}

	@PostMapping("/update/plataforma")
	public String update(Plataformas obj, RedirectAttributes flash){
		try {
			dbo.updateById(obj);
			flash.addFlashAttribute(Cs.SUCCSES, "Registro actualizado exitosamente");
		} catch (Exception e){
			e.printStackTrace();
			flash.addFlashAttribute(Cs.ERROR, "Error al actualziar el registro");
		}
		return REDIRECT;
	}

	@PostMapping("/delete/plataforma")
	public String delete(String id, RedirectAttributes flash){
		try {
			dbo.deleteById(Plataformas.class, id);
			flash.addFlashAttribute(Cs.ERROR, "Registro eliminado exitosamente");
		} catch (Exception e){
			e.printStackTrace();
			flash.addFlashAttribute(Cs.ERROR, "Error al eliminar el registro");
		}
		return REDIRECT;
	}

	@GetMapping("/find/plataforma{id}")
	public ResponseEntity<Plataformas> find(@PathVariable(value = "id") String id){
		try {
			return new ResponseEntity<>(dbo.findById(Plataformas.class, id), HttpStatus.OK);
		} catch (Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	private Page<Plataformas> getCatalog(){
		Page<Plataformas> list = null;
		try {
			list = dbo.findAll(Plataformas.class, null, null);
		} catch (Exception e){
			e.printStackTrace();
		}
		return list;
	}
}
