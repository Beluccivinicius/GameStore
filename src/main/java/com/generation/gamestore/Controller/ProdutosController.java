package com.generation.gamestore.Controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.service.annotation.DeleteExchange;

import com.generation.gamestore.Repository.CategoriasRepository;
import com.generation.gamestore.Repository.ProdutosRepository;
import com.generation.gamestore.model.Categorias;
import com.generation.gamestore.model.Produtos;

import jakarta.validation.Valid;
import net.datafaker.Faker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/Produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutosController {
	@Autowired
	private ProdutosRepository produtoRepository;
	
	@Autowired
	private CategoriasRepository categoriasRepository;
	
	Faker faker = new Faker();
	
	@GetMapping()
	public ResponseEntity<List<Produtos>> getAllProduto() {
		return ResponseEntity.ok(produtoRepository.findAll());
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Produtos> getOneProduto(@PathVariable Long id){
			return produtoRepository.findById(id)
					.map(n -> ResponseEntity.ok(n))
					.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produtos>> getByNomeProduto(@PathVariable String nome){
		return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	
	//Populando a tabela pordutos via API
//	@PostMapping
//	public ResponseEntity<Produtos> postProduto(){
//		Categorias categorias = new Categorias(1l);
//		
//		Produtos produto = new Produtos(faker.videoGame().title(), faker.internet().image(), faker.text().text(100), faker.date().birthday(), categorias);
//		
//		return ResponseEntity.ok(produtoRepository.save(produto));
//	}
	
	@PostMapping
	public ResponseEntity<Produtos> postProduto(@Valid @RequestBody Produtos produto){
		
		if(!categoriasRepository.existsById(produto.getCategoria().getId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "categoria não existe", null);
		}

		return ResponseEntity.ok(produtoRepository.save(produto));
	}
	
	@PutMapping
	public ResponseEntity<Produtos> putMethodName(@Valid @RequestBody Produtos produto) {
		
		if(produtoRepository.existsById(produto.getId())){
			
			if(categoriasRepository.existsById(produto.getCategoria().getId())) {				
				return ResponseEntity.ok(produtoRepository.save(produto));			
			}
		
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id da categoria não existe", null);			
		}
		
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id do produto não existe", null);			
		
	}
	
	@DeleteMapping("{id}")
	public void deleteById(@PathVariable Long id) {
		if(!produtoRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id do produto não existe", null);
		}
		
		produtoRepository.deleteById(id);
		
	}
	
	
}
