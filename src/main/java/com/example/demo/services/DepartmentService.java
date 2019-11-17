package com.example.demo.services;

import com.example.demo.persistence.categories.Department;

public interface DepartmentService extends BaseJpaService<Department>, TextSearchableService<Department> {

}
