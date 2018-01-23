package com.felippels.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.felippels.cursomc.domain.PagamentoComBoleto;

@Service
public class BoletoService {

 public void preencherPagamentoComBoleto(PagamentoComBoleto pagamento, Date instanteDoPagamento) {
	 //situação hipotética
	 Calendar calendario =  Calendar.getInstance();
	 calendario.setTime(instanteDoPagamento);
	 calendario.add(Calendar.DAY_OF_MONTH, 7);
	 pagamento.setDataVencimento(calendario.getTime());
	 
 }
}
