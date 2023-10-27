package com.compras.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "COM_ROLE", schema = "COM")
@SequenceGenerator(name = "COM_ROLE_SEQ", sequenceName = "COM_ROLE_SEQ", allocationSize = 1)
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COM_ROLE_SEQ")
	@Column(name = "ROLE_ID")
	private Long id;

	@Column(name = "NAME", unique = true)
	private String name;

}
