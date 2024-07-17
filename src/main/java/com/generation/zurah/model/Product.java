package com.generation.zurah.model;

import java.math.BigDecimal;
import java.time.LocalDate;
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

	@NotBlank(message = "The atribute name is mandatory")
	@Size(min = 3, max = 255, message = "The name must have at least 3 characters to 255 characters")
	private String name;

	@NotNull(message = "The expire atribute is mandatory and must follow this format: yyyy-MM-dd")
	private LocalDate expire;

	@NotNull(message = "The price atribute is mandatory")
	@Column(precision = 6, scale = 2)
	private BigDecimal price;

	@NotNull(message = "The quantity atribute is mandatory")
	private int quantity;

	@Size(max = 1000, message = "The photo atribute has a limit of 1000 characters")
	private String photo;

	@Size(max = 255, message = "The description atribute has a limit of 255 characters")
	private String description;

	@Size(max = 255, message = "The region atribute has a limit of 255 characters")
	private String region;

	@ManyToOne
	@JsonIgnoreProperties("product")
	private Category category;

	@ManyToOne
	@JsonIgnoreProperties("product")
	private User user;

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

	public LocalDate getExpire() {
		return expire;
	}

	public void setExpire(LocalDate expire) {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
