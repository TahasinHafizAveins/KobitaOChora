package com.cpsdbd.kobitaochora.english.english_fragmant;

import com.cpsdbd.kobitaochora.model.Kobita;

import java.util.List;

public interface  EnglishContract {

    interface Presenter{
        void getAllKobita();
    }

    interface View{

        void updateKobita(List<Kobita> kobitaList);
    }

}
