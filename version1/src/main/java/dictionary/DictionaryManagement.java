package dictionary;

import java.io.*;
import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.List;
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
            trie.insert(word, explain);
        }
    }

    public void insertFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("dictionaries.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length == 2) {
                    String englishWord = parts[0].trim();
                    String vietnameseMeaning = parts[1].trim();
                    trie.insert(englishWord, vietnameseMeaning);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dictionaryLookup() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter word: ");
        String word = scanner.nextLine().trim();

        String definition = String.valueOf(trie.searchMeaning(word));

        if (definition != null) {
            System.out.println("Definition: " + definition);
        } else {
            System.out.println("This word does not exist.");
        }
    }

    //bonus method
    //add word
    public void addWordToDictionary() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter word: ");
        String word = sc.nextLine().trim();
        System.out.println("Enter definition: ");
        String definition = sc.nextLine().trim();
        trie.insert(word, definition);
        System.out.println("Successfully added word: " + word);
    }

    //edit word
    public void editWord() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter word: ");
        String word = sc.nextLine().trim();
        if (trie.search(word) != null) {
            System.out.println("Enter new meaning: ");
            String newMeaning = sc.nextLine().trim();
            trie.insert(word, newMeaning);
            System.out.println("Successfully edited word: " + word);
        }
        else {
            System.out.println("This word does not exist.");
        }

    }

    //delete word
    public void deleteWord() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter word: ");
        String word = sc.nextLine().trim();
        if (trie.search(word) != null) {
            trie.delete(word);
            System.out.println("Successfully deleted word: " + word);
        }
        else {
            System.out.println("This word does not exist.");
        }
    }
    }


