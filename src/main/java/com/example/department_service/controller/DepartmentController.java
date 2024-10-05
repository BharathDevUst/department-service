package com.example.department_service.controller;

import java.util.List;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.*;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import com.example.department_service.model.DepartmentPojo;
import com.example.department_service.model.EmployeePojo;
import com.example.department_service.service.DepartmentService;

@RestController
@RequestMapping("/api")
public class DepartmentController {
    DepartmentService deptService;
    public static final Logger LOG = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    public DepartmentController(DepartmentService deptService) {
        this.deptService = deptService;
    }

    @GetMapping("/departments")
    public List<DepartmentPojo> getAllDepartments() {
        LOG.info("in getAllDepartments");
        return deptService.getAllDepartments();
    }
    
    @GetMapping("/departments/{did}")
    @CircuitBreaker(name="ciremp", fallbackMethod = "empFallback")
    public DepartmentPojo getADepartment(@PathVariable("did") long deptId) {
        LOG.info("in getADepartments");
        DepartmentPojo deptPojo = deptService.getADepartment(deptId);
        RestClient restClient = RestClient.create();
        List<EmployeePojo> allEmps = restClient.get().uri("http://localhost:8082/api/employees/departments/"+ deptId).retrieve().body(List.class);
        deptPojo.setAllEmployees(allEmps);
        return deptPojo;
    }

    public DepartmentPojo empFallback() {
        return new DepartmentPojo(0, "fallback", null);
    }
    
    @PostMapping("/departments")
    public DepartmentPojo addDepartment(@RequestBody DepartmentPojo newPojo) {
        LOG.info("in addDepartments");
        return deptService.addDepartment(newPojo);
    }
    
    @PutMapping("/departments")
    public DepartmentPojo updateDepartment(@RequestBody DepartmentPojo editPojo) {
        LOG.info("in updateDepartments");
        return deptService.updateDepartment(editPojo);
    }
    
    @DeleteMapping("/departments/{did}")
    public void removeDepartment(@PathVariable("did") long deptId) {
        LOG.info("in deleteDepartments");
        deptService.deleteDepartment(deptId);
    }
}
