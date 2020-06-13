package com.company;

/**
 * Algorithm that uses "generations", "populations" and "dna" to guess the provided sentence.
 *
 * The target sentence accepts a-z and spaces.
 */
public class Main {

    public static void main(String[] args) {
        Population population = new Population("never gonna give you up", 0.01f, 1500);

        float average = 0f;

        while (!population.evaluate()) {
            System.out.println(population.getBestPhrase());

            average += population.getAverageFitness();

            population.naturalSelection();

            population.generate();
        }

        System.out.println(population.getBestPhrase());

        System.out.println(
                String.format(
                        "%n%nGenerations: %d%nAverage fitness: %f%nResult: %s%n",
                        population.getGenerations(),
                        average / population.getGenerations(),
                        population.getBestPhrase()
                    ));
    }
}
