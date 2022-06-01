package com.example.carcompare.model;

import lombok.Data;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Data
public class ComparisonReport implements Serializable {
    Map<String, List<Object>> comparisonMap = new LinkedHashMap<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComparisonReport report = (ComparisonReport) o;
        return comparisonMap.equals(report.getComparisonMap());
    }

    @Override
    public int hashCode() {
        return Objects.hash(comparisonMap);
    }
}
