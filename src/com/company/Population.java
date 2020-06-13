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

    public void naturalSelection() {
        matingPool.clear();

        for (int i = 0; i < this.population.length; i++) {
            DNA member = this.population[i];

            for (int y = 0; y < (int) (member.fitness(this.target) * 100); y++)
                this.matingPool.add(member);
        }
    }

    public void generate() {
        Random random = new Random();

        for (int i = 0; i < this.population.length; i++) {

            if (this.matingPool.size() < 2) break;

            this.population[i] = this.matingPool.get(random.nextInt(this.matingPool.size())).crossOver(this.matingPool.get(random.nextInt(this.matingPool.size())));
        }

        for (DNA member : this.population)
            member.mutate(this.mutationRate);
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
