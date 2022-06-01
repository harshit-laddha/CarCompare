package com.example.carcompare.services;

import com.example.carcompare.model.ComparisonReport;
import com.example.carcompare.model.ModelVariant;
import com.example.carcompare.util.ComparisonReportUtil;
import com.example.carcompare.util.ReflectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

@Service
@CacheConfig(cacheNames = {"CarCompare"})
public class CompareService {
    Logger logger = LoggerFactory.getLogger(CompareService.class);

    static Set<String> fieldsToIgnoreInReport = new HashSet<>();

    static {
        fieldsToIgnoreInReport.add("id");
    }
    @Autowired
    ModelVariantService modelVariantService;

    @Cacheable
    public ComparisonReport compareModels(Integer[] modelVariantIds, boolean hideSimilarFeature) {
        logger.info("Preparing ComparisonReport for {}, hideSimilarFeature:{}",modelVariantIds, hideSimilarFeature);
        List<ModelVariant> modelVariants = modelVariantService.getAllModelVariantByIds(modelVariantIds);
        List<Field> fields = ReflectionUtil.getAllFields(ModelVariant.class);
        return prepareComparisonReport(fields, modelVariants, hideSimilarFeature);
    }

    private ComparisonReport prepareComparisonReport(List<Field> fields, List<ModelVariant> modelVariants, boolean hideSimilarFeature) {
        ComparisonReport report = new ComparisonReport();
        ComparisonReportUtil.compareObjects(new LinkedList<Object>(modelVariants), report, hideSimilarFeature);
        return report;
    }
}
