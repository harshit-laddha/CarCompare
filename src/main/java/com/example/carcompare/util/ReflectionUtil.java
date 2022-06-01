package com.example.carcompare.util;

import com.example.carcompare.model.ModelVariant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReflectionUtil {
    static Logger logger = LoggerFactory.getLogger(ReflectionUtil.class);

    public static List<Field> getAllFields(Class<?> clazz) {
        List<Field> resultFields = new ArrayList<>();
        for (Class<?> c = clazz; c != null; c = c.getSuperclass())
        {
            Field[] fields = c.getDeclaredFields();
            for (Field classField : fields)
            {
                resultFields.add(classField);
            }
        }
        return resultFields;
    }

    public static Object runGetter(Field field, Object o) {
        for(Method method : o.getClass().getMethods()) {
            if ((method.getName().startsWith("get")) && (method.getName().length() == (field.getName().length() + 3))) {
                if (method.getName().toLowerCase().endsWith(field.getName().toLowerCase())) {
                    try
                    {
                        return method.invoke(o);
                    }
                    catch (IllegalAccessException e)
                    {
                        logger.info("Could not determine method: " + method.getName());
                    }
                    catch (InvocationTargetException e)
                    {
                        logger.info("Could not determine method: " + method.getName());
                    }
                }
            }
        }
        return null;
    }
}
