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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FreeBoard")
public class FreeBoard {
	
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "frboSeq")
    private int frboSeq;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userSeq")
	private User user;

	
    @Column(name = "frboTitle", length = 200)
    private String frboTitle;

    @Lob
    @Column(name = "frboContent", columnDefinition = "LONGTEXT")
    private String frboContent;

    @Column(name = "frboDate")
    @CreationTimestamp
    private Date frboDate;

    @Column(name = "frboCnt")
    private int frboCnt;
    
  

}
