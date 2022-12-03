package com.example.ryderr.ui.main.studentHome.request;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ryderr.R;
import com.example.ryderr.models.Request;

import java.util.Collections;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class RequestCabListAdapter extends RecyclerView.Adapter<RequestCabViewHolder>{
    List<Request> list = Collections.emptyList();
    Context context;

    RequestViewModel mRequestViewModel;
    public RequestCabListAdapter(List<Request> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RequestCabViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        mRequestViewModel = new RequestViewModel();
        View view = inflater.inflate(R.layout.request_cab_card, parent, false);
        RequestCabViewHolder viewHolder = new RequestCabViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RequestCabViewHolder holder, int position) {

        final int index = holder.getAdapterPosition();
        holder.from.setText(list.get(position).getFrom_location());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(
            RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
