package com.generation.farmacia.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.farmacia.model.Produto;
import com.generation.farmacia.repository.CategoriaRepository;
import com.generation.farmacia.repository.ProdutoRepository;

@RestController // define que é uma Classe Controladora
@RequestMapping("/produtos") // define o end do servidor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

	@Autowired // injeção de independência
	private ProdutoRepository produtoRepository; // usamos a interface Repository, pois ela tem acesso à Classe Model
	// aqui cria-se a relação entre Repository e Controller
	// ProdutoRepository é interface, produtoRepository: objeto da interface

	@Autowired // injeção de independência
	private CategoriaRepository categoriaRepository;

	@GetMapping // criar método
	public ResponseEntity<List<Produto>> getAll() { // vai devolver um objeto de resposta, e no corpo vai trazer varios
													// objetos da classe postagem. /Precisamos de uma Colecctiona
														// <List> pra trazer os objetos <Postagem>
		return ResponseEntity.ok(produtoRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Long id) {
		return produtoRepository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> getByNome (@PathVariable String nome) {
		return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@GetMapping("/nomelab/{nome}~{laboratorio}")
	public ResponseEntity<List<Produto>> findByNomeLaboratorioProduto (@PathVariable String nome, @PathVariable String laboratorio){
		return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCaseAndLaboratorioContainingIgnoreCase(nome,laboratorio));
	}
	
	@GetMapping("/maior/{preco}")
	public ResponseEntity<List<Produto>> findByMaiorQue (@PathVariable BigDecimal preco){
		return ResponseEntity.ok(produtoRepository.findAllByPrecoGreaterThanOrderByPreco(preco));
	}
	
	
	@GetMapping("/menor/{preco}")
	public ResponseEntity<List<Produto>> findByMenorQue (@PathVariable BigDecimal preco){
		return ResponseEntity.ok(produtoRepository.findAllByPrecoLessThanOrderByPreco(preco));
	}
	
	@GetMapping("/entre/{preco1}~{preco2}")
	public ResponseEntity<List<Produto>> findByEntre (@PathVariable BigDecimal preco1, @PathVariable BigDecimal preco2){
		return ResponseEntity.ok(produtoRepository.findAllByPrecoBetweenOrderByPreco(preco1, preco2));
	}

	// no PostMapping eu passo o meu Objeto inteiro após o @RequestBody
	@PostMapping
	public ResponseEntity<Produto> postProduto (@Valid @RequestBody Produto produto) { 
		if (categoriaRepository.existsById(produto.getCategoria().getId()))
			return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
		return ResponseEntity.badRequest().build();
	}
		
	@PutMapping
	public ResponseEntity<Produto> putProduto(@Valid @RequestBody Produto produto) {
		if (produtoRepository.existsById(produto.getId())) {
			if (categoriaRepository.existsById(produto.getCategoria().getId()))
				return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto));
			else
				return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.notFound().build();
	} 
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduto(@PathVariable Long id) { 
		return produtoRepository.findById(id)
				.map(resp -> { 
					produtoRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
					})
				.orElse(ResponseEntity.notFound().build());
	}
	//findReferenceFieldBetween(value1,value2);
}

