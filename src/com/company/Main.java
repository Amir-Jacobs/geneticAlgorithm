package com.company;

/**
 * Algorithm that uses "generations", "populations" and "dna" to guess the provided sentence.
 *
 * The target sentence accepts a-z and spaces.
 */
public class Main {

    public static void main(String[] args) {

        int maxGenerations = 0;
        int avgGenerations = 0;

        for (int i = 0; i < 3; i++) {
            Population population = new Population("to be or not to be to be or not to be to be or not to be", 0.01f, 500);

            float average = 0f;

            while (!population.evaluate()) {
                System.out.println(population.getBestPhrase());

                average += population.getAverageFitness();

                population.generate();
            }

            if (population.getGenerations() > maxGenerations) maxGenerations = population.getGenerations();
            avgGenerations += population.getGenerations();

            System.out.println(population.getBestPhrase());

            System.out.println(
                    String.format(
                            "%n%nGenerations: %d%nAverage fitness: %f%nResult: %s%n",
                            population.getGenerations(),
                            average / population.getGenerations(),
                            population.getBestPhrase()
                    ));
        }

        avgGenerations = avgGenerations / 30;

        System.out.println(avgGenerations);
        System.out.println(maxGenerations);

        // 261 average
        // 3117 max
    }
}
