package com.example.ryder.ui.main.cabs.request;

import androidx.lifecycle.ViewModel;

import com.example.ryder.ui.main.cabs.request.RequestCab;

import java.util.ArrayList;

public class RequestViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    public ArrayList<RequestCab> populate(){

        ArrayList<RequestCab>  list = new ArrayList<>();
        list.add(new RequestCab());
        list.add(new RequestCab());
        list.add(new RequestCab());
        list.add(new RequestCab());
        list.add(new RequestCab());
        list.add(new RequestCab());
        list.add(new RequestCab());

        return list;
    }
}