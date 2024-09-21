package dictionary;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DictionaryCommandline {
    private Trie trie;

    /*public DictionaryCommandline() {
        trie = new Trie();
    }*/
    public void setTrie(Trie trie) {
        this.trie = trie;
    }
    public void showAllWords() {
        List<String> allWords = new ArrayList<>();
        List<String> def= new ArrayList<>();

        collectWordsAndDefinitions(trie.getRoot(), "", allWords, def);

        for (int i = 0; i < allWords.size(); i++) {
            System.out.println(allWords.get(i) + "\t" + def.get(i));
        }
    }
    private void collectWordsAndDefinitions(TrieNode node, String prefix, List<String> words, List<String> definitions) {
        if (node.isEndOfWord()) {
            words.add(prefix);
            definitions.add(node.getDefinition());
        }

        for (Map.Entry<Character, TrieNode> entry : node.getChildren().entrySet()) {
            collectWordsAndDefinitions(entry.getValue(), prefix + entry.getKey(), words, definitions);
        }
    }

    public void dictionarySearcher() {
        Scanner scanner = new Scanner(System.in);
        String prefix = scanner.nextLine();
        List<String> result = new ArrayList<>();
        trie.searchByPrefix(prefix, result);


    }

    public void exportToFile() {
        List<String> words = new ArrayList<>();
        List<String> definitions = new ArrayList<>();
        
        collectWordsAndDefinitions(trie.getRoot(), "", words, definitions);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("learnedWord.txt"))) {
            for (int i = 0; i < words.size(); i++) {
                writer.write(words.get(i) + "\t" + definitions.get(i)); // Assuming meaning is separated by a tab
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

        //print all word from exported file
        try (BufferedReader reader = new BufferedReader(new FileReader("learnedWord.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line by tab
                String[] parts = line.split("\t");
                if (parts.length > 0) {
                    String word = parts[0]; //word is the first part
                    System.out.println(word);
                    String def = parts[1]; //def is the second part
                    System.out.println(def);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
