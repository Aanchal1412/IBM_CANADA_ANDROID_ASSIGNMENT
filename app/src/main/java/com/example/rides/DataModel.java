package com.example.rides;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

    public class DataModel {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("uid")
        @Expose
        private String uid;
        @SerializedName("vin")
        @Expose
        private String vin;
        @SerializedName("make_and_model")
        @Expose
        private String makeAndModel;
        @SerializedName("color")
        @Expose
        private String color;
        @SerializedName("transmission")
        @Expose
        private String transmission;
        @SerializedName("drive_type")
        @Expose
        private String driveType;
        @SerializedName("fuel_type")
        @Expose
        private String fuelType;
        @SerializedName("car_type")
        @Expose
        private String carType;
        @SerializedName("car_options")
        @Expose
        private List<String> carOptions = null;
        @SerializedName("specs")
        @Expose
        private List<String> specs = null;
        @SerializedName("doors")
        @Expose
        private Integer doors;
        @SerializedName("mileage")
        @Expose
        private Integer mileage;
        @SerializedName("kilometrage")
        @Expose
        private Integer kilometrage;
        @SerializedName("license_plate")
        @Expose
        private String licensePlate;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getVin() {
            return vin;
        }

        public void setVin(String vin) {
            this.vin = vin;
        }

        public String getMakeAndModel() {
            return makeAndModel;
        }

        public void setMakeAndModel(String makeAndModel) {
            this.makeAndModel = makeAndModel;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getTransmission() {
            return transmission;
        }

        public void setTransmission(String transmission) {
            this.transmission = transmission;
        }

        public String getDriveType() {
            return driveType;
        }

        public void setDriveType(String driveType) {
            this.driveType = driveType;
        }

        public String getFuelType() {
            return fuelType;
        }

        public void setFuelType(String fuelType) {
            this.fuelType = fuelType;
        }

        public String getCarType() {
            return carType;
        }

        public void setCarType(String carType) {
            this.carType = carType;
        }

        public List<String> getCarOptions() {
            return carOptions;
        }

        public void setCarOptions(List<String> carOptions) {
            this.carOptions = carOptions;
        }

        public List<String> getSpecs() {
            return specs;
        }

        public void setSpecs(List<String> specs) {
            this.specs = specs;
        }

        public Integer getDoors() {
            return doors;
        }

        public void setDoors(Integer doors) {
            this.doors = doors;
        }

        public Integer getMileage() {
            return mileage;
        }

        public void setMileage(Integer mileage) {
            this.mileage = mileage;
        }

        public Integer getKilometrage() {
            return kilometrage;
        }

        public void setKilometrage(Integer kilometrage) {
            this.kilometrage = kilometrage;
        }

        public String getLicensePlate() {
            return licensePlate;
        }

        public void setLicensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
        }

    }


