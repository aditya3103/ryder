package com.example.ryderr.ui.main.studentHome.live;

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
    public MutableLiveData<ArrayList<LiveCab>> getStudentLiveCabs() {

        if(studentLiveCabs==null){
            studentLiveCabs = new MutableLiveData<>();
        }
        mDatabase = FirebaseDatabase.getInstance().getReference();



        DatabaseReference liveCabRef = mDatabase.child("cabs");
       // Query queryRef = liveCabRef.orderByChild("live").equalTo(true);
        liveCabRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                GenericTypeIndicator<HashMap<String, LiveCab>> t=  new GenericTypeIndicator<HashMap<String, LiveCab>>() { };

                HashMap<String, LiveCab> hashMap= (HashMap<String, LiveCab>)dataSnapshot.getValue(t);

                ArrayList<LiveCab> cabsList;
                if(hashMap!=null) {
                    cabsList = new ArrayList<LiveCab>(hashMap.values());


                }else{
                    cabsList = new ArrayList<LiveCab>();

                }
//                GenericTypeIndicator<HashMap<String,Cab>> t=  new GenericTypeIndicator<HashMap<String,Cab>>() { };
//                ArrayList<Cab> cabsList = dataSnapshot.getValue(t);
            studentLiveCabs.setValue(cabsList);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        ChildEventListener childEventListener = new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
//                Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());
//
//                Cab cab = dataSnapshot.getValue(Cab.class);
//                ArrayList<Cab> cabs = studentLiveCabs.getValue();
//                cabs.add(cab);
//                studentLiveCabs.setValue(cabs);
//
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
//                Log.d(TAG, "onChildChanged:" + dataSnapshot.getKey());
//
//                // A comment has changed, use the key to determine if we are displaying this
//                // comment and if so displayed the changed comment.
//                Cab cab = dataSnapshot.getValue(Cab.class);
//                String cabKey = dataSnapshot.getKey();
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//                Log.d(TAG, "onChildRemoved:" + dataSnapshot.getKey());
//
//                // A comment has changed, use the key to determine if we are displaying this
//                // comment and if so remove it.
//                String cabKey = dataSnapshot.getKey();
//
//                // ...
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot,
//                    @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.w(TAG, "postComments:onCancelled", databaseError.toException());
//            }
//        };
//        mDatabase.addChildEventListener(childEventListener);

        return studentLiveCabs;
    }

    public MutableLiveData<ArrayList<LiveCab>> loadLiveCabs(){
        if(studentLiveCabs==null){
            studentLiveCabs = new MutableLiveData<>();
        }
        mDatabase = FirebaseDatabase.getInstance().getReference();


        ArrayList<LiveCab> liveLiveCabs = new ArrayList<>();
        db.collection("cabs")
                .whereEqualTo("live", true)
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
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        studentLiveCabs.setValue(liveLiveCabs);
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