package com.example.department_service.model;

import java.util.List;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DepartmentPojo {
    private long deptId;
    private String deptName;
    private List<EmployeePojo> allEmployees;
}
