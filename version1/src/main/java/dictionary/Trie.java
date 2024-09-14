package dictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Trie {
    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Thêm từ vào Trie, sau nay co the insert pronunciation,...
    public void insert(String word, String definition) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node = node.getChildren().computeIfAbsent(c, k -> new TrieNode());
        }
        node.setEndOfWord(true);
        node.setDefinition(definition);
        //node.setPronunciation(pronunciation);
    }

    // Tìm kiếm từ trong Trie
    public TrieNode search(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node = node.getChildren().get(c);
            if (node == null) {
                return null;
            }
        }
        return node.isEndOfWord() ? node : null;
    }

    // Tìm kiếm theo tiền tố
    public List<String> autocomplete(String prefix) {
        List<String> results = new ArrayList<>();
        TrieNode node = search(prefix);
        if (node != null) {
            autocompleteHelper(node, new StringBuilder(prefix), results);
        }
        return results;
    }

    private void autocompleteHelper(TrieNode node, StringBuilder prefix, List<String> results) {
        if (node.isEndOfWord()) {
            results.add(prefix.toString());
        }
        for (Map.Entry<Character, TrieNode> entry : node.getChildren().entrySet()) {
            autocompleteHelper(entry.getValue(), prefix.append(entry.getKey()), results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    public TrieNode getRoot() {
        return root;
    }
}

