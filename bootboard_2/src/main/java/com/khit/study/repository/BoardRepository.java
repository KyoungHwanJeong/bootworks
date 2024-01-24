package com.khit.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khit.study.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{

	
}
