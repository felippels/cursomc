package com.felippels.cursomc.services.valiidations;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.felippels.cursomc.domain.enums.TipoPessoa;
import com.felippels.cursomc.dto.ClienteNewDTO;
import com.felippels.cursomc.repositories.ClienteRepository;
import com.felippels.cursomc.resources.exceptions.FieldMessage;
import com.felippels.cursomc.services.valiidations.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	@Autowired
	private ClienteRepository clienteRepository;
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if (( objDto.getTipoPessoa().equals(TipoPessoa.PESSOAFISICA.getCod())) && ( !BR.isValidCPF( objDto.getCpfOuCnpj()))) 
		{list.add(new FieldMessage("CpfOuCnpj", "CPF inválido")); }
		if (( objDto.getTipoPessoa().equals(TipoPessoa.PESSOAJURIDICA.getCod())) && ( !BR.isValidCNPJ( objDto.getCpfOuCnpj()))) 
		{list.add(new FieldMessage("CpfOuCnpj", "CNPJ inválido")); }
		
		if (clienteRepository.findByEmail(objDto.getEmail())!=null) 
		{list.add(new FieldMessage("Email", "EMail Já cadastrado")); }
		
		if (clienteRepository.findByCpfOuCnpj(objDto.getCpfOuCnpj())!=null) 
		{list.add(new FieldMessage("CpfOuCnpj", "CNPJ ou CPF Já cadastrado")); }
		
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
