package com.shoshore.churchback.util;

import com.shoshore.churchback.exceptions.ValidationException;

/**
 * @author : tapiwanasheshoshore
 * @mailto : tapsshore@gmail.com
 * @created : 18/5/2023, Thursday
 **/
public class ValidationUtil {
    private ValidationUtil() {
    }

    public static void validateFieldPresent(Object object, String fieldName) throws ValidationException {
        Object value;
        try {
            value = ReflectionUtil.getFieldValue(object, fieldName);
        } catch (Exception var4) {
            throw new ValidationException(fieldName, "Missing field");
        }

        if (value == null || (value instanceof String && ((String) value).trim().length() == 0)) {
            throw new ValidationException(fieldName, "Missing field");
        }
    }
}
