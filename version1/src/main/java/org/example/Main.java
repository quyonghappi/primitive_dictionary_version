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
            System.out.println("Welcome to Chuche Dictionary!");
            System.out.println("[0] Exit");
            System.out.println("[1] Insert words from command line");
            System.out.println("[2] Remove words from command line");
            System.out.println("[3] Update");
            System.out.println("[4] Display");
            System.out.println("[5] Look up");
            System.out.println("[6] Exit");
            System.out.println("[7] Game");
            System.out.println("[8] Import from file");
            System.out.println("[9] Export to file");
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
            int choice=-1;
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
                //done
                case 1:
                    System.out.println("Inserting words from command line...");
                    dictionary.insertFromCommandline();
                    break;
                    //done
                case 2:
                    System.out.println("Removing words from command line...");
                    dictionary.deleteWord();
                    break;
                    //done
                case 5:
                    System.out.println("Looking up words =)");
                    dictionary.lookUpWord();
                    break;
                    //done
                case 4:
                    System.out.println("Showing all words:");
                    dictionary.showAllWords();
                    break;
                    //done
                case 3:
                    System.out.println("You can update words right now!");
                    dictionary.editWord();
                    break;
                    //done
                case 6:
                    System.out.println("Enter the word to search...");
                    dictionary.searchByPrefix();
                    break;
                    //done
                case 7:
                    System.out.println("This function is not available :(((. Please choose another function!");
                    break;
                    //done
                case 8:
                    dictionary.importFromFile();
                    System.out.println("Successfully imported file.");
                    break;
                case 9:
                    System.out.println("Here is your wordlist");
                    dictionary.exportToFile();
                    break;
                    //done
                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    return; //Exit the program
                default:
                    System.out.println("Action not supported :(((");
                    break;
            }
        }
    }
}
