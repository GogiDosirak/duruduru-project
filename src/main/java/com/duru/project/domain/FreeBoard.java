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
    @Column(name = "frbo_seq")
    private int frbo_seq;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_seq")
	private User user;

	
    @Column(name = "frbo_title", length = 200)
    private String frbo_title;

    @Lob
    @Column(name = "frbo_content", columnDefinition = "LONGTEXT")
    private String frbo_content;

    @Column(name = "frbo_date")
    @CreationTimestamp
    private Timestamp frbo_date;

    @Column(name = "frbo_cnt")
    private int frbo_cnt;
	

}
