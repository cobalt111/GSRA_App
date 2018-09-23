package com.timothycox.gsra_app.util;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class DatabaseRequest {

    private static Database database = Database.getInstance();

    public static void placeholderName(DatabaseReference databaseReference, boolean continuousUpdating,
                                   final OnGetDataListener listener) {
        listener.onStart();
        if (continuousUpdating) {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    listener.onSuccess(dataSnapshot);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    listener.onFailure(databaseError);
                }
            });
        } else {
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    listener.onSuccess(dataSnapshot);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    listener.onFailure(databaseError);
                }
            });
        }
    }

    public interface OnGetDataListener {
        void onSuccess(DataSnapshot dataSnapshot);

        void onStart();

        void onFailure(DatabaseError databaseError);
    }

}