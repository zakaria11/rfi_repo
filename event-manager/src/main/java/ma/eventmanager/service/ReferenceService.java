package ma.eventmanager.service;

import java.util.Map;


public class ReferenceService{
	
	private static Map<String, String> envVariabls;
	private static Map<String, String> messages;
	private static Map<String, String> paymentMethods;


	public static Map<String, String> getEnvVariabls()
	{
		return envVariabls;
	}

	public static void setEnvVariabls(Map<String, String> envVariabls)
	{
		ReferenceService.envVariabls = envVariabls;
	}

	public static Map<String, String> getMessages()
	{
		return messages;
	}

	public static void setMessages(Map<String, String> messages)
	{
		ReferenceService.messages = messages;
	}

	public static Map<String, String> getPaymentMethods()
	{
		return paymentMethods;
	}

	public static void setPaymentMethods(Map<String, String> paymentMethods)
	{
		ReferenceService.paymentMethods = paymentMethods;
	}

	



}
