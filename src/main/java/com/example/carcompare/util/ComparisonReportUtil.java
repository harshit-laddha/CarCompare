package com.example.carcompare.util;

import com.example.carcompare.model.ComparisonReport;
import com.example.carcompare.model.ModelVariant;

import java.lang.reflect.Field;
import java.util.*;

public class ComparisonReportUtil {

    static Set<String> fieldsToIgnoreInReport = new HashSet<>();

    static {
        fieldsToIgnoreInReport.add("id");
    }

    public static void addFieldsToReport(Field field, List<ModelVariant> objects, ComparisonReport report, boolean hideSimilarFeature) {
        String fieldName = field.getName();
        List<Object> fieldValues = new LinkedList<>();
        for(ModelVariant object : objects) {
            Object value = ReflectionUtil.runGetter(field, object);
            if (field.getClass().getPackage().getName().startsWith("com.example.model")) {
                List<Field> allSubFields = ReflectionUtil.getAllFields(value.getClass());
                for (Field subField : allSubFields) {
                    Object subFieldValue = ReflectionUtil.runGetter(subField, value);
                }
            } else {
                fieldValues.add(value);
            }
        }

        if (hideSimilarFeature) {
            Set<Object> fieldValueSet = new LinkedHashSet<>();
            fieldValues.forEach(value-> fieldValueSet.add(value));
            if(fieldValueSet.size() <= 1) {
                return;
            }
        }
        report.getComparisonMap().put(fieldName,fieldValues);
    }


    private static void compareObjects(List<Object> objects, ComparisonReport report, boolean hideSimilarFeature ,String keyPrefix) {
        Class clazz = objects.get(0).getClass();
        List<Field> fields = ReflectionUtil.getAllFields(clazz);
        for (Field field : fields) {
            List<Object> fieldValues = new LinkedList<>();
            /*if (fieldsToIgnoreInReport.contains(field.getName())) {
                continue;
            }*/
            String fieldName = field.getName();
            for(Object object : objects) {
                fieldValues.add(ReflectionUtil.runGetter(field, object));
            }
            if (hideSimilarFeature) {
                Set<Object> fieldValueSet = new LinkedHashSet<>();
                fieldValues.forEach(value-> fieldValueSet.add(value));
                if(fieldValueSet.size() <= 1) {
                    continue;
                }
            }
            String fieldKey = keyPrefix.isEmpty() ? fieldName : keyPrefix + "-" + fieldName;
            if (field.getType().getPackage() !=null &&
                    field.getType().getPackage().getName().startsWith("com.example.carcompare.model")) {
                compareObjects(fieldValues,report,hideSimilarFeature,fieldKey);
            } else {
                report.getComparisonMap().put(fieldKey,fieldValues);
            }
        }
    }


    public static void compareObjects(List<Object> objects, ComparisonReport report, boolean hideSimilarFeature) {
        compareObjects(objects,report, hideSimilarFeature, "");
    }
}
