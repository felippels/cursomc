package com.felippels.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.felippels.cursomc.domain.Estado;
import com.felippels.cursomc.services.EstadoService;

@RestController
@RequestMapping(value="/estados")
public class EstadoResource {
	
	@Autowired
	private EstadoService estadoService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?>  find(@PathVariable Integer id) {
		Estado estado = estadoService.buscar(id);
		
		return ResponseEntity.ok().body(estado);
		
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Estado>  listar() {
		
		Estado est1 = new Estado(null,"Ceará");
		Estado est2 = new Estado(null,"São Paulo");
		List<Estado> lista = new ArrayList<Estado >(); 
		lista.add(est1);
		lista.add(est2);
		return lista;
	}

}
