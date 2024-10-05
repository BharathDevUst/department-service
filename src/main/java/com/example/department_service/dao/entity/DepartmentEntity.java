package com.example.department_service.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name="department")
public class DepartmentEntity {
    @Id
    @Column(name="department_details")
    private long deptId;

    @Column(name="dept_name")
    private String deptName;

}
