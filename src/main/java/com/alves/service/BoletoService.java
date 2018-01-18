package com.alves.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.alves.model.PagamentoComBoleto;

@Service
public class BoletoService {

	public void preencherBoleto(PagamentoComBoleto pagto, Date instante) {
		
		Calendar calendar =  Calendar.getInstance();
		calendar.setTime(instante);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		
		pagto.setDataVencimento(calendar.getTime());
	}

}
