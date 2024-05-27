package com.generation.gamestore.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.gamestore.model.Categorias;
import com.generation.gamestore.model.Produtos;

public interface CategoriasRepository extends JpaRepository<Categorias, Long>{
	public List<Categorias> findAllByDescricaoContainingIgnoreCase(@Param("descricao") String descricao);

}
