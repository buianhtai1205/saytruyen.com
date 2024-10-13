package vn.com.saytruyen.common_service.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * The type Reflection utils.
 */
@Slf4j
public class ReflectionUtils {

    /**
     * Copy non-null fields.
     *
     * @param source the source
     * @param target the target
     */
    public static void copyNonNullFields(Object source, Object target) {
        Field[] sourceFields = source.getClass().getDeclaredFields();
        Field[] targetFields = target.getClass().getDeclaredFields();

        for (Field sourceField : sourceFields) {
            sourceField.setAccessible(true);

            for (Field targetField : targetFields) {
                targetField.setAccessible(true);

                if (sourceField.getName().equals(targetField.getName())) {
                    try {
                        Object value = sourceField.get(source);
                        if (value != null) {
                            targetField.set(target, value);
                        }
                    } catch (IllegalAccessException e) {
                        log.error(e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
