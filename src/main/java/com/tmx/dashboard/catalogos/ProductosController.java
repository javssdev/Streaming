package com.tmx.dashboard.catalogos;

import java.util.List;

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

import com.tmx.dashboard.dao.CatProducts;
import com.tmx.dashboard.database.DatabaseOperations;
import com.tmx.dashboard.model.Plataformas;
import com.tmx.dashboard.model.Productos;
import com.tmx.dashboard.utils.Cs;

@Controller
public class ProductosController {

	private static final String REDIRECT = "redirect:/productos";

	@Autowired
	private DatabaseOperations<Productos> dbo;

	@Autowired
	private DatabaseOperations<Plataformas> dboPlatform;

	@RequestMapping("/productos")
	public ModelAndView init(ModelAndView m){
		Page<Productos> list = dbo.findAll(Productos.class, null, null);
		List<Plataformas> listPlatform = dboPlatform.findAll(Plataformas.class, null);

		m.addObject("menu", Cs.MENU);
		m.addObject("catalogos", Cs.MENU_CAT);
		m.addObject("list", getCatalog());
		m.addObject("object", new Plataformas());
		m.addObject("list", list);
		m.addObject("listPlatform", listPlatform);

		m.setViewName("/catalogos/productos");
		return m;
	}

	@PostMapping("/save/producto")
	public String save(CatProducts obj, RedirectAttributes flash){
		try {
			Productos prod = new Productos();
			Plataformas plat = new Plataformas();
			prod.setClave(obj.getClave());
			prod.setDescripcion(obj.getDescripcion());
			plat.setClave(obj.getPlataforma());
			prod.setPlataforma(plat);
			dbo.save(prod);
			flash.addFlashAttribute(Cs.SUCCSES, "Registro agregado exitosamente");
		} catch (Exception e){
			e.printStackTrace();
			flash.addFlashAttribute(Cs.ERROR, "Error al guardar el registro");
		}
		return REDIRECT;
	}

	@PostMapping("/update/producto")
	public String update(CatProducts obj, RedirectAttributes flash){
		try {
			Plataformas plat = new Plataformas();
			plat.setClave(obj.getPlataforma());
			Productos entity = new Productos();
			entity.setClave(obj.getClave());
			entity.setDescripcion(obj.getDescripcion());
			entity.setPlataforma(plat);
			dbo.updateById(entity);
			flash.addFlashAttribute(Cs.SUCCSES, "Registro actualizado exitosamente");
		} catch (Exception e){
			e.printStackTrace();
			flash.addFlashAttribute(Cs.ERROR, "Error al actualziar el registro");
		}
		return REDIRECT;
	}

	@PostMapping("/delete/producto")
	public String delete(String id, RedirectAttributes flash){
		try {
			dbo.deleteById(Productos.class, id);
			flash.addFlashAttribute(Cs.ERROR, "Registro eliminado exitosamente");
		} catch (Exception e){
			e.printStackTrace();
			flash.addFlashAttribute(Cs.ERROR, "Error al eliminar el registro");
		}
		return REDIRECT;
	}

	@GetMapping("/find/producto{id}")
	public ResponseEntity<CatProducts> find(@PathVariable(value = "id") String id){
		try {
			Productos entity = dbo.findById(Productos.class, id);
			CatProducts prod = new CatProducts();
			prod.setClave(entity.getClave().trim());
			prod.setDescripcion(entity.getDescripcion().trim());
			prod.setPlataforma(entity.getPlataforma().getClave().trim());
			prod.setPlatDesc(entity.getPlataforma().getDescripcion());
			return new ResponseEntity<>(prod, HttpStatus.OK);
		} catch (Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	private Page<Productos> getCatalog(){
		Page<Productos> list = null;
		try {
			list = dbo.findAll(Productos.class, null, null);
		} catch (Exception e){
			e.printStackTrace();
		}
		return list;
	}
}
