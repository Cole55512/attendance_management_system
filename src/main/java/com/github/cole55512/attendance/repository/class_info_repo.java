package com.github.cole55512.attendance.repository;

import com.github.cole55512.attendance.entity.class_info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface class_info_repo extends JpaRepository<class_info, Integer> {

}
