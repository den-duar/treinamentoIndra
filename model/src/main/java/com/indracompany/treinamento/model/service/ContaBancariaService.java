package com.indracompany.treinamento.model.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.dto.ClienteDTO;
import com.indracompany.treinamento.model.dto.ConsultaContaBancariaDTO;
import com.indracompany.treinamento.model.dto.TransferenciaBancariaDTO;
import com.indracompany.treinamento.model.entity.Cliente;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.Transacao;
import com.indracompany.treinamento.model.repository.ContaBancariaRepository;


@Service
public class ContaBancariaService extends GenericCrudService<ContaBancaria, Long, ContaBancariaRepository>{
	
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private TransacaoService transacaoService;
	
	public double consultarSaldo(String agencia, String numero) {
		ContaBancaria c = consultarConta(agencia, numero);
		return c.getSaldo();
	}

	
	@Transactional(rollbackOn = Exception.class)
	public void depositar (String agencia, String numeroConta, double valor) {
		ContaBancaria conta = consultarConta(agencia, numeroConta);
		conta.setSaldo(conta.getSaldo() + valor);
		
		super.salvar(conta);
		Transacao tran = new Transacao();
		tran.setTipoTransacao("DEPOSITO");
		tran.setValor(valor);
		tran.setContaOrigem(conta);
		long milisec = System.currentTimeMillis();
		java.sql.Date data = new java.sql.Date(milisec);
		tran.setData(data);
		transacaoService.salvar(tran);
		
	}
	
	@Transactional(rollbackOn = Exception.class)
	public void sacar (String agencia, String numeroConta, double valor) {
		ContaBancaria conta = consultarConta(agencia, numeroConta);
		
		if (conta.getSaldo() < valor) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_SALDO_INEXISTENTE);
		}
		
		conta.setSaldo(conta.getSaldo() - valor);
		super.salvar(conta);
		
		Transacao tran = new Transacao();
		tran.setTipoTransacao("SAQUE");
		tran.setValor(-valor);
		tran.setContaOrigem(conta);
		long milisec = System.currentTimeMillis();
		java.sql.Date data = new java.sql.Date(milisec);
		tran.setData(data);
		transacaoService.salvarTran(tran);
	}
	
	@Transactional(rollbackOn = Exception.class)
	public void transferir(TransferenciaBancariaDTO dto) {
		
		ContaBancaria contaOrigem = consultarConta(dto.getAgenciaOrigem(), dto.getNumeroContaOrigem());
		if (contaOrigem.getSaldo() < dto.getValor()) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_SALDO_INEXISTENTE);
		}
		contaOrigem.setSaldo(contaOrigem.getSaldo() - dto.getValor());
		super.salvar(contaOrigem);
		
		ContaBancaria contaDestino = consultarConta(dto.getAgenciaDestino(), dto.getNumeroContaDestino());
		contaDestino.setSaldo(contaDestino.getSaldo() + dto.getValor());
		super.salvar(contaDestino);
		
		Transacao tranSaida = new Transacao();
		tranSaida.setTipoTransacao("TRANSFERÊNCIA SAÍDA");
		tranSaida.setValor(-dto.getValor());
		tranSaida.setContaOrigem(consultarConta(dto.getAgenciaOrigem(), dto.getNumeroContaOrigem()));
		tranSaida.setContaDestino(consultarConta(dto.getAgenciaDestino(),dto.getNumeroContaDestino()));
		long milisec = System.currentTimeMillis();
		java.sql.Date data = new java.sql.Date(milisec);
		tranSaida.setData(data);
		transacaoService.salvar(tranSaida);
		Transacao tranRecebida = new Transacao();
		tranRecebida.setTipoTransacao("TRANSFERÊNCIA RECEBIDA");
		tranRecebida.setContaOrigem(consultarConta(dto.getAgenciaDestino(), dto.getNumeroContaDestino()));
		tranRecebida.setContaDestino(consultarConta(dto.getAgenciaOrigem(), dto.getNumeroContaOrigem()));
		tranRecebida.setValor(dto.getValor());
		tranRecebida.setData(data);
		transacaoService.salvar(tranRecebida);
	}
	

	public ContaBancaria consultarConta (String agencia, String numeroConta) {
		ContaBancaria c = repository.findByAgenciaAndNumero(agencia, numeroConta);
		
		if (c == null) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_CONTA_INVALIDA);
		}
		
		return c;
	}
	
	public List<ConsultaContaBancariaDTO> obterContasPorCpf(String cpf){

		List<ConsultaContaBancariaDTO> listaContasRetorno = new ArrayList<>();
		Cliente cli = clienteService.buscarCliente(cpf);

		List<ContaBancaria> listaContasCliente = repository.findByCliente(cli);
		for (ContaBancaria conta : listaContasCliente) {
			ConsultaContaBancariaDTO dtoConta = new ConsultaContaBancariaDTO();
			BeanUtils.copyProperties(conta, dtoConta);
			dtoConta.setCpf(conta.getCliente().getCpf());
			dtoConta.setNomeTitular(conta.getCliente().getNome());
			listaContasRetorno.add(dtoConta);
		}


		return listaContasRetorno;
	}
}
