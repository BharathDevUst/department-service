package com.example.department_service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.department_service.dao.entity.DepartmentEntity;

@Repository
public interface DepartmentRepoistory extends JpaRepository<DepartmentEntity, Long>{} 
