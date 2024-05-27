package com.generation.gamestore.Controller;

import java.util.List;
import java.util.Optional;

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

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/Categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriasController {
	
	@Autowired
	CategoriasRepository categoriasRepository;
	
	@Autowired
	ProdutosRepository produtosRepository;

	@GetMapping("/{id}")
	public Optional<Object> getOne(@PathVariable Long id) {
		if(categoriasRepository.existsById(id)) {
			return categoriasRepository.findById(id).map(response -> ResponseEntity.ok(response));
		}
		
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não existe!!", null);
	}
	
	@GetMapping
	public ResponseEntity<List<Categorias>> getAll(){
		return ResponseEntity.ok(categoriasRepository.findAll());
	}
	
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Categorias>> getByDescricao(@PathVariable String descricao){
		return ResponseEntity.ok(categoriasRepository.findAllByDescricaoContainingIgnoreCase(descricao));
	}
	
	@PostMapping
	public ResponseEntity<Categorias> postCategory(@Valid @RequestBody Categorias categoria){
		if(!categoriasRepository.existsById(categoria.getId())) {
			return ResponseEntity.ok(categoriasRepository.save(categoria));
			
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping
	public ResponseEntity<Categorias> putCategory(@Valid @RequestBody Categorias categoria){
		if(categoriasRepository.findById(categoria.getId()).isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tema já existe!!", null);
		}
		
		return ResponseEntity.ok(categoriasRepository.save(categoria));
	}
	
	@DeleteMapping("/{id}")
	public void deleteCategory(@PathVariable Long id) {
		var isValidId = categoriasRepository.findById(id);
		
		if(isValidId.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id não identificado", null);
		}
		
		categoriasRepository.deleteById(id);
		
	}
	
}
