
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
public class WalkBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "waboSeq")
    private int waboSeq;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userSeq")
    private User user;

    @Column(name = "waboTitle", length = 200)
    private String waboTitle;

    @Lob
    @Column(name = "waboContent", columnDefinition = "LONGTEXT")
    private String waboContent;

    @CreationTimestamp
    @Column(name = "waboDate")
    private Date waboDate;

    @Column(name = "waboCnt")
    private int waboCnt;

}