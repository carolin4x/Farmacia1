package com.generation.farmacia.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.farmacia.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	public List<Produto> findAllByNomeContainingIgnoreCase(String nome);
	// o Nome em 'findAllBy' não é um Objeto mas sim o atributo declarado na Classe
	// select * from tb_produtos where nome like "%nome que ta ali%"

	public List <Produto> findAllByPrecoGreaterThanOrderByPreco (BigDecimal preco);
	
	public List <Produto> findAllByPrecoLessThanOrderByPreco (BigDecimal preco);
	
	public List <Produto> findAllByNomeContainingIgnoreCaseAndLaboratorioContainingIgnoreCase (String nome, String laboratorio);
	
	public List <Produto> findAllByPrecoBetweenOrderByPreco(BigDecimal preco1, BigDecimal preco2);
}
