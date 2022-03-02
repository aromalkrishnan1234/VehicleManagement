package com.lazycor.vehiclemanager.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import org.json.simple.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="vehicle")
public class Vehicles {
	@Id
	private String id;
	private String name;
	private String vin;
	private String licencePlateNumber;
	private JSONObject vehicleProperties;


	public Vehicles() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVin() { return vin; }

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getLicencePlateNumber() {
		return licencePlateNumber;
	}

	public void setLicencePlateNumber(String licencePlateNumber) {
		this.licencePlateNumber = licencePlateNumber;
	}

	public JSONObject getVehicleProperties() {
		return vehicleProperties;
	}

	public void setVehicleProperties(JSONObject vehicleProperties) {
		this.vehicleProperties = vehicleProperties;
	}

}
