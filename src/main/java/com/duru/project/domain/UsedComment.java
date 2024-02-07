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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "UsedBoard_comment")
public class UsedComment {
	
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usboCoSeq")
    private int usboCoSeq;

	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userSeq")
	private User user;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usboSeq")
	private UsedBoard usedBoard;
    

    @Column(name = "usboCoContent", length = 400)
    private String usboCoContent;

    @Column(name = "usboCoDate")
    @CreationTimestamp
    private Date usboCoDate;


}
