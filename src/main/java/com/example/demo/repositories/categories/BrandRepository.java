package com.example.demo.repositories.categories;

import com.example.demo.persistence.categories.Brand;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BrandRepository extends CategoryRepository<Brand> {

}
