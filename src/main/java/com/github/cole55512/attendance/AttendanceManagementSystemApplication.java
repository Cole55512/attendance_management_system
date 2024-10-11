package com.github.cole55512.attendance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.github.cole55512.attendance.repository")
public class AttendanceManagementSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(AttendanceManagementSystemApplication.class, args);
	}
}
