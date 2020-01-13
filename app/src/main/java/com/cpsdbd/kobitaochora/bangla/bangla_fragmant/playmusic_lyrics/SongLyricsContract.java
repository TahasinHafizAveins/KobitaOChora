package com.cpsdbd.kobitaochora.bangla.bangla_fragmant.playmusic_lyrics;

import com.cpsdbd.kobitaochora.model.Kobita;

import java.util.List;

public class SongLyricsContract {
    interface Presenter{
        void getLyrics(String kobitaName);
    }
    interface View{
        void lyrics(List<Kobita> kobitaList);
    }
}
