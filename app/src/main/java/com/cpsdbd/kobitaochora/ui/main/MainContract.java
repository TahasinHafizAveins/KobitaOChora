package com.cpsdbd.kobitaochora.ui.main;

import com.cpsdbd.kobitaochora.model.Kobita;

import java.util.List;

public interface MainContract {

    interface Presenter{
        void getAllPoem();
    }

    interface View{
        void updateFragment(List<Kobita> banglaPoems, List<Kobita> englishPoems);
    }
}
