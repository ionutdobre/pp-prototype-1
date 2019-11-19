package com.example.demo.services;

import com.example.demo.persistence.categories.Brand;

public interface BrandService extends BaseJpaService<Brand>, TextSearchableService<Brand> {

}
