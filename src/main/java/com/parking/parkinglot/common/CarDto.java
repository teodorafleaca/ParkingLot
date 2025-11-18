package com.parking.parkinglot.common;

public class CarDto {
    Long id;
    String licensePlate;

    public String getParkingSpot() {
        return parkingSpot;
    }

    String parkingSpot;

    public String getOwnerName() {
        return ownerName;
    }

    String ownerName;

    public CarDto(Long id, String licensePlate, String parkingspot, String ownerName) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.parkingSpot = parkingspot;
        this.ownerName = ownerName;
    }

    public Long getId() {

        return id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

}
