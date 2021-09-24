package ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.model;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Drawing implements Serializable {

    private int id;
    private String name;
    private Bitmap bitmap;

    public Drawing(int id, String name, Bitmap bitmap) {
        this.id = id;
        this.name = name;
        this.bitmap = bitmap;
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

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
