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
@Table
public class WalkReservationComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wareCoSeq")
    private int wareCoSeq;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userSeq")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "wareSeq")
    private WalkReservation walkReservation;

    @Column(name = "wareCoContent", length = 400)
    private String wareCoContent;

    @CreationTimestamp
    @Column(name = "wareCoDate")
    private Date wareCoDate;

}