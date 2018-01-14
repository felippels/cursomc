package com.felippels.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.felippels.cursomc.domain.Estado;

@RestController
@RequestMapping(value="/estados")
public class EstadoResource {
	@RequestMapping(method=RequestMethod.GET)
	public List<Estado>  listar() {
		
		Estado est1 = new Estado(1,"Ceará");
		Estado est2 = new Estado(2,"São Paulo");
		List<Estado> lista = new ArrayList<Estado >(); 
		lista.add(est1);
		lista.add(est2);
		return lista;
	}

}
