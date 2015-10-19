package ma.eventmanager.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import ma.eventmanager.entitys.ViewField;
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

	public static ViewEntity toViewEntity(Object entity)
	{
		List<Attribute> attributes = new ArrayList<Attribute>();
		//Check for @ViewField Annotations
	      Method[] methods = entity.getClass().getMethods();
	      for (Method method : methods) {
	          if (method.isAnnotationPresent(myAnnotation)) {
	        	  Attribute attr = new Attribute();
	        	  attributes.add(attr);
	          }
	      }


		// TODO Auto-generated method stub
		return null;
	}
}
