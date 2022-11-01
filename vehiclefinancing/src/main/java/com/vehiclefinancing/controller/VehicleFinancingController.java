package com.vehiclefinancing.controller;


import com.vehiclefinancing.model.VehicleFinancing;
import com.vehiclefinancing.service.VehicleFinancingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
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
        vehicleFinancing.setValueInstallment(vehicleFinancing.createValueInstallment(
                vehicleFinancing.getTypeFinancing(),
                vehicleFinancing.getMonths(),
                vehicleFinancing.getVehiclePrice()
        ));
        return vehicleFinancing.getValueInstallment();
    }


    @PostMapping("/add")
    public VehicleFinancing add(@RequestBody VehicleFinancing vehicleFinancing){
        vehicleFinancing.setValueInstallment(vehicleFinancing.createValueInstallment(
                vehicleFinancing.getTypeFinancing(),
                vehicleFinancing.getMonths(),
                vehicleFinancing.getVehiclePrice()
        ));
        return service.add(vehicleFinancing);
    }
}
