package com.example.ryderr.ui.main.driverHome.driverRequestDetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ryderr.R;
import com.example.ryderr.models.Request;
import com.google.android.material.button.MaterialButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DriverRequestDetailsFragment extends Fragment {
    private Request requestObj;
    private TextView from, to, vehicle, time, capacity;
    private MaterialButton acceptBtn;
    private ListView ridersList;

    public DriverRequestDetailsFragment(){}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestObj = new Request();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_driver_request_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        from = (TextView)view.findViewById(R.id.from);
        to = (TextView)view.findViewById(R.id.to);
        vehicle = (TextView)view.findViewById(R.id.vehicle);
        time = (TextView)view.findViewById(R.id.time);
        capacity = (TextView)view.findViewById(R.id.capacity);
        acceptBtn = (MaterialButton)view.findViewById(R.id.acceptButton);

        from.setText(requestObj.getFrom_location());
        to.setText(requestObj.getTo_location());
        vehicle.setText(requestObj.getVehicle_type());
        time.setText(requestObj.getTime());
        capacity.setText(requestObj.getCapacity());

        ridersList = (ListView)view.findViewById(R.id.ridersList);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, requestObj.getRiders_ids());
        ridersList.setAdapter(arrayAdapter);
        acceptBtn.setOnClickListener(view1 -> {

        });
        super.onViewCreated(view, savedInstanceState);
    }
}