package com.example.ryderr.ui.main.student.studentHome.live;

import android.util.Log;

import com.example.ryderr.models.LiveCab;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LiveCabsViewModel extends ViewModel {
    private static final String TAG = "Live cabs view model";
    // TODO: Implement the ViewModel
    public MutableLiveData<ArrayList<LiveCab>> studentLiveCabs;
    private DatabaseReference mDatabase;

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    public MutableLiveData<ArrayList<LiveCab>> loadLiveCabs(){
        if(studentLiveCabs==null){
            studentLiveCabs = new MutableLiveData<>();
        }
        mDatabase = FirebaseDatabase.getInstance().getReference();


        ArrayList<LiveCab> liveLiveCabs = new ArrayList<>();
        db.collection("cabs")
           //     .whereEqualTo("live", true)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                LiveCab liveCab = document.toObject(LiveCab.class);
                                liveLiveCabs.add(liveCab);
                            }
                            studentLiveCabs.setValue(liveLiveCabs);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

        return studentLiveCabs;
    }
    public void joinCab(String cabId){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String uid = mAuth.getCurrentUser().getUid();
        final ArrayList<String>[] riders = new ArrayList[]{new ArrayList<>()};
        DatabaseReference liveCabRef = mDatabase.child("cabs").child(cabId);
        // Query queryRef = liveCabRef.orderByChild("live").equalTo(true);
        liveCabRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                                      @Override
                                                      public void onDataChange(DataSnapshot dataSnapshot) {

                                                          GenericTypeIndicator<HashMap<String, String>> t =
                                                                  new GenericTypeIndicator<HashMap<String, String>>() {
                                                                  };

                                                          HashMap<String, String> hashMap;
                                                          hashMap = (HashMap<String, String>) dataSnapshot.getValue(t);

                                                          ArrayList<String> riderList;
                                                          if (hashMap != null) {
                                                              riderList = new ArrayList<String>(hashMap.values());


                                                          } else {
                                                              riderList = new ArrayList<String>();

                                                          }
//                GenericTypeIndicator<HashMap<String,Cab>> t=  new GenericTypeIndicator<HashMap<String,Cab>>() { };
//                ArrayList<Cab> cabsList = dataSnapshot.getValue(t);
                                                          riders[0] = riderList;

                                                      }

                                                      @Override
                                                      public void onCancelled(@NonNull DatabaseError error) {

                                                      }
                                                  });

            riders[0].add(uid);

        mDatabase.child("cabs").child(cabId).setValue(riders[0]);
        DatabaseReference liveCabRefCount = mDatabase.child("cabs").child(cabId);
        // Query queryRef = liveCabRef.orderByChild("live").equalTo(true);
        final int[] riders_count = {0};
        mDatabase.child("cabs").child(cabId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    riders_count[0] = (int) task.getResult().getValue();
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                }
            }
        });
        int newCount = riders_count[0]+1;
        mDatabase.child("cabs").child(cabId).child("count_riders").setValue(newCount);





    }

    public ArrayList<LiveCab> populate(){

        ArrayList<LiveCab>  list = new ArrayList<>();
        list.add(new LiveCab());
        list.add(new LiveCab());
        list.add(new LiveCab());
        list.add(new LiveCab());
        list.add(new LiveCab());
        list.add(new LiveCab());
        list.add(new LiveCab());

        return list;
    }


}