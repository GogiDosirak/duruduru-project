package com.duru.project.domain;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table
public class EventBoard {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eventSeq")
    private int eventSeq;


    @ManyToOne
    @JoinColumn(name = "userSeq")
    private User user;
    
    @Column(name = "eventTitle", length = 200)
    private String eventTitle;

    @Lob
    @Column(name = "eventContent", columnDefinition = "LONGTEXT" )
    private String eventContent;

    @Column(name = "eventDate")
    @CreationTimestamp
    private Date eventDate;

    @Column(name = "eventCnt")
    private int eventCnt;



}
