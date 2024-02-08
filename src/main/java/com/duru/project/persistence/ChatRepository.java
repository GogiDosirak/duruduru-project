package com.duru.project.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duru.project.domain.ChatUser;

public interface ChatRepository extends JpaRepository<ChatUser, Integer>{

}
