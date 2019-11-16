package com.example.demo.repositories;

import com.example.demo.persistence.Test;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author idobre
 * @since 16/11/2019
 */
@Transactional
public interface TestRepository extends BaseJpaRepository<Test, Long> {

}

