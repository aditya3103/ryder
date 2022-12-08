package com.example.ryderr.ui.main.chat;

import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ryderr.MainActivity;
import com.example.ryderr.models.LiveCab;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ChatViewModel extends ViewModel {
    private MutableLiveData<List<ChatMessage>> mChatMessages;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    //TODO: initialize mutable live data

    public MutableLiveData<List<ChatMessage>> initChat(){
        if(mChatMessages==null){
            mChatMessages = new MutableLiveData<>();
        }
        return mChatMessages;
    }
    public MutableLiveData<List<ChatMessage>> getmChatMessages() {

        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference();
        MutableLiveData<List<ChatMessage>> mChatMessages= new MutableLiveData<>();

        DatabaseReference messages = dbref.child("groups").child("grpid").child("messages");

        messages.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                GenericTypeIndicator<HashMap<String, ChatMessage>> t=  new GenericTypeIndicator<HashMap<String, ChatMessage>>() { };

                HashMap<String, ChatMessage> hashMap= (HashMap<String, ChatMessage>)snapshot.getValue(t);
                List<ChatMessage> msgs;

                if(hashMap!=null) {
                    msgs = new ArrayList<ChatMessage>(hashMap.values());


                }else{
                    msgs = new ArrayList<ChatMessage>();

                }

                mChatMessages.setValue(msgs);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return mChatMessages;
    }

    public void setmChatMessages(MutableLiveData<List<ChatMessage>> mChatMessages) {
        this.mChatMessages = mChatMessages;
    }

    public void sendMessage(ChatMessage chatMessage){
        db = FirebaseFirestore.getInstance();
        db.collection("groups").document(chatMessage.getGrpID()).collection("messages").add(chatMessage).addOnSuccessListener(
                new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                    }
                }
        ).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }


}
