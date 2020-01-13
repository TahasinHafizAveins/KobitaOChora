package com.cpsdbd.kobitaochora.model;

import java.io.Serializable;
import java.util.List;

public class Kobita implements Serializable {

    private String id;
    private String kobitaName;
    private List<String> kobitaLyrics;
    private String kobitaPlay;


    public Kobita() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKobitaName() {
        return kobitaName;
    }

    public void setKobitaName(String kobitaName) {
        this.kobitaName = kobitaName;
    }

    public List<String> getKobitaLyrics() {
        return kobitaLyrics;
    }

    public void setKobitaLyrics(List<String> kobitaLyrics) {
        this.kobitaLyrics = kobitaLyrics;
    }

    public String getKobitaPlay() {
        return kobitaPlay;
    }

    public void setKobitaPlay(String kobitaPlay) {
        this.kobitaPlay = kobitaPlay;
    }
}
