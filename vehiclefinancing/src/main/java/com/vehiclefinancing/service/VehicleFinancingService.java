package com.vehiclefinancing.service;

import com.vehiclefinancing.model.VehicleFinancing;
import com.vehiclefinancing.repositories.VehicleFinancingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleFinancingService {
    @Autowired
    private VehicleFinancingRepository repository;

    public VehicleFinancing find (Long id){
         return repository.findById(id).orElse(null);
    }

    public List<VehicleFinancing> findAll (){
        return repository.findAll();
    }

    public VehicleFinancing add (VehicleFinancing vehicleFinancing){
        return repository.save(vehicleFinancing);
    }



}
