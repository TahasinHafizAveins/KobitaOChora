package com.cpsdbd.kobitaochora.english.english_fragmant.playmusic_lyrics;

import android.util.Log;

import androidx.annotation.NonNull;

import com.cpsdbd.kobitaochora.firebase.MyDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class EnglishSongLyricsPresenter implements EnglishSongLyricsContract.Presenter {

    private EnglishSongLyricsContract.View mView;

    public EnglishSongLyricsPresenter(EnglishSongLyricsContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void getLyrics(final String kobitaName) {

        MyDatabase.getPoemRef().child("english").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("DataSnapshot","successful "+ kobitaName);
                for (DataSnapshot childSnapshot: dataSnapshot.child(kobitaName).getChildren()) {
                    Log.d("DataSnapshot"," "+childSnapshot.getValue(String.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("DataSnapshot","unsuccessful "+databaseError.getMessage());
            }
        });

    }
}
