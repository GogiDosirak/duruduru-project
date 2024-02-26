package com.duru.project.domain;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

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
import jakarta.persistence.PrePersist;
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
    @Column(name = "wachboSeq")
    private int wachboSeq;

    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userSeq")
	private User user;

    @Column(name = "wachboTitle", length = 100)
    private String wachboTitle;

    @Lob
    @Column(name = "wachboContent", columnDefinition = "LONGTEXT")
    private String wachboContent;
    
    @CreationTimestamp
    @Column(name = "wachboDate")
    private Date wachboDate;

    
    @Column(name = "wachboCheck")
    private int wachboCheck;
    
    @PrePersist
    public void prePersist() {
        if (this.wachboCheck == 0) {
            this.wachboCheck = 1;
        }
    }
    
    @Column
    private String wachFilename;
    
    @Column
    private String wachFilepath;

    @Column(name = "wachboCnt")
    private int wachboCnt;
    
    @Column(name = "lastPointDate")
    private LocalDate lastPointDate;

    
}