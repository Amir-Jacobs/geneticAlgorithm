package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Population {
    private float mutationRate;

    private DNA[] population;
    private ArrayList<DNA> matingPool;

    private String target;
    private int generations;
    private boolean finished;

    /**
     * Constructor.
     *
     * @param target final sentence
     * @param mutationRate floating point representation of chance percentage
     * @param populationMax amount of members in population
     */
    public Population(String target, float mutationRate, int populationMax) {
        this.target = target;
        this.mutationRate = mutationRate;
        this.matingPool = new ArrayList<DNA>();

        this.generations = 1;
        this.finished = false;

        // Generate population
        this.population = new DNA[populationMax];

        for (int i = 0; i < populationMax; i++)
            this.population[i] = new DNA(target.length());
    }

    /**
     * Generates a mating pool based on fitness score of DNA
     */
    public void naturalSelection() {
        // TODO: change implementation, so that it's not needed to create huge array

        matingPool.clear();

        for (int i = 0; i < this.population.length; i++) {
            DNA member = this.population[i];

            for (int y = 0; y < (int) (member.fitness(this.target) * 100); y++)
                this.matingPool.add(member);
        }
    }

    /**
     * Generates a new population based on mating pool members.
     */
    public void generate() {
        this.generations++;

        Random random = new Random();

        for (int i = 0; i < this.population.length; i++) {

            if (this.matingPool.size() < 2) break;

            this.population[i] = this.matingPool.get(random.nextInt(this.matingPool.size())).crossOver(this.matingPool.get(random.nextInt(this.matingPool.size())));
        }

        for (DNA member : this.population)
            member.mutate(this.mutationRate);
    }

    /**
     * Checks if the evolutions are over.
     *
     * @return whether or not it's finished
     */
    public boolean evaluate() {
        for (int i = 0; i < this.population.length; i++)
            if (this.population[i].fitness(this.target) == 1) this.finished = true;

        return finished;
    }

    /**
     * Gets the best scored phrase from the population.
     *
     * @return best phrase
     */
    public String getBestPhrase() {
        DNA best = this.population[0];

        for (int i = 0; i < this.population.length; i++)
            if (best.fitness(this.target) < this.population[i].fitness(this.target))
                best = this.population[i];

        return best.getPhrase();
    }

    /**
     * Calculates the average fitness of current population.
     *
     * @return average fitness
     */
    public float getAverageFitness() {
        float total = 0f;

        for (int i = 0; i < this.population.length; i++)
                total += this.population[i].fitness(this.target);

        return total / this.population.length;
    }

    /**
     * Getter
     */
    public int getGenerations() {
        return generations;
    }
}
