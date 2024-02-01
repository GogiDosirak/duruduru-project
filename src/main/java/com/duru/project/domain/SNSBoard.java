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
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class SNSBoard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "snsboSeq")
	private int snsboSeq;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userSeq")
	private User user;

	@Lob
	@Column(name = "snsboContent")
	private String snsboContent;

	@CreationTimestamp
	@Column(name = "snsboDate")
	private Date snsboDate;

	@Column(name = "snsboCnt")
	private int snsboCnt;
	
	@Column(name = "likeCnt")
	private int likeCnt;
	
	@Column
	private String filename;

	@Column
	private String filepath;

}
