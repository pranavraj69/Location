package com.example.appu.location;

/**
 * Created by APPU on 29-03-2017.
 */

public class Details {

    String name;
    String password;
    String vehiclenum;
    String phone;

    public Details() {}

    public Details(String name, String password, String vehiclenum, String phone) {
        this.name = name;
        this.password = password;
        this.vehiclenum = vehiclenum;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getVehiclenum() {
        return vehiclenum;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setVehiclenum(String vehiclenum) {
        this.vehiclenum = vehiclenum;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
