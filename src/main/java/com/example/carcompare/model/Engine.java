package com.example.carcompare.model;

import com.example.carcompare.enums.DriveType;
import com.example.carcompare.enums.FuelType;
import com.example.carcompare.enums.TransmissionType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Engine implements Serializable {

    private String engineName;
    private int displacement;
    private FuelType fuelType;
    private TransmissionType transmissionType;
    private String maxPower;
    private String maxTorque;
    private int totalCylinder;
    private DriveType driveType;
    private double mileage;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Engine engine = (Engine) o;
        return displacement == engine.displacement && totalCylinder == engine.totalCylinder
                && Double.compare(engine.mileage, mileage) == 0 && Objects.equals(engineName, engine.engineName)
                && fuelType == engine.fuelType && transmissionType == engine.transmissionType && Objects.equals(maxPower, engine.maxPower)
                && Objects.equals(maxTorque, engine.maxTorque) && driveType == engine.driveType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(engineName, displacement, fuelType, transmissionType, maxPower, maxTorque, totalCylinder, driveType, mileage);
    }
}
