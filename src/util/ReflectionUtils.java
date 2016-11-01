package util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;

import org.apache.commons.lang3.StringUtils;

import model.infra.Column;

public class ReflectionUtils {

	public static void resultSetToObject(Object object, ResultSet resultSet) {
		
		Class<?> objectClazz = object.getClass();
		
		try {
			
			for (Field objectField : objectClazz.getDeclaredFields()) {
				
				if (objectField.isAnnotationPresent(Column.class)) {
					
					Column columnAnnotation = objectField.getAnnotation(Column.class);
					String columnName = columnAnnotation.name();
					
					String property = objectField.getName();
					String objectSetterMethodName = "set"+StringUtils.capitalize(property);
					Method objectSetterMethod = objectClazz.getDeclaredMethod(objectSetterMethodName, objectField.getType());
					
					String parameterType = "Integer".equals(objectField.getType().getSimpleName()) ? "Int" : objectField.getType().getSimpleName();
					String resultSetMethodName = "get"+StringUtils.capitalize(parameterType);
					
					Class<ResultSet> rsClazz = ResultSet.class;
					Method rsMethod = rsClazz.getDeclaredMethod(resultSetMethodName, String.class);
					objectSetterMethod.invoke(object, rsMethod.invoke(resultSet, columnName));
				}
			}
			
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException |
				IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException(String.format("Erro ao popular objeto '%s' com ResultSet.", objectClazz.getSimpleName()), e);
		} 
	}
 
	public static boolean validateObject(Object object) {
		
		Class<?> objectClazz = object.getClass();
		
		try {
			
			for (Field objectField : objectClazz.getDeclaredFields()) {
				
				if (objectField.isAnnotationPresent(Column.class)) {
					
					Column columnAnnotation = objectField.getAnnotation(Column.class);
					boolean required = columnAnnotation.required();
					
					String property = objectField.getName();
					String propertyGetMethodName = "get"+StringUtils.capitalize(property);
					Method objectSetterMethod = objectClazz.getDeclaredMethod(propertyGetMethodName);
					Object fieldValue = objectSetterMethod.invoke(object);
					
					if (required) {
						if (fieldValue instanceof String) {
							String strValue = (String) fieldValue;
							if (StringUtils.isEmpty(strValue)) 
								return false;
						}
						else if (fieldValue == null)
							return false;
					}
				}
			}
			return true;
			
		} catch (SecurityException | IllegalArgumentException | 
				IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
			throw new RuntimeException(String.format("Erro ao validar objeto '%s'", objectClazz.getSimpleName()), e);
		} 
	}

}
