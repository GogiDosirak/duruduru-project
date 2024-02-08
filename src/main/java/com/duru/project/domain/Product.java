
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
	    private int productSeq;

	    @Column(name = "productName", length = 40)
	    private String productName;

	    @Lob
	    @Column(columnDefinition = "LONGTEXT")
	    private String productContent;

	    @Column(name = "productPrice")
	    private int productPrice;

	    @Column(name = "productStock")
	    private int productStock;
	    
	    @Column
	    private String productFilename;
	    
	    @Column
	    private String productFilepath;

	    @CreationTimestamp

	    private Date productCreateDate;


}