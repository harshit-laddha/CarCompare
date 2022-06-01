package com.example.carcompare.model;

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
public class Dimension implements Serializable {

    private double length;
    private double width;
    private double height;
    private double wheelBase;
    private double bootSpace;
    private double weight;
    private int seatingCapacity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dimension dimension = (Dimension) o;
        return Double.compare(dimension.length, length) == 0 && Double.compare(dimension.width, width) == 0
                && Double.compare(dimension.height, height) == 0 && Double.compare(dimension.wheelBase, wheelBase) == 0
                && Double.compare(dimension.bootSpace, bootSpace) == 0 && Double.compare(dimension.weight, weight) == 0
                && seatingCapacity == dimension.seatingCapacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, width, height, wheelBase, bootSpace, weight, seatingCapacity);
    }
}
