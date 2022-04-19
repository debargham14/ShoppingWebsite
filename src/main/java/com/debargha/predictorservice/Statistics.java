package com.debargha.predictorservice;

import java.io.Serializable;

public class Statistics implements Serializable {
    private static final long serialVersionUID = 1000L;
    private double mean;
    private double variance;
    private int n;

    public double getMean() {
        return mean;
    }

    public double getStandardDeviation()
    {
        if(n == 1) return 0;
        return Math.sqrt(variance);
    }

    public synchronized void update(double x)
    {
        n += 1;
        double newMean = mean + (x - mean) / n;
        variance = variance + (x - mean) * (x - newMean) / n;
        mean = newMean;
    }

    public int getCount()
    {
        return n;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "mean=" + mean +
                ", variance=" + variance +
                ", n=" + n +
                '}';
    }
}
