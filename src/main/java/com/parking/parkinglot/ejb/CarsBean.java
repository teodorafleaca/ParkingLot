package com.parking.parkinglot.ejb;

import com.parking.parkinglot.common.CarDto;
import com.parking.parkinglot.common.CarPhotoDto;
import com.parking.parkinglot.entities.CarPhoto;
import com.parking.parkinglot.entities.User;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;

import jakarta.persistence.TypedQuery;
import com.parking.parkinglot.entities.Car;


import java.util.ArrayList;
import java.util.Collection;
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
    public void createCar(String licensePlate, String parkingSpot, Long userId) {
        Car car = new Car();
        car.setLicensePlate(licensePlate);
        car.setParkingspot(parkingSpot);

        User user = entityManager.find(User.class, userId);
        user.getCars().add(car);
        car.setOwner(user);

        entityManager.persist(car);

    }
    public CarDto findById(Long carId) {
        LOG.info("findById: " + carId);

        try {
            Car car = entityManager.find(Car.class, carId);

            if (car == null) {
                return null;
            }

            String ownerName = car.getOwner() != null ? car.getOwner().getUsername() : null;

            return new CarDto(
                    car.getId(),
                    car.getLicensePlate(),
                    car.getParkingspot(),
                    ownerName
            );
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

      public void updateCar( Long carId, String licensePlate, String parkingSpot, Long userId) {
        LOG.info("updateCar");

        Car car = entityManager.find(Car.class, carId);
        car.setLicensePlate(licensePlate);
        car.setParkingspot(parkingSpot);

        User oldlUser=car.getOwner();
        oldlUser.getCars().remove(car);

        User user = entityManager.find(User.class, userId);
        user.getCars().add(car);
        car.setOwner(user);
      }

      public void deleteCarsByIds(Collection<Long> carIds){
        LOG.info("deleteCarsByIds");

        for (Long carId : carIds) {
            Car car = entityManager.find(Car.class, carId);
            entityManager.remove(car);
        }
      }
    public void addPhotoToCar(Long carId, String filename, String fileType, byte[] fileContent) {
        LOG.info("addPhotoToCar");

        CarPhoto photo = new CarPhoto();
        photo.setFilename(filename);
        photo.setFileType(fileType);
        photo.setFileContent(fileContent);

        Car car = entityManager.find(Car.class, carId);

        // Dacă are deja o poză → ștergem vechea poză
        if (car.getPhoto() != null) {
            entityManager.remove(car.getPhoto());
        }

        // Legăm poza de mașină
        car.setPhoto(photo);
        photo.setCar(car);

        // Salvăm noua poză
        entityManager.persist(photo);
    }
    public CarPhotoDto findPhotoByCarId(Integer carId) {

        List<CarPhoto> photos = entityManager
                .createQuery("SELECT p FROM CarPhoto p WHERE p.car.id = :id", CarPhoto.class)
                .setParameter("id", carId)
                .getResultList();

        if (photos.isEmpty()) {
            return null;
        }

        CarPhoto photo = photos.get(0); // prima poză (există maxim una)

        return new CarPhotoDto(
                photo.getId(),
                photo.getFilename(),
                photo.getFileType(),
                photo.getFileContent()
        );
    }


}




