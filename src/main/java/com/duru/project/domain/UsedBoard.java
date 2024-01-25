package com.duru.project.domain;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "UsedBoard")
public class UsedBoard {

	
	    @Id
	    @Column(name = "usboSeq", nullable = false)
	    private int usboSeq;

	    
	    @ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "userSeq")
		private User user;
	    
	    

	    @Column(name = "usboTitle", length = 200)
	    private String usboTitle;

	    @Lob
	    @Column(name = "usboContent", columnDefinition = "LONGTEXT")
	    private String usboContent;

	    @Column(name = "usboDate")
	    private Date usboDate;

	    @Column(name = "usboCnt")
	    private int usboCnt;
	    
	    
	    @Column
	    private String UsedFilename;
	    
	    @Column
	    private String UsedFilepath;
	    
	    
	    
	    

}
