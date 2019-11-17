package com.example.demo.services;

import com.example.demo.persistence.categories.Department;
import com.example.demo.repositories.BaseJpaRepository;
import com.example.demo.repositories.TextSearchableRepository;
import com.example.demo.repositories.categories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DepartmentServiceImpl extends BaseJpaServiceImpl<Department> implements DepartmentService {
    @Autowired
    private DepartmentRepository repository;
    
    @Override
    protected BaseJpaRepository<Department, Long> repository() {
        return repository;
    }

    @Override
    public TextSearchableRepository<Department, Long> textRepository() {
        return repository;
    }

    @Override
    public Department newInstance() {
        return new Department();
    }
}
