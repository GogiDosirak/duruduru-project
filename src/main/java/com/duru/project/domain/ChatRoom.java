package com.duru.project.domain;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "CHATROOM")
public class ChatRoom {
	
	  	@Id
	  	@Column(name = "crSeq")
	    private String crSeq;

	    @Column(name = "crName", length = 50)
	    private String crName;
	    
	    @Column(name = "crCount")
	    private int crCount;
	    
	    @CreationTimestamp
	    @Column(name = "crDate")
	    private Date crDate;
	}


