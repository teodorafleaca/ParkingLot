package com.parking.parkinglot.ejb;

import com.parking.parkinglot.common.CarDto;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;

import jakarta.persistence.TypedQuery;
import com.parking.parkinglot.entities.Car;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class CarsBean {
    private static final Logger LOG = Logger.getLogger(CarsBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;


    public List<CarDto> findAllCars(){
        LOG.info("findAllCars");
        try{TypedQuery<Car> typedQuery = entityManager.createQuery("SELECT c FROM Car c", Car.class);
            List<Car> cars = typedQuery.getResultList();
            return copyCarsToDto(cars);

        } catch (Exception ex){
            throw new EJBException(ex);
        }

    }

    public List<CarDto> copyCarsToDto(List<Car> cars) {
        List<CarDto> carDtos = new ArrayList<>();

        for (Car car : cars) {
            // Obținem datele folosind getterii entității Car
            Long id = car.getId();
            String licensePlate = car.getLicensePlate();
            String parkingSpot = car.getParkingspot();
            String ownerName = null;

            if (car.getOwner() != null) {
                ownerName = car.getOwner().getUsername();
            }

            // Creăm DTO
            CarDto carDto = new CarDto(id, licensePlate, parkingSpot, ownerName);

            carDtos.add(carDto);
        }

        return carDtos;
    }
}




