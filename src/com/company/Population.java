package com.company;

import java.util.ArrayList;

public class Population {
    private float mutationRate;

    private DNA[] population;
    private ArrayList<DNA> matingPool;

    private String target;
    private int generations;
    private boolean finished;
    private int perfectScore;

    public Population(String target, float mutationRate, int populationMax) {
        this.target = target;
        this.mutationRate = mutationRate;
        this.matingPool = new ArrayList<DNA>();

        this.generations = 0;
        this.finished = false;
        this.perfectScore = 1;

        // Generate population
        this.population = new DNA[populationMax];

        for (int i = 0; i < populationMax; i++)
            this.population[i] = new DNA(target.length());
    }

    public void calcFitness() {
        // TODO: loop through each member of population and calculate their fitness
    }

    public void naturalSelection() {
        matingPool.clear();

        // TODO: make pool of population based on fitness scores
    }

    public void generate() {
        // TODO: generate a new population by randomly selecting from the mating pool
    }

    public String getBestPhrase() {
        // TODO: return best phrase based on fitness score
        return "";
    }

    public float getAverageFitness() {
        // TODO: return average fitness of population
        return 0;
    }

    public String getPhrases(int amount) {
        // TODO: return all phrases, with a maximum amount
        return "";
    }

    public boolean isFinished() {
        return finished;
    }

    public int getGenerations() {
        return generations;
    }
}
