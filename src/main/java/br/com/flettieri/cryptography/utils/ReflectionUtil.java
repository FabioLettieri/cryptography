package br.com.flettieri.cryptography.utils;

import java.lang.reflect.Field;

public abstract class ReflectionUtil {

	public static void copyProperties(Object toUpdate, Object target) {
	    Field[] fields = toUpdate.getClass().getDeclaredFields();
	    for (Field field : fields) {
	        try {
	            field.setAccessible(true);
	            Object value = field.get(toUpdate);
	            if (value != null) {
	                Field targetField = target.getClass().getDeclaredField(field.getName());
	                targetField.setAccessible(true);
	                targetField.set(target, value);
	            }
	        } catch (NoSuchFieldException | IllegalAccessException e) {
	        }
	    }
	}
}
