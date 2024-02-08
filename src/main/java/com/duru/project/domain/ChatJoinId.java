package com.duru.project.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatJoinId implements Serializable{
	
	private static final long serialVersionUID = 1L;

	
	@Column(name = "crSeq2")
    private String crSeq;

	
    @Column(name = "userSeq2")
    private int userSeq;

}
