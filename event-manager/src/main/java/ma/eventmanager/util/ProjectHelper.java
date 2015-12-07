package ma.eventmanager.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import ma.eventmanager.annotations.ViewField;
import ma.eventmanager.annotations.ViewObject;
import ma.eventmanager.model.Attribute;
import ma.eventmanager.model.ViewEntity;

import org.codehaus.jackson.map.ObjectMapper;

import com.google.common.base.Splitter;

public class ProjectHelper
{
	
	private static ObjectMapper m = new ObjectMapper();
	

	public static String toJson(Object pojo) throws IOException {
		return m.writeValueAsString(pojo);
	}
	
	public static void sendObjectAsJsonResponse(Object pojo, HttpServletResponse response) throws IOException {	
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(ProjectHelper.toJson(pojo));
		out.flush();
	}
	
	public static Map<String, String> parseMap(String formattedMap) {
	    return Splitter.on(",").withKeyValueSeparator("=").split(formattedMap);
	}

	public static ViewEntity toViewEntity(Object entity){
		
		ViewEntity viewEntity = new ViewEntity();
		if(entity != null){
			if(entity.getClass().isAnnotationPresent(ViewObject.class)){
				viewEntity.setName(entity.getClass().getAnnotation(ViewObject.class).name());				
			}

			Method[] methods = entity.getClass().getMethods();
			List<Attribute> attributes = new ArrayList<Attribute>();
		    for (Method method : methods) {
		    	if (method.isAnnotationPresent(ViewField.class)) {
		    		Attribute attr = new Attribute();
		    		attr.setLabel(method.getAnnotation(ViewField.class).name());
					String value;
					try{
						Object object = method.invoke(entity,null);
						if(object != null){
							value = (String) object; 
				    		attr.setValue(value);
				    		attributes.add(attr);							 
						}
					}
					catch (IllegalArgumentException e){
						e.printStackTrace();
					}
					catch (IllegalAccessException e){
						e.printStackTrace();
					}
					catch (InvocationTargetException e){
						e.printStackTrace();
					}
		    	}
		    }
			viewEntity.setAttributes(attributes);
		}
		return viewEntity;
	}
}
