package com.company;

import java.util.Arrays;
import java.util.Random;

public class DNA {
    private char[] genes;

    public DNA(int length) {
        this.genes = new char[length];

        // add random character to each index of array
        for (int i = 0; i < this.genes.length; i++)
            this.genes[i] = (char) (new Random().nextInt(26) + 'a');

        System.out.println(getPhrase());
    }

    public int fitness(String target) {
        int score = 0;

        for (int i = 0; i < this.genes.length; i++)
            if (this.genes[i] == target.charAt(i)) score++;

        return score / this.genes.length;
    }

    public void crossOver(DNA partner) {
        // TODO: return a child with half of each parents genes
    }

    public void mutate(float mutationRate) {
        // TODO: change one character randomly from array (1% chance)
    }

    public String getPhrase() {
        return Arrays.toString(genes);
    }
}
