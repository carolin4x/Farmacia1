package com.generation.farmacia.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity 
@Table(name = "tb_produtos")
public class Produto {
	
	@Id // declara que este atributo será a PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) // declara que será gerado o ID automaticamente, via autoincrement
	private Long id;
	
	@NotBlank(message = "Nome é obrigatório!")
	private String nome;
	
	@NotBlank(message = "Descrição é obrigatória!")
	private String descricao;
	
	private int quantidade;
	
	private String laboratorio;
	
	//o Produto é o Lado N e e obedece o LAdo 1(Categoria)
	@JsonFormat(shape = JsonFormat.Shape.STRING) //coloca 2 casas após virugula no BigDecimal 
	@NotNull(message = "Preço é obrigatório!") //valor n pode ser nulo, mas pode ser espaço vazio
	@Positive(message = "Digite um valor maior do que zero") //força que o valor seja positivo
	private BigDecimal preco;
	
	private String foto;
	
	
	@ManyToOne //(FK); aqui não tem parâmetro pois quem manda é a Categoria(Lado 1)
	@JsonIgnoreProperties("produto") // evita a recursividade
	private Categoria categoria;// aqui crio um Objeto que está sendo mapeado na Classe Categoria


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public int getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}


	public String getLaboratorio() {
		return laboratorio;
	}


	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}


	public BigDecimal getPreco() {
		return preco;
	}


	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}


	public String getFoto() {
		return foto;
	}


	public void setFoto(String foto) {
		this.foto = foto;
	}


	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
}
