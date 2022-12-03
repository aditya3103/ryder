package com.example.ryderr.models;

import java.util.ArrayList;

public class Driver {
    String uid;
    String name;
    String vehicle_number;
    String vehicle_type;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVehicle_number() {
        return vehicle_number;
    }

    public void setVehicle_number(String vehicle_number) {
        this.vehicle_number = vehicle_number;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public ArrayList<LiveCab> getMyRides() {
        return myRides;
    }

    public void setMyRides(ArrayList<LiveCab> myRides) {
        this.myRides = myRides;
    }

    int capacity;
    ArrayList<LiveCab> myRides;
}
