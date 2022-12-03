package com.example.ryderr.ui.main.studentHome.request;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ryderr.R;

public class RequestCabViewHolder extends RecyclerView.ViewHolder{



    TextView from;
    TextView to;
    TextView timeText;
    TextView fare_text;
    TextView request_name;
    Button joinButton;
    View view;


    public RequestCabViewHolder(View itemView) {
        super(itemView);

        from = itemView.findViewById(R.id.request_from_text);
        to = itemView.findViewById(R.id.request_to_text);
        timeText = itemView.findViewById(R.id.request_time_text);
        fare_text = itemView.findViewById(R.id.cab_fare_text);
        request_name = itemView.findViewById(R.id.request_text);
        joinButton = itemView.findViewById(R.id.request_join_button);
        view = itemView;
    }
}
