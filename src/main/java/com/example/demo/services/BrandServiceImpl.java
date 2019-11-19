package com.example.demo.services;

import com.example.demo.persistence.categories.Brand;
import com.example.demo.repositories.BaseJpaRepository;
import com.example.demo.repositories.TextSearchableRepository;
import com.example.demo.repositories.categories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class BrandServiceImpl extends BaseJpaServiceImpl<Brand> implements BrandService {
    @Autowired
    private BrandRepository repository;
    
    @Override
    protected BaseJpaRepository<Brand, Long> repository() {
        return repository;
    }

    @Override
    public TextSearchableRepository<Brand, Long> textRepository() {
        return repository;
    }

    @Override
    public Brand newInstance() {
        return new Brand();
    }
}
