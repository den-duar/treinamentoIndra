package com.indracompany.treinamento.model.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.dto.ConsultarExtratoDTO;
import com.indracompany.treinamento.model.dto.TransacaoDTO;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.Transacao;
import com.indracompany.treinamento.model.repository.TransacaoRepository;

@Service
public class TransacaoService extends GenericCrudService<Transacao, Long, TransacaoRepository>{
	

	@Autowired
	private ContaBancariaService contaBancariaService;
	
	public void salvarTran(Transacao tran) {
		super.salvar(tran);
	}
	
	public List<TransacaoDTO> consultarExtrato(ConsultarExtratoDTO buscaExtrato){
		List<TransacaoDTO> extrato = null;
		ContaBancaria c = contaBancariaService.consultarConta(buscaExtrato.getAgencia(), buscaExtrato.getNumeroConta());
		List<Transacao> resultado = repository.findByDataBetween(buscaExtrato.getData1(),buscaExtrato.getData2());
		if (resultado == null || resultado.isEmpty()) {
			throw new AplicacaoException(ExceptionValidacoes.ALERTA_NENHUM_REGISTRO_ENCONTRADO);
		}
		TransacaoDTO transacaoDTO = new TransacaoDTO();
		transacaoDTO.setAgencia(buscaExtrato.getAgencia());
		transacaoDTO.setNumeroConta(buscaExtrato.getNumeroConta());
		for(int i = 0; i <= resultado.size(); i++) {
			transacaoDTO.setTipoTransacao(resultado.get(i).getTipoTransacao());
			transacaoDTO.setValor(resultado.get(i).getValor());
			extrato.add(transacaoDTO);
		}
		return extrato;
	}

}
