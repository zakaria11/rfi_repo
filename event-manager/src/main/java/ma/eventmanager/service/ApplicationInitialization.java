package ma.eventmanager.service;

import java.util.ArrayList;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import ma.eventmanager.dao.EventManagerDao;

public class ApplicationInitialization{
	
	private EventManagerDao eventManagerDao;

	
	@PostConstruct
	public void initApp() throws Exception {
		eventManagerDao.addUser("zakaria", "zakaria",new ArrayList<String>(Arrays.asList("ROLE_EVENT_ADMIN")));
		eventManagerDao.addUser("borne", "borne",new ArrayList<String>(Arrays.asList("BORNE_ROLE")));
	}


	public EventManagerDao getEventManagerDao()
	{
		return eventManagerDao;
	}


	public void setEventManagerDao(EventManagerDao eventManagerDao)
	{
		this.eventManagerDao = eventManagerDao;
	}
	
	
	

}
