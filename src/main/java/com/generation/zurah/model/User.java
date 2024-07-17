package com.generation.zurah.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "tb_users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "The atribute name is mandatory")
	@Size(min = 3, max = 255, message = "The name must have at least 3 characters to 255 characters")
	private String name;

	@Email(message = "Email should be valid")
	@NotEmpty(message = "Email cannot be empty")
	private String email;

	@NotBlank(message = "The atribute password is mandatory")
	@Size(min = 8, message = "The atribute password must have at least 8 characters")
	private String password;

	@NotNull
	private int is_seller;

	@Size(max = 1000, message = "The photo atribute has a limit of 1000 characters")
	private String photo;

	@Size(max = 255, message = "The address atribute has a limit of 255 characters")
	private String address;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("user")
	private List<Product> product;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getIs_seller() {
		return is_seller;
	}

	public void setIs_seller(int is_seller) {
		this.is_seller = is_seller;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getAddress() {
		return address;
	}

	public void setAdress(String adress) {
		this.address = adress;
	}

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}
}
