package com.company;

public class Main {

    public static void main(String[] args) {
        Population population = new Population("to be or not to be", .01f, 500);

        while (!population.evaluate()) {
            System.out.println(population.getBestPhrase());

            population.naturalSelection();

            population.generate();
        }

        System.out.println(population.getBestPhrase());
    }
}
