package com.duru.project.domain;

import java.sql.Date;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "userorder")
public class UserOrder {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "orderSeq")
	    private int orderSeq;

	    @Column(name = "orderPrice")
	    private int orderPrice;

	    @Column(name = "orderName")
	    private String orderName;
	    
	    @Column(name = "orderPhonenumber")
	    private String orderPhonenumber;
	    
	    @Column(name = "orderEmail")
	    private String orderEmail;

	    @Column(name = "orderAddress")
	    private String orderAddress;

	    @Column(name = "orderAddressDetail")
	    private String orderAddressDetail;

	    @Column(name = "orderZipcode")
	    private String orderZipcode;


	    @Column(name = "orderRequest")
	    private String orderRequest;

	    @CreationTimestamp
	    private Timestamp orderDate;
	    
	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "userSeq")
	    private User user;

}
