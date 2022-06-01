package com.example.carcompare.services;

import com.example.carcompare.enums.Category;
import com.example.carcompare.model.ModelVariant;
import com.example.carcompare.repos.ModelVariantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@CacheConfig(cacheNames = {"CarCompare"})
public class ModelVariantService {

    Logger logger = LoggerFactory.getLogger(ModelVariantService.class);

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    ModelVariantRepository modelVariantRepository;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    public ModelVariant saveModelVariant(ModelVariant modelVariant) {
        modelVariant.setId(sequenceGeneratorService.getNextSequenceId(modelVariant));
        return mongoTemplate.save(modelVariant);
    }

    @Cacheable
    public ModelVariant getModelVariantById(Integer id) {
        logger.info("Getting Model Variant {} from DB",id);
        return mongoTemplate.findById(id, ModelVariant.class);
    }

    @Cacheable
    public List<ModelVariant> getAllModelVariants() {
        logger.info("Getting all variants from DB");
        return mongoTemplate.findAll(ModelVariant.class);
    }

    @Cacheable
    public List<String> getAllBrands() {
        logger.info("Getting all Brands from DB");
        Query query = new Query();
        List<String> result = mongoTemplate.findDistinct(query,"brand","variant",ModelVariant.class,String.class);
        Set<String> uniqueSet = new HashSet<>();
        result.forEach(brandName -> uniqueSet.add(brandName.toUpperCase()));
        return new ArrayList<>(uniqueSet);
    }

    @Cacheable
    public List<ModelVariant> getAllModelVariantsByBrand(String brandName) {
        List<ModelVariant> modelVariants = modelVariantRepository.findByBrandIgnoreCase(brandName);
        return modelVariants;
    }

    @Cacheable
    public List<ModelVariant> getAllModelVariantsByModelName(String modelName) {
        List<ModelVariant> modelVariants = modelVariantRepository.findByModelNameIgnoreCase(modelName);
        return modelVariants;
    }

    @Cacheable
    public List<ModelVariant> getAllModelVariantByIds(Integer[] ids) {
        Query query = new Query(Criteria.where("_id").in(ids));
        return mongoTemplate.find(query,ModelVariant.class);
    }

    @Cacheable
    public List<ModelVariant> getAllModelVariantByCategory(Category category) {
        Query query = new Query(Criteria.where("category").is(category));
        return mongoTemplate.find(query, ModelVariant.class);
    }
}
