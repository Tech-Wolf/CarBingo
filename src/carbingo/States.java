/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carbingo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Geoff
 */
public class States {
    
    private static final int NUMBER_OF_WORDS = 50;
    private static final String FILE_NAME = "states.txt";
    private String[] states;

    // The default constructor instantiates the array of Strings and reads
    // the words from the input file and loads them into the array.
    public States() {
        states = new String[NUMBER_OF_WORDS];
        Scanner inputStream = null;
        try {
            inputStream = new Scanner(new File(FILE_NAME));
            for (int i = 0; i < NUMBER_OF_WORDS; i++) {
                states[i] = inputStream.next();
            }
        } catch (FileNotFoundException e) {
            System.out.println("dictionaryWords.txt File Not Found.");
            System.exit(0);
        }
    }

    // Method getRandomWord() returns a random word from array dictionary.
    public String getRandomWord() {
        Random generator = new Random();
        return states[generator.nextInt(NUMBER_OF_WORDS)];
    }
    
}
