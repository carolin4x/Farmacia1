package com.generation.farmacia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.farmacia.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository <Categoria, Long> {

	public List <Categoria> findAllByCategoriaContainingIgnoreCase(String categoria);
	
	//a Categoria em 'findAllBy' não é um Objeto mas sim o atributo declarado na Classe Categoria 
	// select * from tb_categorias where nome like "%nome que ta ali%"
}
