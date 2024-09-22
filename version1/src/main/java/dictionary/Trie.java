package dictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Trie {
    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    //search word in trie, return node
    public TrieNode search1(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node = node.getChildren().get(c);
            if (node == null) {
                return null; // Word not found
            }
        }
        return node; // Return the TrieNode at the end of the term
    }
    //Search for a word in the Trie and return the Word object
    public Word search2(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node = node.getChildren().get(c);
            if (node == null) {
                return null; // Word not found
            }
        }
        return node.isEndOfWord() ? node.getWord() : null; // Return Word if it exists
    }

    // Add a word to the Trie, including its definition
    public void insert(String term, String definition) {
        TrieNode node = root;
        for (char c : term.toCharArray()) {
            node = node.getChildren().computeIfAbsent(c, k -> new TrieNode());
        }
        node.setEndOfWord(true);
        node.setWord(new Word(term, definition)); // Store Word object
    }

    // Find the meaning of the word
    public String searchMeaning(String term) {
        Word word = search2(term);
        return word != null ? word.getWord_explain() : null; // Return meaning if the word is valid
    }

    // Autocomplete suggestions for a prefix
    public List<Word> autocomplete(String prefix) {
        List<Word> results = new ArrayList<>();
        TrieNode node = search1(prefix);
        if (node != null) {
            autocompleteHelper(node, new StringBuilder(prefix), results);
        }
        return results;
    }

    private void autocompleteHelper(TrieNode node, StringBuilder prefix, List<Word> results) {
        if (node.isEndOfWord()) {
            results.add(node.getWord()); // Add Word object to results
        }
        for (Map.Entry<Character, TrieNode> entry : node.getChildren().entrySet()) {
            autocompleteHelper(entry.getValue(), prefix.append(entry.getKey()), results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    // Delete a word from the Trie
    public boolean delete(String term) {
        return delete(root, term, 0);
    }

    private boolean delete(TrieNode currentNode, String term, int index) {
        if (index == term.length()) {
            if (!currentNode.isEndOfWord()) {
                return false; // Word not found
            }
            currentNode.setEndOfWord(false);
            return currentNode.getChildren().isEmpty(); // Check if the node can be deleted
        }

        char c = term.charAt(index);
        TrieNode node = currentNode.getChildren().get(c);
        if (node == null) {
            return false;
        }

        boolean shouldDeleteCurrentNode = delete(node, term, index + 1);

        if (shouldDeleteCurrentNode) {
            currentNode.getChildren().remove(c);
            return currentNode.getChildren().isEmpty() && !currentNode.isEndOfWord();
        }
        return false;
    }

    // Search for all words starting with a given prefix
    public void searchByPrefix(String prefix, List<Word> result) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            node = node.getChildren().get(c);
            if (node == null) {
                return; // If prefix not found, exit
            }
        }
        //if prefix found, collect all words from here
        collectAllWords(node, prefix, result);
    }

    //recursive function to collect all words from the current node
    private void collectAllWords(TrieNode node, String prefix, List<Word> result) {
        if (node.isEndOfWord()) {
            result.add(node.getWord()); //add Word object to the results
        }
        for (char c : node.getChildren().keySet()) {
            collectAllWords(node.getChildren().get(c), prefix + c, result);
        }
    }

    public TrieNode getRoot() {
        return root;
    }
}
