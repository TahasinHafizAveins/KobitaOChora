package com.cpsdbd.kobitaochora.bangla.bangla_fragmant.playmusic_lyrics;

import android.util.Log;

import androidx.annotation.NonNull;

import com.cpsdbd.kobitaochora.firebase.MyDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class SongLyricsPresenter implements SongLyricsContract.Presenter {

    private SongLyricsContract.View mView;

    public SongLyricsPresenter(SongLyricsContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void getLyrics(final String kobitaName) {

        MyDatabase.getPoemRef().child("bangla").addListenerForSingleValueEvent(new ValueEventListener() {
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
