package com.example.carcompare.services;

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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = {"CarCompare"})
public class SuggestionService {

    Logger logger = LoggerFactory.getLogger(SuggestionService.class);

    private final int RECOMMENDATION_LENGTH = 10; //TODO derive from property

    @Autowired
    ModelVariantService modelVariantService;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    ModelVariantRepository modelVariantRepository;

    @Cacheable
    public List<ModelVariant> getSuggestionByModel(ModelVariant selectedModel) {
        List<ModelVariant> recommendedModels = new LinkedList<>();

        // First fetch all the model with selected category
        List<ModelVariant> modelVariantsWithSameCategory = modelVariantService.getAllModelVariantByCategory(selectedModel.getCategory())
                .stream().filter(modelVariant -> !modelVariant.equals(selectedModel)).collect(Collectors.toList());

        if (!modelVariantsWithSameCategory.isEmpty()) {
            recommendedModels.addAll(modelVariantsWithSameCategory);
        }

        if (recommendedModels.size() < RECOMMENDATION_LENGTH) {
            // fetch all the model with selected brand
            List<ModelVariant> modelVariantsWithSameBrand = modelVariantRepository.findByBrandIgnoreCase(selectedModel.getBrand())
                    .stream().filter(modelVariant -> !modelVariant.equals(selectedModel)).collect(Collectors.toList());
            if (!modelVariantsWithSameBrand.isEmpty()) {
                recommendedModels.addAll(modelVariantsWithSameBrand);
            }
        }

        if(recommendedModels.size() > RECOMMENDATION_LENGTH) {
            List<ModelVariant> finalRecommendedModels = new LinkedList<>();
            Iterator<ModelVariant> iterator = recommendedModels.iterator();
            int i = 0;
            while (iterator.hasNext() && i < RECOMMENDATION_LENGTH) {
                finalRecommendedModels.add(iterator.next());
                i++;
            }
            return finalRecommendedModels;
        }
        return recommendedModels;
    }

    @Cacheable
    public List<ModelVariant> getSuggestionForModelId(int modelVariantId) {
        logger.info("Getting suggestions for {}",modelVariantId);
        ModelVariant selectedModel = modelVariantService.getModelVariantById(modelVariantId);
        if (null != selectedModel) {
            return getSuggestionByModel(selectedModel);
        }
        return new ArrayList<>();
    }
}
