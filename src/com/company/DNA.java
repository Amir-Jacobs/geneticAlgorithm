package com.company;

import java.util.Random;

public class DNA {
    private char[] genes;

    /**
     * Constructor
     *
     * @param length of the final sentence
     */
    public DNA(int length) {
        this.genes = new char[length];

        // add random character to each index of array
        for (int i = 0; i < this.genes.length; i++)
            this.genes[i] = generateRandomChar();
    }

    /**
     * Calculates the fitness of the DNA.
     *
     * @param target the final sentence
     * @return a floating point representation of fitness percentage (0.5f = 50% fit).
     */
    public float fitness(String target) {
        // TODO: test this with larger targets, after huge Population array is gone

        float score = 0;

        for (int i = 0; i < this.genes.length; i++)
            if (this.genes[i] == target.charAt(i)) score++;

        return score / this.genes.length;
    }

    /**
     * Crosses DNA with a partner, so that part of the DNA is mixed.
     *
     * @param partner other DNA
     * @return a new child with genes of both parents
     */
    public DNA crossOver(DNA partner) {
        DNA child = new DNA(this.genes.length);

        Random random = new Random();
        int midPoint = random.nextInt(this.genes.length);

        String newGenes = "";

        for (int i = 0; i < this.genes.length; i++)
            if (i < midPoint) newGenes += this.genes[i];
            else newGenes += partner.getGenes()[i];

        child.setGenes(newGenes);

        return child;
    }

    /**
     * Mutates a character to a random char based on the provided mutation rate
     *
     * @param mutationRate floating point representation of a mutation percentage (0.5f = 50% chance)
     */
    public void mutate(float mutationRate) {
        Random random = new Random();

        if (random.nextInt(11) > mutationRate * 10) return;

        int index = random.nextInt(this.genes.length);

        char character = generateRandomChar();

        // replace character if it's the same
        while (character == this.genes[index])
            character = generateRandomChar();

        this.genes[index] = character;
    }

    /**
     * Generates a random character from a-z + space
     *
     * @return random character
     */
    private char generateRandomChar() {
        // TODO: make this work with more characters

        String chars = "abcdefghijklmnopqrstuvwxyz ";
        Random random = new Random();
        return chars.charAt(random.nextInt(chars.length()));
    }

    /**
     * Setter
     */
    public void setGenes(String genes) {
        this.genes = genes.toCharArray();
    }

    /**
     * Getter
     */
    public String getPhrase() {
        return String.copyValueOf(this.genes);
    }

    /**
     * Getter
     */
    public char[] getGenes() {
        return this.genes;
    }
}
