package com.generation.gamestore.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_produtos")
public class Produtos {
	
	
//	public Produtos(
//			String nome, String foto, String sinopse,
//			Timestamp localDate, 
//			Categorias categoria) {
//		super();
//		this.nome = nome;
//		this.foto = foto;
//		this.sinopse = sinopse;
//		this.dataLancamento = localDate;
//		this.categoria = categoria;
//	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O atributo NOME não deve ser NULL")
	@Size(min = 4, max = 255, message = "O atributo NOME deve ter 4 caractere ou no máximo 255")
	private String nome;
	
	private String foto;
	
	@Size(min = 100, max = 255, message = "O atributo SINOPSE pode ter no minimo 100 e no máximo 255 caractere")
	private String sinopse;
	
	@NotNull(message = "O atributo PRECO não deve ser NULL")
	private double preco;
	
	//comentado o get o set, atributo e contrutor do objeto
//	@DateTimeFormat
//	@NotNull
//	@Past
//	private Timestamp dataLancamento;
	
	@UpdateTimestamp
	private LocalDateTime dataEntradaEstoque;
	
	@ManyToOne
	@JsonIgnoreProperties("produtos")
	private Categorias categoria;

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

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

//	public Timestamp getDataLancamento() {
//		return dataLancamento;
//	}
//
//	public void setDataLancamento(Timestamp dataLancamento) {
//		this.dataLancamento = dataLancamento;
//	}


	public Categorias getCategoria() {
		return categoria;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public LocalDateTime getDataEntradaEstoque() {
		return dataEntradaEstoque;
	}

	public void setDataEntradaEstoque(LocalDateTime dataEntradaEstoque) {
		this.dataEntradaEstoque = dataEntradaEstoque;
	}

	public void setCategoria(Categorias categoria) {
		this.categoria = categoria;
	}
	
}
