package ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.model;

import java.io.Serializable;

public class Drawing implements Serializable {

    private int id;
    private String name;

    public Drawing(int id, String name) {
        this.id = id;
        this.name = name;
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
}
