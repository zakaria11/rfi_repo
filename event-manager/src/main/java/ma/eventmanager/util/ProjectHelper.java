package ma.eventmanager.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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
}
