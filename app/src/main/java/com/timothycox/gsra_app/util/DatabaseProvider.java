package com.timothycox.gsra_app.util;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class DatabaseProvider {

    private static Firebase firebase = Firebase.getInstance();
    private static DataSnapshot currentDatabase = null;

    public static void submit() {

    }

    public static List<String> getData() {
        Query query = firebase.getDatabaseReference()
                .child("server")
                .child("animals")
                .orderByChild("date");
        firebase.access(false, query, new Firebase.OnGetDataListener() {
            @Override
            public void onSuccessfulAdd(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onSuccessfulChange(DataSnapshot dataSnapshot) {
                currentDatabase = dataSnapshot;
            }

            @Override
            public void onSuccessfulRemoval(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onSuccessfulMove(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onFailure(DatabaseError databaseError) {
                Log.d(TAG, databaseError.getDetails());
            }
        });
        return parseDataSnapshotAsList(currentDatabase);
    }

    private static List<String> parseDataSnapshotAsList(DataSnapshot dataSnapshot) {
        List<String> list = new ArrayList<>();
        Iterable<DataSnapshot> snapshotIterable = dataSnapshot.getChildren();
        for (DataSnapshot stringEntry : snapshotIterable) {
            list.add(stringEntry.getValue(String.class));
        }
        return list;
    }
}
