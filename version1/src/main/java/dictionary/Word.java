package dictionary;

public class Word {
    public String word;
    private String word_explain;

    //constructor
    public Word(String word, String word_explain) {
        this.word = word;
        this.word_explain = word_explain;
    }

    public String getWord() {
        return word;
    }

    public String getWord_explain() {
        return word_explain;
    }

    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }

    public String toString() {
        return word + "\t" + word_explain;
    }
}
