package com.example.carcompare.services;

import com.example.carcompare.model.ComparisonReport;
import com.example.carcompare.model.ModelVariant;
import com.example.carcompare.request.ComparisonRequest;
import com.example.carcompare.util.ComparisonReportUtil;
import com.example.carcompare.util.ReflectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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

    @Cacheable(key = "#comparisonRequest.getCacheKey()")
    public ComparisonReport compareModels(ComparisonRequest comparisonRequest) {
        logger.info("Preparing ComparisonReport for {}",comparisonRequest);
        List<ModelVariant> modelVariants = modelVariantService.getAllModelVariantByIds(comparisonRequest.getModelVariantIds());
        List<Field> fields = ReflectionUtil.getAllFields(ModelVariant.class);
        return prepareComparisonReport(fields, modelVariants, comparisonRequest.isHideSimilarFeature());
    }

    private ComparisonReport prepareComparisonReport(List<Field> fields, List<ModelVariant> modelVariants, boolean hideSimilarFeature) {
        ComparisonReport report = new ComparisonReport();
        ComparisonReportUtil.compareObjects(new LinkedList<Object>(modelVariants), report, hideSimilarFeature);
        return report;
    }
}
