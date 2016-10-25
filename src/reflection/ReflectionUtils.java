package reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;

import org.apache.commons.lang3.StringUtils;

import model.Column;

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
			
			/*for (Method objectMethod : objectClazz.getMethods()) {
				
				if (objectMethod.getName().startsWith("set") 
					&&  objectMethod.getParameterCount() == 1
					&&  objectMethod.getReturnType() == void.class) {
					
					String property = objectMethod.getName().substring(3, objectMethod.getName().length());
					
					String parameterType = objectMethod.getParameterTypes()[0].getSimpleName();
					parameterType = "Integer".equals(parameterType) ? "Int" : parameterType;
					String resultSetMethodName = "get"+parameterType;
					
					Class<ResultSet> rsClazz = ResultSet.class;
					Method rsMethod = rsClazz.getDeclaredMethod(resultSetMethodName, String.class);
					objectMethod.invoke(object, rsMethod.invoke(resultSet, property));
				}
			}*/
			
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException |
				IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException(String.format("Erro ao popular objeto '%s' com ResultSet.", objectClazz.getSimpleName()), e);
		} 
	}

}
