
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
    @Column(name = "wabo_seq")
    private int wabo_seq;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_seq")
    private User user;

    @Column(name = "wabo_title", length = 200)
    private String wabo_title;

    @Lob
    @Column(name = "wabo_content", columnDefinition = "LONGTEXT")
    private String wabo_content;

    @CreationTimestamp
    @Column(name = "wabo_date")
    private Date wabo_date;

    @Column(name = "wabo_cnt")
    private int wabo_cnt;

}