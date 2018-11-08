package com.timothycox.gsra_app.util;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.timothycox.gsra_app.model.Question;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class DatabaseProvider {

    private static Firebase firebase = Firebase.getInstance();
    private static DataSnapshot currentDatabase = null;
    private static List<Question> questionList;

    public static void submit() {

    }

    public static List<Question> getAssessment(String category) {
        Query query = firebase.getDatabaseReference()
                .child("server")
                .child("assessments")
                .child(category);
        firebase.access(false, query, new Firebase.OnGetDataListener() {
            @Override
            public void onSuccessfulAdd(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onSuccessfulChange(DataSnapshot dataSnapshot) {
                questionList = parseDataSnapshotAsQuestionList(dataSnapshot);
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
        return questionList;
    }

    private static List<Question> parseDataSnapshotAsQuestionList(DataSnapshot dataSnapshot) {
        List<Question> list = new ArrayList<>();
        Iterable<DataSnapshot> snapshotIterable = dataSnapshot.getChildren();
        for (DataSnapshot questionEntry : snapshotIterable) {
            list.add(questionEntry.getValue(Question.class));
        }
        return list;
    }
}
