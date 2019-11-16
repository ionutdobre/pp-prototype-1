package com.example.demo.controllers;

import com.example.demo.persistence.Test;
import com.example.demo.repositories.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author idobre
 * @since 16/11/2019
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestRepository testRepository;

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public List<Test> showAllTasks() {
        final List<Test> tests = testRepository.findAll();

        return tests;
    }
}
