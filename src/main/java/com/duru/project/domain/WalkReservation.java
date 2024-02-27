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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class WalkReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wareSeq")
    private int wareSeq;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userSeq")
    private User user;

    @Column(name = "wareTitle", length = 200)
    private String wareTitle;

    @Lob
    @Column(name = "wareContent", columnDefinition = "LONGTEXT")
    private String wareContent;

    @CreationTimestamp
    @Column(name = "wareDate")
    private Date wareDate;

    @Column(name = "wareCnt")
    private int wareCnt;

}