package com.duru.project.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duru.project.domain.Notice;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Integer> {

}
