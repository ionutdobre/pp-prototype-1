package com.example.demo.repositories.categories;

import com.example.demo.persistence.categories.Department;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface DepartmentRepository extends CategoryRepository<Department> {

}
