package ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.model;

import java.util.ArrayList;

public class Lobby {

    private int id;
    private String name;
    private String[] words;

    public Lobby(int id, String name, String[] words) {
        this.id = id;
        this.name = name;
        this.words = words;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getWords() {
        return words;
    }

    public void setWords(String[] words) {
        this.words = words;
    }
}
