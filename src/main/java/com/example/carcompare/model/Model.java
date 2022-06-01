package com.example.carcompare.model;

import com.example.carcompare.enums.Category;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Model implements Serializable, SequenceGen {
    private String modelName;
    private String brand;
    private Category category;
}
