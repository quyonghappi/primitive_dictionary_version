package dictionary;

public class Dictionary {
    private Trie trie;

    public Dictionary() {
        trie = new Trie(); // Khởi tạo Trie
    }

    public void insertWord(String word, String definition) {
        trie.insert(word, definition); // Chèn từ và nghĩa vào Trie
    }

    public String searchWord(String word) {
        TrieNode node = trie.search(word); // Tìm kiếm từ trong Trie
        return (node != null && node.isEndOfWord()) ? node.getDefinition() : "Word not found.";
    }

    public void insertFromCommandline() {
        DictionaryManagement management = new DictionaryManagement();
        management.setTrie(trie); // Cung cấp Trie cho DictionaryManagement
        management.insertFromCommandline(); // Chèn từ từ command line
    }

    public void showAllWords() {
        DictionaryCommandline show = new DictionaryCommandline();
        show.setTrie(trie); // Cung cấp Trie cho DictionaryManagement
        show.showAllWords(); // Hiển thị tất cả các từ
    }
    }

