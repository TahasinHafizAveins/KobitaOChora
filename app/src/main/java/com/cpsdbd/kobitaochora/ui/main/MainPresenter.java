package com.cpsdbd.kobitaochora.ui.main;

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

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;


    public MainPresenter(MainContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void getAllPoem() {
        Log.d("tagUSer","activity ok");
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        MyDatabase.getPoemRef().keepSynced(true);

        MyDatabase.getPoemRef().addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Log.d("tagUSer","data recieve");
                        Log.d("tagUSer",dataSnapshot.toString());
                        List<Kobita> banglaPoems = new ArrayList<>();
                        List<Kobita> englishPoems = new ArrayList<>();

                        for (DataSnapshot x : dataSnapshot.getChildren()){
                            Log.d("ssssss",x.toString());


                            if (x.getKey().equals("bangla")){
                                for (DataSnapshot y : x.getChildren()){
                                    banglaPoems.add(y.getValue(Kobita.class));
                                }
                            }
                            if (x.getKey().equals("english")){
                                for (DataSnapshot y : x.getChildren()){
                                    englishPoems.add(y.getValue(Kobita.class));
                                }
                            }
                        }

                        mView.updateFragment(banglaPoems,englishPoems);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.d("tagUSer","data failed");
                    }
                });

    }
}
