package com.example.carcompare.model;

import com.example.carcompare.constants.Constants;
import com.example.carcompare.enums.Category;
import com.example.carcompare.enums.features.ComfortFeature;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

@Document(collection = "variant")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModelVariant extends Model implements Serializable {

    @Id
    private int id;
    private String variantName;
    private float price;
    private String image;

    private Engine engine;
    private Dimension dimension;

    private Map<ComfortFeature, Object> comfortFeatures;

    @Override
    public String getSequenceName() {
        return Constants.MODEL_VARIANT_SEQUENCE;
    }

    public ModelVariant(int id, String modelName, String brand, Category category, String variantName, float price, String image, Engine engine, Dimension dimension) {
        this.setModelName(modelName);
        this.setBrand(brand);
        this.setCategory(category);
        this.id = id;
        this.variantName = variantName;
        this.price = price;
        this.image = image;
        this.engine = engine;
        this.dimension = dimension;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ModelVariant that = (ModelVariant) o;
        return Float.compare(that.price, price) == 0 && Objects.equals(variantName, that.variantName)
                && Objects.equals(image, that.image) && Objects.equals(engine, that.engine)
                && Objects.equals(dimension, that.dimension) && comfortFeatures.equals(that.getComfortFeatures());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), variantName, price, image, engine, dimension, comfortFeatures);
    }
}
