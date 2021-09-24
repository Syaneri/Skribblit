package ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.model;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Drawing implements Serializable {
    private String name;
    private Bitmap bitmap;
    private byte[] byteArray;

    public Drawing(String name, Bitmap bitmap) {
        this.name = name;
        this.bitmap = bitmap;
    }

    public Drawing(String name, byte[] byteArray) {
        this.name = name;
        this.byteArray = byteArray;
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

    public byte[] getByteArray() { return byteArray; }
}
