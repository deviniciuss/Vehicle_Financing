package com.vehiclefinancing.controller;


import com.vehiclefinancing.model.VehicleFinancing;
import com.vehiclefinancing.service.VehicleFinancingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RestController
@RequestMapping(path = "api/vehicle-financing")
public class VehicleFinancingController {

    @Autowired
    private VehicleFinancingService service;

    @GetMapping ("/{id}")
    public VehicleFinancing find(@PathVariable Long id){
         return service.find(id);
    }

    @GetMapping ("" )
    public List<VehicleFinancing> findAll(){
        return service.findAll();
    }


    @PostMapping("/calculate")
    public double calculate(@RequestBody VehicleFinancing vehicleFinancing){
        double valueInstallment = vehicleFinancing.createValueInstallment(
                    vehicleFinancing.getTypeFinancing(),
                    vehicleFinancing.getMonths(),
                    vehicleFinancing.getVehiclePrice()
        );
        vehicleFinancing.setValueInstallment(valueInstallment);
        return vehicleFinancing.getValueInstallment();
    }

    @PostMapping("/add")
    public ResponseEntity<VehicleFinancing> add(@RequestBody VehicleFinancing vehicleFinancing){
        try {
            double valueInstallment = vehicleFinancing.createValueInstallment(
                    vehicleFinancing.getTypeFinancing(),
                    vehicleFinancing.getMonths(),
                    vehicleFinancing.getVehiclePrice()
            );
            vehicleFinancing.setValueInstallment(valueInstallment);

            service.add(vehicleFinancing);

        } catch (IllegalArgumentException e){

            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(vehicleFinancing);
    }
}
