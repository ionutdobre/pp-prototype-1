package com.example.demo.controllers;

import com.example.demo.pages.HomePage;
import com.example.demo.persistence.test.Test;
import com.example.demo.services.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author idobre
 * @since 16/11/2019
 */
@RestController
@RequestMapping("/test")
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(HomePage.class);

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

    @RequestMapping(value = "calculate", method = RequestMethod.GET)
    public Float calculate() {
        final long time = new Date().getTime();
        final Random random = new Random();
        final Float number = random.nextFloat();

        logger.info("number: " + time * number);

        return time * number;
    }
}
