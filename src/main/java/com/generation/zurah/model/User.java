package com.generation.zurah.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O atributo nome é obrigatório")
	@Size(min = 3, max = 255, message = "O nome precisa ter no mínimo 3 caracteres e no máximo 255 caracteres")
	private String name;
	
	@Email(message = "Email should be valid")
	@NotEmpty(message = "Email cannot be empty")
	private String email;
	
	@NotBlank(message = "O atributo senha é obrigatório")
	@Size(min = 8, message = "A senha precisa ter no mínimo 8 caracteres")
	private String password; 
	
	@NotNull
	private int is_seller; 
	
	@Size(max = 1000, message = "O link da foto precisa ter no máximo 1000 caracteres")
	private String photo;
	
	@Size(max = 255, message = "O endereço precisa ter no máximo 255 caracteres")
	private String adress;

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

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	
}
