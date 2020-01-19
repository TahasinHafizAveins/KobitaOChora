package com.cpsdbd.kobitaochora.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyDatabase {

    private static final String KOBITA = "kobitaType";

    public static DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

    public MyDatabase() {

    }

    public static DatabaseReference getPoemRef(){
       return rootRef ;
    }
}
