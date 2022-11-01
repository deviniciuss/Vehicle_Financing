package com.vehiclefinancing.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import com.sun.istack.NotNull;
import java.util.Date;


@Data
@Entity
@Table(name="vehicle_financing")
@Component
@AllArgsConstructor
@NoArgsConstructor
public class VehicleFinancing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @CreationTimestamp
    private Date creationTime;

    @NotNull
    private String typeFinancing;

    @NotNull
    private int months;

    @NotNull
    private double valueInstallment;

    @NotNull
    private double vehiclePrice;

    @OneToOne
    (cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.EAGER)
    private User user;

    public double createValueInstallment( String typeFinancing, int months, double vehiclePrice){
        this.typeFinancing = typeFinancing.toLowerCase();
        this.months = months;
        this.vehiclePrice = vehiclePrice;
        double totalAmount = 0.0;
        double result = 0.0;

        if(typeFinancing.equals("internal") && months < 48){
            totalAmount = vehiclePrice * (1+ Percentage.interPercent / 100);
        }
        if(typeFinancing.equals("external") && months < 60){
            totalAmount = vehiclePrice * (1+ Percentage.externPercent / 100);
        }
        else {
            System.out.println("Incorrect Type");
        }

        result = totalAmount / months;

        return Double.parseDouble(String.format("%.2f", result));

    }



}
