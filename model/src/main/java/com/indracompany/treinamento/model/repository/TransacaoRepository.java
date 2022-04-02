package com.indracompany.treinamento.model.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.Transacao;

public interface TransacaoRepository extends GenericCrudRepository<Transacao, Long> {
	
		
	List<Transacao> findByDataBetween(Date data1, Date data2);

}
