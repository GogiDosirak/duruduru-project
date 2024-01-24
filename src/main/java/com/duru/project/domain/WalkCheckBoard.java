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
@Table(name="WalkCheckBoard")
public class WalkCheckBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wachbo_seq")
    private int wachboSeq;

    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_seq")
	private User user;

    @Column(name = "wachbo_title", length = 100)
    private String wachbo_title;

    @Lob
    @Column(name = "wachbo_content", columnDefinition = "LONGTEXT")
    private String wachbo_content;
    
    @CreationTimestamp
    @Column(name = "wachbo_date")
    private Timestamp wachbo_date;

    @Column(name = "wachbo_cnt")
    private int wachbo_cnt;
    
}