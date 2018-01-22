package com.felippels.cursomc.services.valiidations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.felippels.cursomc.domain.Cliente;
import com.felippels.cursomc.dto.ClienteDTO;
import com.felippels.cursomc.repositories.ClienteRepository;
import com.felippels.cursomc.resources.exceptions.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private HttpServletRequest servletRequest;
	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		Map<String, String> map =(Map<String, String>) servletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer id = Integer.parseInt( map.get("id"));
		List<FieldMessage> list = new ArrayList<>();
				
		Cliente clienteVaidacao = clienteRepository.findByEmail(objDto.getEmail());
		if ((clienteVaidacao!=null) && (!clienteVaidacao.getId().equals(id))) 
		{list.add(new FieldMessage("Email", "EMail Já cadastrado")); }
		
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
