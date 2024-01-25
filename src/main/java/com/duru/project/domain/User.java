package com.duru.project.domain;

import java.sql.Timestamp;
import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userSeq")
    private int userSeq;

    @Column(name = "userid", unique = true, nullable = false, length = 40)
    private String userid;

    @Column(name = "password", length = 40)
    private String password;

    @Column(name = "nickname", length = 20)
    private String nickname;

    @Column(name = "email", length = 60)
    private String email;

    @Column(name = "phonenumber", length = 20)
    private String phonenumber;

    @Column(name = "address", nullable = false, length = 200)
    private String address;

    @Column(name = "address_detail", nullable = false, length = 200)
    private String addressDetail;

    @Column(name = "zipcode", nullable = false, length = 20)
    private String zipcode;

    @Column(name = "localcode", length = 20)
    private String localcode;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private RoleType role;

    @Column(name = "createDate")
    @CreationTimestamp
    private Timestamp createDate;

    @Column(name = "point")
    private int point;
    
    @Column(name = "last_point_date")
    private LocalDate lastPointDate;

}