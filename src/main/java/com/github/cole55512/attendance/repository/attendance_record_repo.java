package com.github.cole55512.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.github.cole55512.attendance.entity.attendance_record;
import com.github.cole55512.attendance.entity.attendance_record_key;

@Repository
public interface attendance_record_repo extends JpaRepository<attendance_record, attendance_record_key> {

}
