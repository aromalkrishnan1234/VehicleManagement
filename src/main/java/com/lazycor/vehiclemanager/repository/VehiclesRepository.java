package com.lazycor.vehiclemanager.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lazycor.vehiclemanager.model.Vehicles;

@Repository
public interface VehiclesRepository extends MongoRepository<Vehicles, Integer>{
	
	boolean deleteByLicencePlateNumber(String licencePlateNumber);
	Vehicles findByLicencePlateNumber(String licencePlateNumber);
}
