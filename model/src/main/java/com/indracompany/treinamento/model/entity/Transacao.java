package com.indracompany.treinamento.model.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "extrato_dennis")


public class Transacao extends GenericEntity<Long>{
	
	
	private static final long serialVersionUID = 1L;

	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
			

	@ManyToOne
	@JoinColumn(name= "fk_id_cont_dest", nullable = true)
	private ContaBancaria contaDestino;
	
	@Column(nullable = false)
	private String tipoTransacao;
	
	@Column(nullable = false)
	private double valor;
		
	@Column(nullable = false)
	private Date data;
	
	@ManyToOne
	@JoinColumn(name = "fk_id_cont_ori")
	private ContaBancaria contaOrigem;
	
	

}
