package com.github.cole55512.attendance.repository;

import com.github.cole55512.attendance.entity.quiz_questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface quiz_questions_repo extends JpaRepository<quiz_questions, Integer> {
    @Query(value = "SELECT * " +
            "FROM quiz_questions " +
            "WHERE quiz_id = ?1", nativeQuery = true)
    List<quiz_questions> findQuizQuestions(int quiz_id);
}
