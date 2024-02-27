package com.duru.project.config;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ChatJoinCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void dropConstraints() {
        // 제약조건 삭제
        entityManager.createNativeQuery("ALTER TABLE chatuser DROP FOREIGN KEY FKtqhkpvavwuwgv2j5ssypm77uo;").executeUpdate();

    }
    
    @Transactional
    public void addConstraints() {
       
        // 제약조건 재설정
        entityManager.createNativeQuery("ALTER TABLE chatuser ADD CONSTRAINT FKtqhkpvavwuwgv2j5ssypm77uo FOREIGN KEY (crSeq, userSeq) REFERENCES CHATJOIN(crSeq, userSeq);").executeUpdate();
        System.out.println("외래 키 제약 조건이 성공적으로 추가되었습니다.");
        System.out.println("----------------------------------------------");
    }
}