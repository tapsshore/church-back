package com.shoshore.churchback.util;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

/**
 * @author : tapiwanasheshoshore
 * @mailto : tapsshore@gmail.com
 * @created : 18/5/2023, Thursday
 **/
public class ReflectionUtil {
    private ReflectionUtil() {
    }

    public static Object getFieldValue(Object object, String fieldName) throws IllegalAccessException, NoSuchFieldException {
        if (fieldName == null) {
            return null;
        } else if (object == null) {
            return null;
        } else {
            Class<?> type = object.getClass();
            if (fieldName.contains(".")) {
                String[] fieldParts = fieldName.split("\\.");
                if (fieldParts.length == 0) {
                    throw new NoSuchFieldException(fieldName);
                } else {
                    String firstFieldName = fieldParts[0];
                    if (StringUtils.isBlank(firstFieldName)) {
                        throw new NoSuchFieldException(fieldName);
                    } else {
                        Field field = getField(type, firstFieldName);
                        field.setAccessible(true);
                        return getFieldValue(field.get(object), fieldName.replace(firstFieldName + ".", ""));
                    }
                }
            } else {
                Field field = getField(type, fieldName);
                field.setAccessible(true);
                return field.get(object);
            }
        }
    }


    public static Field getField(Class<?> type, String fieldName) throws NoSuchFieldException {
        Field[] fields = type.getDeclaredFields();
        Field[] var3 = fields;
        int var4 = fields.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            Field declaredField = var3[var5];
            String key = declaredField.getName();
            if (fieldName.equals(key)) {
                declaredField.setAccessible(true);
                return declaredField;
            }
        }

        Class<?> superClass = type.getSuperclass();
        if (superClass != null) {
            return getField(superClass, fieldName);
        } else {
            throw new NoSuchFieldException(fieldName);
        }
    }
}
