package com.generation.zurah.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
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
@Table(name = "tb_product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O atributo nome é obrigatório")
	@Size(min = 3, max = 255, message = "O nome precisa ter no mínimo 3 caracteres e no máximo 255 caracteres")
	private String name;

	@NotBlank(message = "O atributo é obrigatório e deve seguir a ordem: mês-dia-ano")
	private String expire;

	@NotNull(message = "O atributo preço obrigatório")
	@Column(precision = 6, scale = 2)
	private BigDecimal price;

	@NotNull(message = "O atributo quantidade é obrigatório")
	private int quantity;

	@Size(max = 1000, message = "O link da foto precisa ter no máximo 1000 caracteres")
	private String photo;

	@Size(max = 255, message = "O atributo descrição precisa ter no máximo 255 caracteres")
	private String description;

	@Size(max = 255, message = "O atributo região precisa ter no máximo 255 caracteres")
	private String region;

	@ManyToOne
	@JsonIgnoreProperties("product")
	private Category category;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExpire() {
		return expire;
	}

	public void setExpire(String expire) {
		this.expire = expire;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	
}
