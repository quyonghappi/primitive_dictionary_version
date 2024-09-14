package dictionary;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DictionaryManagement {
    //them thuoc tinh trie
    private Trie trie;

    /*public DictionaryManagement() {
        trie = new Trie();
    }*/
    public void setTrie(Trie trie) {
        this.trie = trie;
    }

    /*void insertFromCommandline() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of words:");

        int numOfWords = sc.nextInt();
        /* like cin.ignore() in C++ */
        /*sc.nextLine();
        for (int i=0; i<numOfWords; i++) {
            System.out.println("Enter word:");
            String word = sc.nextLine();
            System.out.println("Enter definition:");
            String definition = sc.nextLine();

            trie.insert(word, definition);
        }
        sc.close();

    }*/

    public void insertFromCommandline() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of words:");
        int numOfWords;
        try {
            numOfWords = sc.nextInt();
            sc.nextLine(); // Consume newline
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter an integer.");
            sc.nextLine();
            return;
        }

        for (int i = 0; i < numOfWords; i++) {
            System.out.println("Enter word:");
            String word = sc.nextLine();
            System.out.println("Enter explanation:");
            String explain = sc.nextLine();

            // Add word and explanation to Trie or Dictionary here
        }
    }

}
