package com.duru.project.domain;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ChatUser")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cuSeq")
    private int cuSeq;

    @Column(name = "cuMessage", length = 100)
    private String cuMessage;
    
    @Column(name = "cuSender", length = 100)
    private String cuSender;
    
    @CreationTimestamp
    @Column(name = "cuDate")
    private Date cuDate;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "cuCheck")
    private CheckType cuCheck;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "cuRole")
    private MessageType cuRole;
    
    @ManyToOne
    @JoinColumns({
    	 @JoinColumn(name = "crSeq"),
        @JoinColumn(name = "userSeq")
    })
    private ChatJoin chatJoin;
    
    // 생성자, 게터 및 세터 등 필요한 메서드들을 추가할 수 있습니다.
}