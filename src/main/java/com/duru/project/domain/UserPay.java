package com.duru.project.domain;

import java.sql.Date;

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
@Table(name = "userpay")
public class UserPay {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "paySeq")
	    private int paySeq;

	    @Column(name = "payPrice")
	    private int payPrice;

	    @Column(name = "payName")
	    private String payName;
	    
	    @Column(name = "payPhonenumber")
	    private String payPhonenumber;
	    
	    @Column(name = "payEmail")
	    private String payEmail;

	    @Column(name = "payAddress")
	    private String payAddress;

	    @Column(name = "payAddressDetail")
	    private String payAddressDetail;

	    @Column(name = "payZipcode")
	    private String payZipcode;


	    @Column(name = "payRequest")
	    private String payRequest;

	    @CreationTimestamp
	    private Date payDate;
	    
	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "userSeq")
	    private User user;

}
