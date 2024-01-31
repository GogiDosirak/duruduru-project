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
@Table(name = "FindPetBoard")
public class FindPetBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fPetSeq")
    private int fPetSeq;

    @ManyToOne(fetch = FetchType.EAGER)
  	@JoinColumn(name = "userSeq")
  	private User user;

    @Column(name = "fPetTitle", length = 50)
    private String fPetTitle;

    @Lob
    @Column(name = "fPetContent")
    private String fPetContent;

    @Column(name = "fPetDate")
    @CreationTimestamp
    private Date fPetDate;
    
    @Column
    private String fPetFilename;
    
    @Column
    private String fPetFilepath;

    @Column(name = "fPetCnt")
    private Integer fPetCnt;

  
}
