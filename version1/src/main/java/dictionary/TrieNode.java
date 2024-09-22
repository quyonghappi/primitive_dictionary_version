package dictionary;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    private Map<Character, TrieNode> children; // Con trỏ đến các nút con
    private boolean isEndOfWord; // Đánh dấu kết thúc của từ
    //private String definition;
    //private String pronunciation;
    //use Word so no need to use String def
    private Word word;


    public TrieNode() {
        this.children = new HashMap<>();
        this.isEndOfWord = false;
        word = null;
        //pronunciation = null;
    }

    // Getters and Setters
    public Map<Character, TrieNode> getChildren() {
        return children;
    }

    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    public void setEndOfWord(boolean isEndOfWord) {
        this.isEndOfWord = isEndOfWord;
    }

    public Word getWord() {
        return word; // Getter for the Word object
    }

    public void setWord(Word word) {
        this.word = word; // Setter for the Word object
    }

    // Optional: If you want to keep track of definitions directly in TrieNode
    public String getDefinition() {
        return word != null ? word.getWord_explain() : null;
    }
}
