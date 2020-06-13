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

    /**
     * This might be useless.
     *
     * Prints fitness of each member in the population
     */
    public void calcFitness() {
        for (int i = 0; i < this.population.length; i++)
            System.out.println(String.format("Phrase: %s%nFitness: %f%n", this.population[i].getPhrase(), this.population[i].fitness(target)));
    }

    public void naturalSelection() {
        matingPool.clear();

        // TODO: make pool of population based on fitness scores

        DNA child = this.population[0].crossOver(this.population[1]);
        child.mutate(this.mutationRate);

        System.out.println("---");
        System.out.println(this.population[0].getPhrase());
        System.out.println(this.population[1].getPhrase());
        System.out.println(child.getPhrase());
        System.out.println();
    }

    public void generate() {
        // TODO: generate a new population by randomly selecting from the mating pool
    }

    public boolean evaluate() {
        for (int i = 0; i < this.population.length; i++)
            if (this.population[i].fitness(this.target) == 1) this.finished = true;

        return finished;
    }

    public String getBestPhrase() {
        DNA best = this.population[0];

        for (int i = 0; i < this.population.length; i++)
            if (best.fitness(this.target) < this.population[i].fitness(this.target))
                best = this.population[i];

        return best.getPhrase();
    }

    public float getAverageFitness() {
        float total = 0f;

        for (int i = 0; i < this.population.length; i++)
                total += this.population[i].fitness(this.target);

        return total / this.population.length;
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
