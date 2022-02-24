package com.generation.farmacia.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table (name = "tb_categorias")
public class Categoria {
	
	@Id //declara que este atributo será a PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) //declara que será gerado o ID automaticamente, via autoincrement
	private Long id;
	
	@NotBlank(message = "Categoria é obrigatório!")
	private String categoria;

	private String descricao;
	
	//a Categoria é o Lado 1 e manda no Lado N
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL) //será mapeado na Classe Produto
	@JsonIgnoreProperties("categoria") // evita a recursividade
	private List <Produto>produto;// aqui ele irá trazer a relação LISTADA de produtos, da Classe Produtos. 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}
	
	
}
