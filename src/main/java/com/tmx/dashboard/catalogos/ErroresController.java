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

import com.tmx.dashboard.dao.CatErrores;
import com.tmx.dashboard.database.DatabaseOperations;
import com.tmx.dashboard.model.Errores;
import com.tmx.dashboard.model.Etapas;
import com.tmx.dashboard.model.Plataformas;
import com.tmx.dashboard.utils.Cs;

@Controller
public class ErroresController {

	private static final String REDIRECT = "redirect:/errores";

	@Autowired
	private DatabaseOperations<Errores> dbo;

	@Autowired
	private DatabaseOperations<Etapas> dboEtapas;

	@RequestMapping("/errores")
	public ModelAndView init(ModelAndView m){
		List<Errores> list = dbo.findAll(Errores.class, null);
		List<Etapas> listEtapa = dboEtapas.findAll(Etapas.class, null);
		m.addObject("menu", Cs.MENU);
		m.addObject("catalogos", Cs.MENU_CAT);
		m.addObject("list", getCatalog());
		m.addObject("object", new Plataformas());
		m.addObject("list", list);
		m.addObject("listEtapas", listEtapa);

		m.setViewName("/catalogos/errores");
		return m;
	}

	@PostMapping("/save/error")
	public String save(CatErrores obj, RedirectAttributes flash){
		try {
			Etapas etapa = new Etapas();
			etapa.setClave(obj.getEtapa());
			Errores error = new Errores();
			error.setClave(obj.getClave());
			error.setIntentos(obj.getIntentos());
			error.setPersistencia(obj.getPersistencia());
			error.setEtapa(etapa);
			error.setDescripcion(obj.getDescripcion());
			dbo.save(error);
			flash.addFlashAttribute(Cs.SUCCSES, "Registro agregado exitosamente");
		} catch (Exception e){
			e.printStackTrace();
			flash.addFlashAttribute(Cs.ERROR, "Error al guardar el registro");
		}
		return REDIRECT;
	}

	@PostMapping("/update/error")
	public String update(CatErrores obj, RedirectAttributes flash){
		try {
			Etapas etapa = new Etapas();
			etapa.setClave(obj.getEtapa());
			Errores entity = new Errores();
			entity.setClave(obj.getClave());
			entity.setPersistencia(obj.getPersistencia());
			entity.setIntentos(obj.getIntentos());
			entity.setDescripcion(obj.getDescripcion());
			entity.setEtapa(etapa);
			dbo.updateById(entity);
			flash.addFlashAttribute(Cs.SUCCSES, "Registro actualizado exitosamente");
		} catch (Exception e){
			e.printStackTrace();
			flash.addFlashAttribute(Cs.ERROR, "Error al actualziar el registro");
		}
		return REDIRECT;
	}

	@PostMapping("/delete/error")
	public String delete(String id, RedirectAttributes flash){
		try {
			dbo.deleteById(Errores.class, id);
			flash.addFlashAttribute(Cs.ERROR, "Registro eliminado exitosamente");
		} catch (Exception e){
			e.printStackTrace();
			flash.addFlashAttribute(Cs.ERROR, "Error al eliminar el registro");
		}
		return REDIRECT;
	}

	@GetMapping("/find/error{id}")
	public ResponseEntity<CatErrores> find(@PathVariable(value = "id") String id){
		try {
			Errores entity = dbo.findById(Errores.class, id);
			CatErrores error = new CatErrores();
			error.setClave(entity.getClave().trim());
			error.setDescripcion(entity.getDescripcion().trim());
			error.setIntentos(entity.getIntentos());
			error.setPersistencia(entity.getPersistencia());
			error.setEtapa(entity.getEtapa().getClave());
			error.setEtapaDesc(entity.getEtapa().getDescripcion());
			return new ResponseEntity<>(error, HttpStatus.OK);
		} catch (Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	private List<Errores> getCatalog(){
		List<Errores> list = new ArrayList<>();
		try {
			list = dbo.findAll(Errores.class, null);
		} catch (Exception e){
			e.printStackTrace();
		}
		return list;
	}
}