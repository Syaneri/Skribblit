package ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Lobby implements Serializable {

    private int id;
    private String name;
    private String[] words;
    private boolean selected;

    public Lobby(int id, String name, String[] words) {
        this.id = id;
        this.name = name;
        this.words = words;
        selected = false;
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

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
