package com.example.department_service.service;

import java.util.List;

import com.example.department_service.model.DepartmentPojo;

public interface DepartmentService {
    List<DepartmentPojo> getAllDepartments();
    DepartmentPojo getADepartment(long deptId);
    DepartmentPojo addDepartment(DepartmentPojo newDeptPojo);
    DepartmentPojo updateDepartment(DepartmentPojo editDeptPojo);
    void deleteDepartment(long deptId);
}
