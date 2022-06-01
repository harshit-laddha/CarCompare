package com.example.carcompare.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComparisonRequest implements Serializable {
    Integer[] modelVariantIds;
    boolean hideSimilarFeature;

    public String getCacheKey() {
        StringBuilder builder = new StringBuilder();
        builder.append("COMPARISON_KEY_");
        for(Integer modelId : modelVariantIds) {
            builder.append(String.valueOf(modelId)).append("_");
        }
        builder.append(String.valueOf(isHideSimilarFeature()));
        return builder.toString();
    }
}
