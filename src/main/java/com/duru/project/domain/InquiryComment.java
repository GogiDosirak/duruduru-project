package com.duru.project.domain;

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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "inquiryComment")
public class InquiryComment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int inquiryCoSeq;
	
	@Column
	private String inquiryCoContent;
	
	@CreationTimestamp
	private Timestamp inquiryCoDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userSeq")
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "inquirySeq")
	private Inquiry inquiry;
	

}
