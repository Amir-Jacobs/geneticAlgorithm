package com.company;

import java.util.Arrays;
import java.util.Random;

public class DNA {
    private char[] genes;

    public DNA(int length) {
        this.genes = new char[length];

        // add random character to each index of array
        for (int i = 0; i < this.genes.length; i++)
            this.genes[i] = generateRandomChar();
    }

    /**
     * Returns a floating point representation of fitness score.
     *
     * 0.5 = 50% fit.
     */
    public float fitness(String target) {
        float score = 0;

        for (int i = 0; i < this.genes.length; i++)
            if (this.genes[i] == target.charAt(i)) score++;

        return score / this.genes.length;
    }

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

    private char generateRandomChar() {
        String chars = "abcdefghijklmnopqrstuvwxyz ";
        Random random = new Random();
        return chars.charAt(random.nextInt(chars.length()));
    }

    public void setGenes(String genes) {
        this.genes = genes.toCharArray();
    }

    public String getPhrase() {
        return String.copyValueOf(this.genes);
    }

    public char[] getGenes() {
        return this.genes;
    }
}
