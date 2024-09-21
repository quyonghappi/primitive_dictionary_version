package dictionary;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    private Map<Character, TrieNode> children; //Con trỏ đến các nút con
    private boolean isEndOfWord; //Đánh dấu kết thúc của từ
    private String definition;
    private String pronunciation;

    public TrieNode() {
        this.children = new HashMap<>();
        this.isEndOfWord = false;
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

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }
}
