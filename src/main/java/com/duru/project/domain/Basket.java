package com.duru.project.domain;

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
@Table(name = "basket")
public class Basket {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "basketSeq")
    private int basketSeq;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userSeq", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productSeq", nullable = false)
    private Product product;
    
    @Column(name = "basketProductAmount")
    private int basketProductAmount;

    @Column(name = "basketPrice")
    private int basketPrice;


}