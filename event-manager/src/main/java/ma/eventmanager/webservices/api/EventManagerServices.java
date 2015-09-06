package ma.eventmanager.webservices.api;

import javax.jws.WebMethod;
import javax.jws.WebService;


@WebService
public interface EventManagerServices{

	@WebMethod
	public String subscribeClient(String clientId);
}
