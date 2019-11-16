package com.example.demo.controllers;

import com.example.demo.persistence.Test;
import com.example.demo.services.TestService;
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
    private TestService testService;

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public List<Test> showAllTests() {
        final List<Test> tests = testService.findAll();

        return tests;
    }

    @RequestMapping(value = "clear-cache", method = RequestMethod.GET)
    public Boolean clearCache() {
        return testService.clearCache();
    }
}
