package com.example.ByanPrjt;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Tower {
    private String tower_id;
    private String operator;
    private String address;
    private String height;
    private String tower_type;
    private String latitude;
    private String longitude;
    private String technology;

    public String getTower_id() {
        return tower_id;
    }

    public void setTower_id(String tower_id) {
        this.tower_id = tower_id;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getTower_type() {
        return tower_type;
    }

    public void setTower_type(String tower_type) {
        this.tower_type = tower_type;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public Tower() {
        List<ArrayT> arr= new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Tower{" +
                ", tower_id='" + tower_id + '\'' +
                ", operator='" + operator + '\'' +
                ", address='" + address + '\'' +
                ", height='" + height + '\'' +
                ", tower_type='" + tower_type + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", technology='" + technology + '\'' +
                '}';
    }

    public Tower(String tower_id, String operator, String address, String height, String tower_type, String latitude, String longitude, String technology) {
        this.tower_id = tower_id;
        this.operator = operator;
        this.address = address;
        this.height = height;
        this.tower_type = tower_type;
        this.latitude = latitude;
        this.longitude = longitude;
        this.technology = technology;
    }


}