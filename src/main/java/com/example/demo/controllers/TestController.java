package com.example.demo.controllers;

import com.example.demo.pages.HomePage;
import com.example.demo.persistence.test.Test;
import com.example.demo.services.TestService;
import com.google.common.util.concurrent.AtomicDouble;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    @RequestMapping(value = "calcParallel", method = RequestMethod.GET)
    public double longCalcParallel() {
        final long start = System.nanoTime();

        final Random random = new Random();
        final List<Integer> list = IntStream.rangeClosed(1, 100000).boxed().collect(Collectors.toList());

        list.parallelStream().forEach(i -> {
            final long time = new Date().getTime();
            final Float number = random.nextFloat();

            double test = (time * number) /
                    Math.pow(
                            Math.sqrt(
                                    Math.pow(i, number) + Math.pow(2, number + 1)),
                    2);

            test += time /
                    Math.pow(
                            Math.sqrt(
                                    Math.pow(number, i) + Math.pow(2, number + 1)),
                            2);

            test += Math.sqrt(time / Math.pow(i, 10));
        });

        final long end = System.nanoTime();
        final double delta = (end - start) / 1000000000.0;

        logger.error(">>> delta: " + delta);

        return delta;
    }


    @RequestMapping(value = "calc", method = RequestMethod.GET)
    public double longCalc() {
        final long start = System.nanoTime();

        final Random random = new Random();
        final List<Integer> list = IntStream.rangeClosed(1, 100000).boxed().collect(Collectors.toList());

        list.stream().forEach(i -> {
            final long time = new Date().getTime();
            final Float number = random.nextFloat();

            double test = (time * number) /
                    Math.pow(
                            Math.sqrt(
                                    Math.pow(i, number) + Math.pow(2, number + 1)),
                            2);

            test += time /
                    Math.pow(
                            Math.sqrt(
                                    Math.pow(number, i) + Math.pow(2, number + 1)),
                            2);

            test += Math.sqrt(time / Math.pow(i, 10));
        });

        final long end = System.nanoTime();
        final double delta = (end - start) / 1000000000.0;

        logger.error(">>> delta: " + delta);

        return delta;
    }
}
