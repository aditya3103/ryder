package com.example.ryderr.ui.main.student.studentRequestDetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ryderr.R;
import com.example.ryderr.models.Request;
import com.example.ryderr.ui.main.student.studentHome.request.RequestViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;


public class StudentRequestDetailsFragment extends Fragment {
    private Request requestObj;
    private TextView from, to, vehicle, time, capacity;
    private MaterialButton joinBtn;
    private ListView ridersListView;
    RequestViewModel mRequestViewModel;
    FirebaseAuth mAuth;

    public StudentRequestDetailsFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestObj = new Request();
        mRequestViewModel = new RequestViewModel();
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_student_request_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String requestId = StudentRequestDetailsFragmentArgs.fromBundle(getArguments()).getRequestId();
      //  Request request = mRequestViewModel.getRequest(requestId);


        Observer<Request> observer = new Observer<Request>() {
            @Override
            public void onChanged(Request request) {
                requestObj = request;
                from = (TextView)view.findViewById(R.id.from);
                to = (TextView)view.findViewById(R.id.to);
                vehicle = (TextView)view.findViewById(R.id.vehicle);
                time = (TextView)view.findViewById(R.id.time);
                capacity = (TextView)view.findViewById(R.id.capacity);
                joinBtn = (MaterialButton)view.findViewById(R.id.joinButton);
              //  TextView currentRidersCount = view.findViewById(R.id.riders_count);

                from.setText(requestObj.getFrom_location());
                to.setText(requestObj.getTo_location());
                vehicle.setText(requestObj.getVehicle_type());
                time.setText(requestObj.getTime());
                capacity.setText(String.valueOf(requestObj.getCapacity()));


                ridersListView = (ListView)view.findViewById(R.id.ridersList);
                ArrayList<String> riders = new ArrayList<>();
                riders.add("");
                if(requestObj.getRiders_names()!=null){
                    riders = requestObj.getRiders_names();
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, riders);
                ridersListView.setAdapter(arrayAdapter);



                joinBtn.setOnClickListener(view1 ->{

                    String uid = mAuth.getUid();
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    db.collection("requests").document("reqID").update("riders_ids", FieldValue.arrayUnion(uid));

                });
            }
        };

        mRequestViewModel.getRequest(requestId).observe(getViewLifecycleOwner(), observer);


    }
}