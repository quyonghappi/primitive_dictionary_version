package org.example;
//package dictionary;
import dictionary.Dictionary;
import java.util.InputMismatchException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary(); // Tạo đối tượng Dictionary

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Insert words from command line");
            System.out.println("2. Search for a word");
            System.out.println("3. Show all words");
            System.out.println("4. Exit");
            /*int choice=0;
            boolean valid=false;
            while (!valid) {
                try {
                    choice=scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    valid=true;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine(); // Consume invalid input
                }
            }*/
            int choice=0;
            try {
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    // process choice
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }


            switch (choice) {
                case 1:
                    System.out.println("Inserting words from command line...");
                    dictionary.insertFromCommandline();
                    break;
                case 2:
                    System.out.println("Enter the word to search:");
                    String word = scanner.nextLine();
                    String definition = dictionary.searchWord(word);
                    System.out.println("Definition: " + definition);
                    break;
                case 3:
                    System.out.println("Showing all words:");
                    dictionary.showAllWords();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return; // Exit the program
                default:
                    System.out.println("Invalid option, please try again.");
                    break;
            }
        }
    }
}
