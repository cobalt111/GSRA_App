package com.timothycox.gsra_app.util;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

public class Firebase {

    private static final Firebase ourInstance = new Firebase();
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String firebaseInstanceToken;

    private Firebase() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
        firebaseInstanceToken = FirebaseInstanceId.getInstance().getToken();
    }

    public static Firebase getInstance() {
        return ourInstance;
    }

    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }

    public String getFirebaseInstanceToken() {
        return firebaseInstanceToken;
    }

    public void access(final boolean continuousUpdating, final Query locationOfQuery,
                       final Firebase.OnGetDataListener listener) {
        locationOfQuery.addValueEventListener(new ValueEventListener() {
                  @Override
                  public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                      if (!continuousUpdating) locationOfQuery.removeEventListener(this);
                      listener.onSuccess(dataSnapshot);
                  }

                  @Override
                  public void onCancelled(@NonNull DatabaseError databaseError) {
                      if (!continuousUpdating) locationOfQuery.removeEventListener(this);
                      listener.onFailure(databaseError);
                  }
              });
        locationOfQuery.keepSynced(true);
    }

//    public void access(final boolean continuousUpdating, final Query locationOfQuery,
//                       final Firebase.OnGetDataListener listener) {
//        locationOfQuery.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                if (!continuousUpdating) locationOfQuery.removeEventListener(this);
//                listener.onSuccess(dataSnapshot);
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//                if (!continuousUpdating) locationOfQuery.removeEventListener(this);
//                listener.onSuccessfulChange(dataSnapshot);
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//                if (!continuousUpdating) locationOfQuery.removeEventListener(this);
//                listener.onSuccessfulRemoval(dataSnapshot);
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//                if (!continuousUpdating) locationOfQuery.removeEventListener(this);
//                listener.onSuccessfulMove(dataSnapshot);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                if (!continuousUpdating) locationOfQuery.removeEventListener(this);
//                listener.onFailure(databaseError);
//            }
//        });
//        locationOfQuery.keepSynced(true);
//    }

    public interface OnGetDataListener {
        void onSuccess(DataSnapshot dataSnapshot);

        void onFailure(DatabaseError databaseError);
    }
}
