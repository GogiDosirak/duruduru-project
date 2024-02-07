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
import jakarta.persistence.Lob;
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
@Table(name = "notice")
public class Notice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int noticeSeq;
	
	@Column(length = 200)
	private String noticeTitle;
	
	@Lob
	@Column(columnDefinition = "LONGTEXT")
	private String noticeContent;
	
	@CreationTimestamp
	private Date noticeDate;
	
	@Column
	private int noticeCnt;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userSeq")
	private User user;

}
