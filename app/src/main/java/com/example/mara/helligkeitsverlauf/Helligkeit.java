package com.example.mara.helligkeitsverlauf;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Helligkeit implements Serializable {

    private int lux;
    private String ort;
    private Date timestamp;

    public Helligkeit() {
    }

    public Helligkeit(int lux, String ort, Date timestamp) {
        this.lux = lux;
        this.ort = ort;
        this.timestamp = timestamp;
    }

    public int getLux() {
        return lux;
    }

    public void setLux(int lux) {
        this.lux = lux;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        return sdf.format(timestamp) + "\t" +  getOrt() + "\t" + getLux();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Helligkeit that = (Helligkeit) o;
        return lux == that.lux &&
                Objects.equals(ort, that.ort) &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {

        return Objects.hash(lux, ort, timestamp);
    }
}
