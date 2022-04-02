package com.indracompany.treinamento.controller.rest;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.indracompany.treinamento.model.dto.ConsultarExtratoDTO;
import com.indracompany.treinamento.model.dto.TransacaoDTO;
import com.indracompany.treinamento.model.entity.Transacao;
import com.indracompany.treinamento.model.service.TransacaoService;

@RestController
@RequestMapping("rest/extrato")
public class TransacaoRest extends GenericCrudRest<Transacao, Long, TransacaoService > {
	
	@Autowired
	private TransacaoService transacaoService;

	@GetMapping(value = "/buscarExtrato/{conta}/{agencia}/{data1}/{data2}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<Transacao>> consultarExtrato( String conta, String agencia, Date data1, Date data2){
		ConsultarExtratoDTO buscaExtrato = new ConsultarExtratoDTO();
		buscaExtrato.setAgencia(agencia);
		buscaExtrato.setNumeroConta(conta);
		buscaExtrato.setData1(data1);
		buscaExtrato.setData2(data2);
		List<Transacao> extrato = transacaoService.consultarExtrato(buscaExtrato);
		if(extrato == null || extrato.isEmpty()) {
			return new ResponseEntity<List<Transacao>>(extrato, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Transacao>>(extrato, HttpStatus.OK);
	}
}
