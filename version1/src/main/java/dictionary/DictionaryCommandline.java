package dictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
}
