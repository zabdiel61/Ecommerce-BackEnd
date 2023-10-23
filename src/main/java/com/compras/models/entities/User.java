package com.compras.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "COM_USER", schema = "COM")
@SequenceGenerator(name = "COM_USER_SEQ", sequenceName = "COM_USER_SEQ", allocationSize = 1)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COM_USER_SEQ")
	@Column(name = "USER_ID")
	private Long id;

	@NotBlank
	@Column(name = "USERNAME", unique = true)
	private String username;

	@NotEmpty
	@Column(name = "PASSWORD")
	private String password;

	@NotEmpty
	@Email
	@Column(name = "EMAIL", unique = true)
	private String email;

}
