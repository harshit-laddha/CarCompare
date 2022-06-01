package com.example.carcompare.repos;

import com.example.carcompare.model.ModelVariant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelVariantRepository extends MongoRepository<ModelVariant, Integer> {

    List<ModelVariant> findByBrandIgnoreCase(String brand);

    List<ModelVariant> findByModelNameIgnoreCase(String modelName);
}
