package dictionary;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DictionaryCommandline {
    private Trie trie;

    public void setTrie(Trie trie) {
        this.trie = trie;
    }

    public void showAllWords() {
        List<Word> allWords = new ArrayList<>();

        collectWordsAndDefinitions(trie.getRoot(), "", allWords);

        for (Word word : allWords) {
            System.out.println(word); //use toString() method of Word
        }
    }

    private void collectWordsAndDefinitions(TrieNode node, String prefix, List<Word> words) {
        if (node.isEndOfWord()) {
            words.add(new Word(prefix, node.getDefinition())); // Create Word object
        }

        for (Map.Entry<Character, TrieNode> entry : node.getChildren().entrySet()) {
            collectWordsAndDefinitions(entry.getValue(), prefix + entry.getKey(), words);
        }
    }

    public void dictionarySearcher() {
        Scanner scanner = new Scanner(System.in);
        String prefix = scanner.nextLine();
        List<Word> result = new ArrayList<>();
        trie.searchByPrefix(prefix, result);

        for (Word word : result) {
            System.out.println(word);
        }
    }

    public void exportToFile() {
        List<Word> words = new ArrayList<>();

        //collect words and their def
        collectWordsAndDefinitions(trie.getRoot(), "", words);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("learnedWord.txt"))) {
            for (Word word : words) {
                writer.write(word.getWord() + "\t" + word.getWord_explain()); // Exporting term and definition
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

        //print all words from exported file
        try (BufferedReader reader = new BufferedReader(new FileReader("learnedWord.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length > 1) {
                    String word = parts[0]; //word is the first part
                    String def = parts[1]; //definition is the second part
                    System.out.println(new Word(word, def));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
