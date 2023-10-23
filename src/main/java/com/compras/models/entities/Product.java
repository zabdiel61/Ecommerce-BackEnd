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
@Table(name = "COM_PRODUCT", schema = "COM")
@SequenceGenerator(name = "COM_PRODUCT_SEQ", sequenceName = "COM_PRODUCT_SEQ", allocationSize = 1)
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COM_PRODUCT_SEQ")
	@Column(name = "PRODUCT_ID")
	private Long id;
	
	@Column(name = "NAME")
	private String namme;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "PRICE")
	private Long price;

}
