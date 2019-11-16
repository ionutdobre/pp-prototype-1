package com.example.demo.services;

import com.example.demo.persistence.Test;
import com.example.demo.repositories.TestRepository;
import com.google.code.ssm.api.InvalidateAssignCache;
import com.google.code.ssm.api.ReadThroughAssignCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author idobre
 * @since 16/11/2019
 */
@Service
public class TestService {
    @Autowired
    private TestRepository testRepository;

    @ReadThroughAssignCache(namespace = "TestService", assignedKey = "all")
    public List<Test> findAll() {
        return testRepository.findAll();
    }

    @InvalidateAssignCache(namespace = "TestService", assignedKey = "all")
    public Boolean clearCache() {
        return true;
    }
}
