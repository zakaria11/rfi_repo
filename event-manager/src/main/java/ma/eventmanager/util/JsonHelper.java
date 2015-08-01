package ma.eventmanager.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

public class JsonHelper
{
	
	private static ObjectMapper m = new ObjectMapper();
	

	public static String toJson(Object pojo) throws IOException {
		return m.writeValueAsString(pojo);
	}
	
	public static void sendObjectAsJsonResponse(Object pojo, HttpServletResponse response) throws IOException {	
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(JsonHelper.toJson(pojo));
		out.flush();
	}
}
