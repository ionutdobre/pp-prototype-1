package com.example.demo.services;

import com.example.demo.persistence.test.TestForm;
import com.example.demo.repositories.BaseJpaRepository;
import com.example.demo.repositories.TestFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author idobre
 */
@Service
@Transactional(readOnly = true)
public class TestFormServiceImpl extends BaseJpaServiceImpl<TestForm> implements TestFormService {
    @Autowired
    private TestFormRepository testFormRepository;

    @Override
    protected BaseJpaRepository<TestForm, Long> repository() {
        return testFormRepository;
    }

    @Override
    public TestForm newInstance() {
        return new TestForm();
    }
}
