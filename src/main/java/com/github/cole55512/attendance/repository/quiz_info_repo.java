package com.github.cole55512.attendance.repository;

import com.github.cole55512.attendance.entity.quiz_info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Repository
public interface quiz_info_repo extends JpaRepository<quiz_info, Integer> {
    // Custom MySQL Queries Section
    // Find available class quiz at specific date and time
    @Query(value = "SELECT * FROM quiz_info q " +
            "JOIN class_info c ON q.quiz_class_id = c.class_id " +
            "WHERE c.class_id = ?1 AND q.quiz_date = ?2 " +
            "AND ?3 BETWEEN c.quiz_start_time AND c.quiz_end_time", nativeQuery = true)
    Optional<quiz_info> findQuiz(int class_id, LocalDate quiz_date, LocalTime current_time);
}
