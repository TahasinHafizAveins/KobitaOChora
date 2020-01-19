package com.cpsdbd.kobitaochora.english.english_fragmant.playmusic_lyrics;

import com.cpsdbd.kobitaochora.model.Kobita;

import java.util.List;

public interface EnglishSongLyricsContract {

    interface Presenter{
        void getLyrics(String kobitaName);
    }
    interface View{
        void lyrics(List<Kobita> kobitaList);
    }
}
