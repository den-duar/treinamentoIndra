package com.indracompany.treinamento.model.dto;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;

@Data
public class ConsultarExtratoDTO implements Serializable {
	
	private String agencia;
	private String numeroConta;
	private Date data1;
	private Date data2;

}
