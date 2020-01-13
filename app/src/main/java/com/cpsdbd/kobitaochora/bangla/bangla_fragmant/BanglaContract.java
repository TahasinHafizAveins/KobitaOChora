package com.cpsdbd.kobitaochora.bangla.bangla_fragmant;

import com.cpsdbd.kobitaochora.model.Kobita;

import java.util.List;

public interface BanglaContract {

    interface Presenter{
        void getAllKobita();
    }

    interface View{

        void updateKobita(List<Kobita> kobitaList);

        void startSongLyricsActivity(Kobita kobita);
    }
}
