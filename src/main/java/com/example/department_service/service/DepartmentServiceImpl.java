package com.example.department_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.department_service.dao.DepartmentRepoistory;
import com.example.department_service.dao.entity.DepartmentEntity;
import com.example.department_service.model.DepartmentPojo;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    DepartmentRepoistory deptRepo;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepoistory deptRepo) {
        this.deptRepo = deptRepo;
    }

    @Override
    public List<DepartmentPojo> getAllDepartments() {
        List<DepartmentEntity> allDeptEntity = deptRepo.findAll();
        List<DepartmentPojo> allDeptPojo = new ArrayList<>();
        allDeptEntity.stream().forEach((eachDeptEntity) -> {
            DepartmentPojo deptPojo = new DepartmentPojo();
            BeanUtils.copyProperties(eachDeptEntity, deptPojo);
            allDeptPojo.add(deptPojo);
        });
        return allDeptPojo;
    }

    @Override
    public DepartmentPojo getADepartment(long deptId) {
        Optional<DepartmentEntity> fetchedDeptEntity = deptRepo.findById(deptId);
        DepartmentPojo deptPojo = null;
        if (fetchedDeptEntity.isPresent()) {
            deptPojo = new DepartmentPojo();
            BeanUtils.copyProperties(fetchedDeptEntity.get(), deptPojo);
        }
        return deptPojo;
    }

    @Override
    public DepartmentPojo addDepartment(DepartmentPojo newDeptPojo) {
        DepartmentEntity deptEntity = new DepartmentEntity();
        BeanUtils.copyProperties(newDeptPojo, deptEntity);
        deptRepo.saveAndFlush(deptEntity);
        return newDeptPojo;
    }

    @Override
    public DepartmentPojo updateDepartment(DepartmentPojo editDeptPojo) {
        DepartmentEntity deptEntity = new DepartmentEntity();
        BeanUtils.copyProperties(editDeptPojo, deptEntity);
        deptRepo.saveAndFlush(deptEntity);
        return editDeptPojo;
    }

    @Override
    public void deleteDepartment(long deptId) {
        deptRepo.deleteById(deptId);
    }
    
}
