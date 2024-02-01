package com.duru.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.duru.project.domain.LikeBoard;
import com.duru.project.domain.SNSBoard;
import com.duru.project.domain.SNSBoardComment;
import com.duru.project.persistence.SNSBoardRepository;
import com.duru.project.persistence.SNSCommentRepository;
import com.duru.project.persistence.likeRepository;

@Service
public class SNSBoardService {

	@Autowired
	SNSBoardRepository snsBoardRepository;

	@Autowired
	SNSCommentRepository snsCommentRepository;

	@Autowired
	likeRepository likeRepository;

	@Transactional
	public void insertSNS(SNSBoard snsBoard) {
		snsBoardRepository.save(snsBoard);
	}

	@Transactional
	public void insertSNS(SNSBoard snsBoard, MultipartFile file) throws Exception {
		snsBoardRepository.save(snsBoard);
	}

	@Transactional
	public List<SNSBoard> SNSList() {
		return snsBoardRepository.findAll();
	}

	@Transactional
	public void deleteSNS(int snsboSeq) {
		snsBoardRepository.deleteById(snsboSeq);
	}

	@Transactional(readOnly = true)
	public SNSBoard getSNSBaord(int snsboSeq) {

		SNSBoard findSns = snsBoardRepository.findById(snsboSeq).orElseGet(() -> {
			return new SNSBoard();
		});
		return findSns;

	}

	// 댓글

	@Transactional
	public void insertSnsComment(SNSBoardComment snsBoardComment) {
		snsCommentRepository.save(snsBoardComment);
	}

	@Transactional(readOnly = true)
	public List<SNSBoardComment> getSnsCommentList() {
		return snsCommentRepository.findAll();
	}

	@Transactional
	public void deleteSnsComment(int snsboCoSeq) {
		snsCommentRepository.deleteById(snsboCoSeq);
	}

	@Transactional
	public void deleteAllSnsComment(int snsboSeq) {
		snsCommentRepository.deleteBySnsBoard_snsboSeq(snsboSeq);
	}

	// 좋아요

	// 좋아요 개수
	@Transactional(readOnly = true)
	public List<LikeBoard> likeSns() {
		return likeRepository.findAll();

	}

	// 좋아요 하기
	@Transactional
	public void insertLike(LikeBoard likeBoard) {
		likeRepository.save(likeBoard);
	}

	// 좋아요 취소
	@Transactional
	public void deleteLike(int liboSeq) {
		likeRepository.deleteById(liboSeq);
	}

	//좋아요순 정렬
	@Transactional
	public List<SNSBoard> getTopSNSBoardsByLikes() {
		return snsBoardRepository.findAllByOrderByLikeCntDesc();
	}

}
