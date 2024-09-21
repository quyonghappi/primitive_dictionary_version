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

    //find the meaning of word
    public String searchMeaning(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node = node.getChildren().get(c);
            if (node == null) {
                return null; // Word not found
            }
        }
        return node.isEndOfWord() ? node.getDefinition() : null; // Return meaning if the word is valid
    }

    //Tìm kiếm theo tiền tố
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

    //delete word
    public boolean delete(String word) {
        return delete(root, word, 0);
    }

    private boolean delete(TrieNode currentNode, String word, int index) {
        if (index == word.length()) {
            if (!currentNode.isEndOfWord()) {
                return false; //word not found
            }

            currentNode.setEndOfWord(false);
            return currentNode.getChildren().isEmpty();
        }

        char c = word.charAt(index);
        TrieNode node = currentNode.getChildren().get(c);
        if (node == null) {
            return false;
        }

        boolean result = delete(node, word, index + 1);

        if (result) {
            currentNode.getChildren().remove(c);
            return currentNode.getChildren().isEmpty() && !currentNode.isEndOfWord();
        }
        return false;
    }

    // Tìm kiếm tất cả các từ bắt đầu với prefix
    public void searchByPrefix(String prefix, List<String> result) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            node = node.getChildren().get(c);
            if (node == null) {
                return; //Nếu không tìm thấy tiền tố thì end
            }
        }
        //Nếu tìm thấy tiền tố thì tìm từ ở đây
        collectAllWords(node, prefix, result);
    }

    //thu thập tất cả các từ từ node hiện tại
    private void collectAllWords(TrieNode node, String prefix, List<String> result) {
        if (node.isEndOfWord()) {
            result.add(prefix); //Thêm từ vào danh sách nếu là từ đó end rồi
        }
        for (char c : node.getChildren().keySet()) {
            collectAllWords(node.getChildren().get(c), prefix + c, result);
        }
    }

    public TrieNode getRoot() {
        return root;
    }
}

