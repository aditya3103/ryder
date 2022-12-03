package com.example.ryderr.ui.main.studentHome.request;

import android.util.Log;

import com.example.ryderr.models.Request;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RequestViewModel extends ViewModel {
    private static final String TAG = "Request view model";
    // TODO: Implement the ViewModel

    public MutableLiveData<ArrayList<Request>> studentRequests;
    private DatabaseReference mDatabase;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public ArrayList<Request> populate() {

        ArrayList<Request> list = new ArrayList<>();
        list.add(new Request());
        list.add(new Request());
        list.add(new Request());


        return list;
    }

    public MutableLiveData<ArrayList<Request>> loadRequests() {

        if (studentRequests == null) {
            studentRequests = new MutableLiveData<>();
        }
        mDatabase = FirebaseDatabase.getInstance().getReference();

        ArrayList<Request> requestList = new ArrayList<>();
        db.collection("requests")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Request request = document.toObject(Request.class);
                                requestList.add(request);
                            }
                            studentRequests.setValue(requestList);
                        } else {
                            Log.d(TAG, "Error getting requests documents: ", task.getException());
                        }
                    }
                });


        return studentRequests;
    }
}