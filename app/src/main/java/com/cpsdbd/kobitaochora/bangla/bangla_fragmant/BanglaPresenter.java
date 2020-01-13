package com.cpsdbd.kobitaochora.bangla.bangla_fragmant;

import android.util.Log;

import androidx.annotation.NonNull;

import com.cpsdbd.kobitaochora.firebase.MyDatabase;
import com.cpsdbd.kobitaochora.model.Kobita;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BanglaPresenter implements BanglaContract.Presenter {

    private BanglaContract.View mView;
    private Kobita kobita;

    public BanglaPresenter(BanglaContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void getAllKobita() {
        Log.d("tagUSer","activity ok");
       /* MyDatabase.getPoemRef().setValue("bangla");*/

        MyDatabase.getPoemRef().child("bangla")
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("tagUSer","data recieve");
                Log.d("tagUSer",dataSnapshot.toString());

                List<Kobita> userList = new ArrayList<>();

                for (DataSnapshot x : dataSnapshot.getChildren()){
                    userList.add(x.getValue(Kobita.class));
                }

                mView.updateKobita(userList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("tagUSer","data failed");
            }
        });

        /*MyDatabase.getPoemRef().child("bangla")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Log.d("tagUSer","data recieve");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.d("tagUSer","data recieve");
                    }
                });*/
    }
}
