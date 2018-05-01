package com.example.mara.helligkeitsverlauf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Repo implements Serializable {

    private List<Helligkeit> liste;

    public Repo() {
        this.liste = new ArrayList<>();
    }

    public int size() {
        return liste.size();
    }

    public boolean add(Helligkeit helligkeit) {
        return liste.add(helligkeit);
    }

    public List<Helligkeit> getListe() {
        return liste;
    }

    public Helligkeit get(int index) {
        return liste.get(index);
    }
}
