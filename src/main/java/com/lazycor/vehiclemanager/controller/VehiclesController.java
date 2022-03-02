package com.lazycor.vehiclemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lazycor.vehiclemanager.model.Vehicles;
import com.lazycor.vehiclemanager.repository.VehiclesRepository;

@CrossOrigin
@RestController
public class VehiclesController {
    @Autowired
    VehiclesRepository vehicesrepository;


    @SuppressWarnings("unchecked")
    @PostMapping("/addVehicle")
    private JSONObject addVehicle(@RequestBody JSONObject vehicleBody) {
        JSONObject resp = new JSONObject();
        Vehicles newVehicle = new Vehicles();
        try {
            ObjectMapper mapper = new ObjectMapper();
            newVehicle = mapper.readValue(vehicleBody.toString(), Vehicles.class);
            if (newVehicle != null) {
                try {
                    vehicesrepository.save(newVehicle);
                } catch (Exception e) {
                    e.printStackTrace();
                    resp.put("Status", "Failed");
                    resp.put("Message", "Unable to Process Insertion");
                }
            } else {
                resp.put("Status", "Failed");
                resp.put("Message", "Data not sufficient");
            }

        } catch (Exception e) {
            System.out.println(e);
            resp.put("Status", "Failed");
            resp.put("Message", "Vehicle is not saved, some issue occured");
        }
        resp.put("Status", "Success");
        resp.put("Message", "Vehicle is Added Successfully");
        resp.put("Vehicle Number", (String) newVehicle.getLicencePlateNumber());

        return resp;

    }


    @SuppressWarnings("unchecked")
    @GetMapping("/getVehicle")
    private JSONObject getVehicleDetails(@PathVariable("licencePlateNumber") String licencePlateNumber) {
        JSONObject response = new JSONObject();
        try {
            Vehicles v_res = vehicesrepository.findByLicencePlateNumber(licencePlateNumber);
            response.put("name", v_res.getName());
            response.put("vin", v_res.getVin());
            response.put("licencePlateNumber", v_res.getLicencePlateNumber());
            response.put("propertices", v_res.getVehicleProperties());
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            response.put("Status", "Failed");
            response.put("Message", "Unable to fetch details");
            return response;

        }
    }


    @DeleteMapping("/deleteVehicle/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("licencePlateNumber") String licencePlateNumber) {
        try {
            vehicesrepository.deleteByLicencePlateNumber(licencePlateNumber);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @SuppressWarnings("unchecked")
    @PostMapping("/updateVehicle")
    private JSONObject updateVehicle(@RequestBody JSONObject vehicleBody) {
        JSONObject resp = new JSONObject();
        try {
            if (vehicleBody.containsKey("license_plate") & !((String) vehicleBody.get("license_plate")).equalsIgnoreCase("")) {
                String vlp = (String) vehicleBody.get("license_plate");
                Vehicles updateVehicle = vehicesrepository.findByLicencePlateNumber(vlp);
                updateVehicle.setVehicleProperties((JSONObject) vehicleBody.getOrDefault("vehicleProperties", ""));
                updateVehicle.setVin((String) vehicleBody.getOrDefault("vin", ""));
                updateVehicle.setName((String) vehicleBody.getOrDefault("name", ""));
                vehicesrepository.save(updateVehicle);

                resp.put("Status", "Success");
                resp.put("Message", "Vehicle is Added Successfully");
                resp.put("Vehicle Number", vlp);
            } else {
                resp.put("Status", "Failed");
                resp.put("Message", "No Vehicle with given license plate, data cannot be updated");
            }
        } catch (Exception e) {
            System.out.println(e);
            resp.put("Status", "Failed");
            resp.put("Message", "Updation Failed");
        }
        return resp;
    }

}
