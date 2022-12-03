package com.example.ryderr.ui.main.login;

import com.example.ryderr.models.Student;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;


    public boolean isUserSignedIn() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            return true;
        }
        return false;
    }
    public void signin(){

    }
    public void addUser(FirebaseUser firebaseUser){
        Student student = new Student(firebaseUser.getUid(), firebaseUser.getDisplayName(),
                firebaseUser.getEmail());
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("students").child(student.getUid()).setValue(student);


    }
//    public void addCab(){
//        final long[] cabId = {0};
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//        Cab cab1 = new Cab("c1",null,5,0,500,false,"Ravu", "TN08XX9999", "BITS", "Alankrita","3:00pm");
//        mDatabase.child("cabs").child("c1").setValue(cab1);
//    }
    public void updateStatus(FirebaseUser firebaseUser){

    }
}