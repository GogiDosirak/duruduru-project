package com.duru.project.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CHATJOIN")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatJoin {
	
    @EmbeddedId
    private ChatJoinId id;
    
    @Column
    private String cjStatus;

    @ManyToOne
    @MapsId("crSeq")
    @JoinColumn(name = "crSeq") // 매핑할 컬럼 지정
    private ChatRoom chatRoom;

    @ManyToOne
    @MapsId("userSeq")
    @JoinColumn(name = "userSeq") // 매핑할 컬럼 지정
    private User user;

	    
	    

}
