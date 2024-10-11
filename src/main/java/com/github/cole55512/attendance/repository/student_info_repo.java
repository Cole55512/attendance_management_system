package com.github.cole55512.attendance.repository;

import com.github.cole55512.attendance.entity.student_info;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface student_info_repo extends JpaRepository<student_info, String> {
    @Query(value = "SELECT * " +
            "FROM student_info " +
            "WHERE student_id = ?1", nativeQuery = true)
    List<student_info> findStudent(String student_id);
}
