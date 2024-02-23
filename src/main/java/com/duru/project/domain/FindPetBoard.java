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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class FindPetBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fpSeq")
    private int fpSeq;

    @ManyToOne(fetch = FetchType.EAGER)
  	@JoinColumn(name = "userSeq")
  	private User user;

    @Column(name = "fpTitle", length = 50)
    private String fpTitle;

    @Lob
    @Column(name = "fpContent")
    private String fpContent;

    @Column(name = "fpDate")
    @CreationTimestamp
    private Date fpDate;
    
    @Column
    private String fpFilename;
    
    @Column
    private String fpFilepath;

    @Column(name = "fPetCnt")
    private Integer fpCnt;

  
}
