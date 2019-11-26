package com.example.demo.policy.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author idobre
 * @since 24/11/2019
 */
public class GeneratedPolicy {
    private final List<Map<String, Object>> policyContent = new ArrayList<>();

    /**
     * the timestamp of the moment this policy started to be generated (nanoseconds)
     */
    private final long generationStart = System.nanoTime();

    /**
     * the timestamp of the moment this policy ended to be generated (nanoseconds)
     */
    private long generationEnd;

    private String user;

    private SortedSet<String> policyWarnings = new TreeSet<>();

    public GeneratedPolicy() {
        // ....
    }

    public void endGeneration() {
        generationEnd = System.nanoTime();
    }

    public double deltaTime() {
        if (generationEnd == 0) {
            this.endGeneration();
        }

        final double delta = (generationEnd - generationStart) / 1000000000.0;

        return delta;
    }

    public List<Map<String, Object>> getPolicyContent() {
        return policyContent;
    }

    public long getGenerationStart() {
        return generationStart;
    }

    public long getGenerationEnd() {
        return generationEnd;
    }

    public String getUser() {
        return user;
    }

    public SortedSet<String> getPolicyWarnings() {
        return policyWarnings;
    }
}
