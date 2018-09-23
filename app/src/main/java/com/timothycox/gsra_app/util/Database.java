package com.timothycox.gsra_app.util;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

class Database {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private static final Database ourInstance = new Database();

    private Database() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public static Database getInstance() {
        return ourInstance;
    }

    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }
}