package com.indracompany.treinamento.model.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class TransacaoDTO implements Serializable {
	
	
	private String agencia;
	private String numeroConta;
	private String tipoTransacao;
	private double valor;

}
