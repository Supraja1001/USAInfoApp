package com.example.supraja.usainfoapp;

import java.util.List;

/**
 * Created by Supraja on 6/15/2017.
 */

public class LocationData {
    private List<LocationBean> Location;

    public List<LocationBean> getLocation() {
        return Location;
    }

    public void setLocation(List<LocationBean> Location) {
        this.Location = Location;
    }

    public static class LocationBean {
        /**
         * state : Alaska
         * latitude : 61.385
         * longitude : -152.2683
         */

        private String state;
        private double latitude;
        private double longitude;

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }
    }
}
