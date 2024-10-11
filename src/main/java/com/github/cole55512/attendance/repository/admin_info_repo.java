package com.github.cole55512.attendance.repository;

import com.github.cole55512.attendance.entity.admin_info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface admin_info_repo extends JpaRepository<admin_info, Integer> {
    // Custom MySQL Queries Section
    @Query(value = "SELECT COUNT(*) " +
            "FROM admin_info a " +
            "WHERE a.admin_email = ?1", nativeQuery = true)
    long adminExists(String admin_email);
    @Query(value = "SELECT * " +
            "FROM admin_info " +
            "WHERE admin_email = ?1", nativeQuery = true)
    admin_info findAdmin(String admin_email);
}
