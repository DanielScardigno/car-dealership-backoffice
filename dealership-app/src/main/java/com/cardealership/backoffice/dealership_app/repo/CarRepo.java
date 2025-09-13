package com.cardealership.backoffice.dealership_app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cardealership.backoffice.dealership_app.models.Car;

public interface CarRepo extends JpaRepository<Car, Integer> {

    public List<Car> findByCarModel(String model);

    public List<Car> findByYear(Integer year);
}