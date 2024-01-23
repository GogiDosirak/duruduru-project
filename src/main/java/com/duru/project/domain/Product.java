package com.duru.project.domain;

import java.sql.Date;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "product")
public class Product {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int product_seq;

	    @Column(name = "product_name", length = 40, nullable = false)
	    private String product_name;

	    @Lob
	    @Column(columnDefinition = "LONGTEXT")
	    private String product_content;

	    @Column(name = "product_price", nullable = false)
	    private int product_price;

	    @Column(name = "product_stock", nullable = false)
	    private int product_stock;

	    @CreationTimestamp
	    private Timestamp product_createdate;

}
