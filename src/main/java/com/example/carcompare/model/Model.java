package com.example.carcompare.model;

import com.example.carcompare.enums.Category;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Model implements Serializable, SequenceGen {
    private String modelName;
    private String brand;
    private Category category;
}
