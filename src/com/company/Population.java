package com.company;

import java.util.Random;

public class Population {
    private float mutationRate;

    private DNA[] population;

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

        this.generations = 1;
        this.finished = false;

        // Generate population
        this.population = new DNA[populationMax];

        for (int i = 0; i < populationMax; i++)
            this.population[i] = new DNA(target.length());
    }


    public DNA selectOne(float sum) {
        Random random = new Random();
        float randomDigit = 0f + random.nextFloat() * sum; // 0-sum (1f not inclusive)

        for (DNA dna : this.population) {
            randomDigit -= dna.fitness(this.target);

            if (randomDigit < 0f)
                return dna;
        }

        return this.population[this.population.length - 1];
    }

    /**
     * Generates a new population based on mating pool members.
     */
    public void generate() {
        this.generations++;

        float sum = 0;

        for (DNA member : this.population)
            sum += member.fitness(this.target);

        for (int i = 0; i < this.population.length; i++) {
            this.population[i] = selectOne(sum).crossOver(selectOne(sum));
            this.population[i].mutate(this.mutationRate);
        }
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
