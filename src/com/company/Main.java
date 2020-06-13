package com.company;

/**
 * Algorithm that uses "generations", "populations" and "dna" to guess the provided sentence.
 */
public class Main {

    public static void main(String[] args) {
        Population population = new Population("never gonna give you up", 0.01f, 500);

        float average = 0f;

        while (!population.evaluate()) {
            System.out.println(population.getBestPhrase());

            average += population.getAverageFitness();

            population.naturalSelection();

            population.generate();
        }

        System.out.println(
                String.format(
                        "%n%nGenerations: %d%nAverage fitness: %f%nResult: %s%n",
                        population.getGenerations(),
                        average / population.getGenerations(),
                        population.getBestPhrase()
                    ));
    }
}
