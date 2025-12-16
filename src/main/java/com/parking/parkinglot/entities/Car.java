package com.parking.parkinglot.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "license_plate")
    private String licensePlate;

    @Column(name = "parkingspot")
    private String parkingspot;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    // --- One-to-One cu CarPhoto ---
    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CarPhoto photo;

    // ==================
    //      GETTERS
    // ==================

    public Long getId() {
        return id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getParkingspot() {
        return parkingspot;
    }

    public User getOwner() {
        return owner;
    }

    public CarPhoto getPhoto() {
        return photo;
    }

    public String getUsername() {
        return owner != null ? owner.getUsername() : null;
    }

    // ==================
    //      SETTERS
    // ==================

    public void setId(Long id) {
        this.id = id;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setParkingspot(String parkingspot) {
        this.parkingspot = parkingspot;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setPhoto(CarPhoto photo) {
        this.photo = photo;
    }
}
