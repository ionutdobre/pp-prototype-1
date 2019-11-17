package com.example.demo.repositories;

import com.example.demo.persistence.test.TestForm;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TestFormRepository extends BaseJpaRepository<TestForm, Long> {

}
